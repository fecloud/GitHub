#define LOG_TAG "ReadEventHub"

#include "LocalLog.h"
#include "ReadEventHub.h"
#include "KeyLayoutMap.h"

#include <hardware_legacy/power.h>

#include <cutils/properties.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>
#include <dirent.h>
#include <fcntl.h>
//#include <utils/Log.h>

#include <sys/poll.h>
#include <sys/ioctl.h>

/* this macro is used to tell if "bit" is set in "array"
 * it selects a byte from the array, and does a boolean AND
 * operation with a byte that only has the relevant bit set.
 * eg. to check for the 12th bit, we do (array[1] & 1<<4)
 */



#define test_bit(bit, array)    (array[bit/8] & (1<<(bit%8)))

/* this macro computes the number of bytes needed to represent a bit array of the specified size */
#define sizeof_bit_array(bits)  ((bits + 7) / 8)

#define ID_MASK  0x0000ffff
#define SEQ_MASK 0x7fff0000
#define SEQ_SHIFT 16
static const char *WAKE_LOCK_ID = "KeyEvents";
static const char *device_path = "/dev/input";

ReadEventHub::device_t::device_t(int32_t _id, const char* _path, const char* name)
    : id(_id), path(_path), name(name), classes(0)
    , keyBitmask(NULL), layoutMap(new KeyLayoutMap()), fd(-1), next(NULL) {
}

ReadEventHub::device_t::~device_t() {
    delete [] keyBitmask;
    delete layoutMap;
}

static bool containsNonZeroByte(const uint8_t* array, uint32_t startIndex, uint32_t endIndex) {
    const uint8_t* end = array + endIndex;
    array += startIndex;
    while (array != end) {
        if (*(array++) != 0) {
            return true;
        }
    }
    return false;
}


/*
ReadEventHub::ReadEventHub()
{
    LOGD("New ReadEventHub!\n");
}


ReadEventHub::~ReadEventHub() 
{
    LOGD("Destory ReadEventHub!\n");

    //release_wake_lock(WAKE_LOCK_ID);
    // we should free stuff here...
}
*/

ReadEventHub::ReadEventHub(void)
    : mHaveFirstKeyboard(false), mFirstKeyboardId(0)
    , mDevicesById(0), mNumDevicesById(0)
    , mOpeningDevices(0), mClosingDevices(0)
    , mDevices(0), mFDs(0), mFDCount(0), mOpened(false), mNeedToSendFinishedDeviceScan(false)
    , mInputBufferIndex(0), mInputBufferCount(0), mInputDeviceIndex(0)
{
    acquire_wake_lock(PARTIAL_WAKE_LOCK, WAKE_LOCK_ID);
//Temp mark
#if 0 //def EV_SW
    memset(mSwitches, 0, sizeof(mSwitches));
#endif
}

/*
 * Clean up.
 */
ReadEventHub::~ReadEventHub(void)
{
    LOGD("Destory ReadEventHub!\n");
    release_wake_lock(WAKE_LOCK_ID);
    // we should free stuff here...
}


void ReadEventHub::initRawEvent(RawEvent* outEvent)
{
    LOGD("ReadEventHub initRawEvent!\n");
    outEvent->deviceId = 0;
    outEvent->type = 0;
    outEvent->scanCode = 0;
    outEvent->keyCode = 0;
    outEvent->flags = 0;
    outEvent->value = 0;
    outEvent->when = 0;
}

int ReadEventHub::openDevice(const char *deviceName)
{
    int version;
    int fd;
    struct pollfd *new_mFDs;
    device_t **new_devices;
    char **new_device_names;
    char name[80];
    char location[80];
    char idstr[80];
    struct input_id id;

    LOGD("Opening device: %s", deviceName);

    AutoMutex _l(mLock);

    fd = open(deviceName, O_RDWR);
    if(fd < 0) {
        LOGE("could not open %s, %s\n", deviceName, strerror(errno));
        return -1;
    }

    if(ioctl(fd, EVIOCGVERSION, &version)) {
        LOGE("could not get driver version for %s, %s\n", deviceName, strerror(errno));
        return -1;
    }
    if(ioctl(fd, EVIOCGID, &id)) {
        LOGE("could not get driver id for %s, %s\n", deviceName, strerror(errno));
        return -1;
    }
    name[sizeof(name) - 1] = '\0';
    location[sizeof(location) - 1] = '\0';
    idstr[sizeof(idstr) - 1] = '\0';
    if(ioctl(fd, EVIOCGNAME(sizeof(name) - 1), &name) < 1) {
        //fprintf(stderr, "could not get device name for %s, %s\n", deviceName, strerror(errno));
        name[0] = '\0';
    }

    // check to see if the device is on our excluded list
    List<String8>::iterator iter = mExcludedDevices.begin();
    List<String8>::iterator end = mExcludedDevices.end();
    for ( ; iter != end; iter++) {
        const char* test = *iter;
        if (strcmp(name, test) == 0) {
            LOGI("ignoring event id %s driver %s\n", deviceName, test);
            close(fd);
            return -1;
        }
    }

    if(ioctl(fd, EVIOCGPHYS(sizeof(location) - 1), &location) < 1) {
        //fprintf(stderr, "could not get location for %s, %s\n", deviceName, strerror(errno));
        location[0] = '\0';
    }
    if(ioctl(fd, EVIOCGUNIQ(sizeof(idstr) - 1), &idstr) < 1) {
        //fprintf(stderr, "could not get idstring for %s, %s\n", deviceName, strerror(errno));
        idstr[0] = '\0';
    }

    if (fcntl(fd, F_SETFL, O_NONBLOCK)) {
        LOGE("Error %d making device file descriptor non-blocking.", errno);
        close(fd);
        return -1;
    }

    int devid = 0;
    while (devid < mNumDevicesById) {
        if (mDevicesById[devid].device == NULL) {
            break;
        }
        devid++;
    }
    if (devid >= mNumDevicesById) {
        device_ent* new_devids = (device_ent*)realloc(mDevicesById,
                sizeof(mDevicesById[0]) * (devid + 1));
        if (new_devids == NULL) {
            LOGE("out of memory");
            return -1;
        }
        mDevicesById = new_devids;
        mNumDevicesById = devid+1;
        mDevicesById[devid].device = NULL;
        mDevicesById[devid].seq = 0;
    }

    mDevicesById[devid].seq = (mDevicesById[devid].seq+(1<<SEQ_SHIFT))&SEQ_MASK;
    if (mDevicesById[devid].seq == 0) {
        mDevicesById[devid].seq = 1<<SEQ_SHIFT;
    }

    new_mFDs = (pollfd*)realloc(mFDs, sizeof(mFDs[0]) * (mFDCount + 1));
    new_devices = (device_t**)realloc(mDevices, sizeof(mDevices[0]) * (mFDCount + 1));
    if (new_mFDs == NULL || new_devices == NULL) {
        LOGE("out of memory");
        return -1;
    }
    mFDs = new_mFDs;
    mDevices = new_devices;

#if 0
    LOGI("add device %d: %s\n", mFDCount, deviceName);
    LOGI("  bus:      %04x\n"
         "  vendor    %04x\n"
         "  product   %04x\n"
         "  version   %04x\n",
        id.bustype, id.vendor, id.product, id.version);
    LOGI("  name:     \"%s\"\n", name);
    LOGI("  location: \"%s\"\n"
         "  id:       \"%s\"\n", location, idstr);
    LOGI("  version:  %d.%d.%d\n",
        version >> 16, (version >> 8) & 0xff, version & 0xff);
#endif

    device_t* device = new device_t(devid|mDevicesById[devid].seq, deviceName, name);
    if (device == NULL) {
        LOGE("out of memory");
        return -1;
    }

    device->fd = fd;
    mFDs[mFDCount].fd = fd;
    mFDs[mFDCount].events = POLLIN;
    mFDs[mFDCount].revents = 0;

    // Figure out the kinds of events the device reports.
    
    uint8_t key_bitmask[sizeof_bit_array(KEY_MAX + 1)];
    memset(key_bitmask, 0, sizeof(key_bitmask));

    LOGV("Getting keys...");
    if (ioctl(fd, EVIOCGBIT(EV_KEY, sizeof(key_bitmask)), key_bitmask) >= 0) {
        //LOGI("MAP\n");
        //for (int i = 0; i < sizeof(key_bitmask); i++) {
        //    LOGI("%d: 0x%02x\n", i, key_bitmask[i]);
        //}

        // See if this is a keyboard.  Ignore everything in the button range except for
        // gamepads which are also considered keyboards.
        if (containsNonZeroByte(key_bitmask, 0, sizeof_bit_array(BTN_MISC))
                || containsNonZeroByte(key_bitmask, sizeof_bit_array(BTN_GAMEPAD),
                        sizeof_bit_array(BTN_DIGI))
                || containsNonZeroByte(key_bitmask, sizeof_bit_array(KEY_OK),
                        sizeof_bit_array(KEY_MAX + 1))) {
            device->classes |= INPUT_DEVICE_CLASS_KEYBOARD;

            device->keyBitmask = new uint8_t[sizeof(key_bitmask)];
            if (device->keyBitmask != NULL) {
                memcpy(device->keyBitmask, key_bitmask, sizeof(key_bitmask));
            } else {
                delete device;
                LOGE("out of memory allocating key bitmask");
                return -1;
            }
        }
    }
  
#if 1 //Temp mark it
    // See if this is a trackball (or mouse).
    if (test_bit(BTN_MOUSE, key_bitmask)) {
        uint8_t rel_bitmask[sizeof_bit_array(REL_MAX + 1)];
        memset(rel_bitmask, 0, sizeof(rel_bitmask));
        LOGV("Getting relative controllers...");
        if (ioctl(fd, EVIOCGBIT(EV_REL, sizeof(rel_bitmask)), rel_bitmask) >= 0) {
            if (test_bit(REL_X, rel_bitmask) && test_bit(REL_Y, rel_bitmask)) {
                device->classes |= INPUT_DEVICE_CLASS_TRACKBALL;
            }
        }
    }

#endif


    // See if this is a touch pad.
    uint8_t abs_bitmask[sizeof_bit_array(ABS_MAX + 1)];
    memset(abs_bitmask, 0, sizeof(abs_bitmask));
    LOGV("Getting absolute controllers...");
    if (ioctl(fd, EVIOCGBIT(EV_ABS, sizeof(abs_bitmask)), abs_bitmask) >= 0) {
        // Is this a new modern multi-touch driver?
        if (test_bit(ABS_MT_POSITION_X, abs_bitmask)
                && test_bit(ABS_MT_POSITION_Y, abs_bitmask)) {
            device->classes |= INPUT_DEVICE_CLASS_TOUCHSCREEN | INPUT_DEVICE_CLASS_TOUCHSCREEN_MT;

        // Is this an old style single-touch driver?
        } else if (test_bit(BTN_TOUCH, key_bitmask)
                && test_bit(ABS_X, abs_bitmask) && test_bit(ABS_Y, abs_bitmask)) {
            device->classes |= INPUT_DEVICE_CLASS_TOUCHSCREEN;
        }
    }
//#endif

#if 0
#ifdef EV_SW
    // figure out the switches this device reports
    uint8_t sw_bitmask[sizeof_bit_array(SW_MAX + 1)];
    memset(sw_bitmask, 0, sizeof(sw_bitmask));
    bool hasSwitches = false;
    if (ioctl(fd, EVIOCGBIT(EV_SW, sizeof(sw_bitmask)), sw_bitmask) >= 0) {
        for (int i=0; i<EV_SW; i++) {
            //LOGI("Device 0x%x sw %d: has=%d", device->id, i, test_bit(i, sw_bitmask));
            if (test_bit(i, sw_bitmask)) {
                hasSwitches = true;
                if (mSwitches[i] == 0) {
                    mSwitches[i] = device->id;
                }
            }
        }
    }
    if (hasSwitches) {
        device->classes |= INPUT_DEVICE_CLASS_SWITCH;
    }
#endif
#endif

    if ((device->classes & INPUT_DEVICE_CLASS_KEYBOARD) != 0) {
        char tmpfn[sizeof(name)];
        char keylayoutFilename[300];

        // a more descriptive name
        device->name = name;

        // replace all the spaces with underscores
        strcpy(tmpfn, name);
        for (char *p = strchr(tmpfn, ' '); p && *p; p = strchr(tmpfn, ' '))
            *p = '_';

        // find the .kl file we need for this device
        const char* root = getenv("ANDROID_ROOT");
        snprintf(keylayoutFilename, sizeof(keylayoutFilename),
                 "%s/usr/keylayout/%s.kl", root, tmpfn);
        bool defaultKeymap = false;
        if (access(keylayoutFilename, R_OK)) {
            snprintf(keylayoutFilename, sizeof(keylayoutFilename),"%s/usr/keylayout/%s", root, "qwerty.kl");
            defaultKeymap = true;
        }

        //#if 1  //Temp mark it here
        //status_t status = device->layoutMap->load(keylayoutFilename);
        int status = device->layoutMap->load(keylayoutFilename);
        if (status) {
            LOGE("Error %d loading key layout.", status);
        }

        // tell the world about the devname (the descriptive name)
        if (!mHaveFirstKeyboard && !defaultKeymap && strstr(name, "-keypad")) {
            // the built-in keyboard has a well-known device ID of 0,
            // this device better not go away.
            mHaveFirstKeyboard = true;
            mFirstKeyboardId = device->id;
            //property_set("hw.keyboards.0.devname", name);
            LOGD("The devname 1:%s", name);
        } else {
            // ensure mFirstKeyboardId is set to -something-.
            if (mFirstKeyboardId == 0) {
                mFirstKeyboardId = device->id;
            }
        }
        char propName[100];
        sprintf(propName, "hw.keyboards.%u.devname", device->id);
        //property_set(propName, name);
        LOGD("The devname 2:%s", name);

        // 'Q' key support = cheap test of whether this is an alpha-capable kbd
        if (hasKeycodeLocked(device, AKEYCODE_Q)) {
            device->classes |= INPUT_DEVICE_CLASS_ALPHAKEY;
        }
        
       #if 0
        // See if this device has a DPAD.
        if (hasKeycodeLocked(device, AKEYCODE_DPAD_UP) &&
                hasKeycodeLocked(device, AKEYCODE_DPAD_DOWN) &&
                hasKeycodeLocked(device, AKEYCODE_DPAD_LEFT) &&
                hasKeycodeLocked(device, AKEYCODE_DPAD_RIGHT) &&
                hasKeycodeLocked(device, AKEYCODE_DPAD_CENTER)) {
            device->classes |= INPUT_DEVICE_CLASS_DPAD;
        }
        
        // See if this device has a gamepad.
        for (size_t i = 0; i < sizeof(GAMEPAD_KEYCODES)/sizeof(GAMEPAD_KEYCODES[0]); i++) {
            if (hasKeycodeLocked(device, GAMEPAD_KEYCODES[i])) {
                device->classes |= INPUT_DEVICE_CLASS_GAMEPAD;
                break;
            }
        }
        #endif

        LOGI("New keyboard: device->id=0x%x devname='%s' propName='%s' keylayout='%s'\n",
                device->id, name, propName, keylayoutFilename);
    }
    

    // If the device isn't recognized as something we handle, don't monitor it.
    if (device->classes == 0) {
        LOGV("Dropping device %s %p, id = %d\n", deviceName, device, devid);
        close(fd);
        delete device;
        return -1;
    }

    LOGI("New device: path=%s name=%s id=0x%x (of 0x%x) index=%d fd=%d classes=0x%x\n",
         deviceName, name, device->id, mNumDevicesById, mFDCount, fd, device->classes);
         
    LOGV("Adding device %s %p at %d, id = %d, classes = 0x%x\n",
         deviceName, device, mFDCount, devid, device->classes);

    mDevicesById[devid].device = device;
    device->next = mOpeningDevices;
    mOpeningDevices = device;
    mDevices[mFDCount] = device;

    mFDCount++;
    return 0;
}

bool ReadEventHub::hasKeycodeLocked(device_t* device, int keycode) const
{
    if (device->keyBitmask == NULL || device->layoutMap == NULL) {
        return false;
    }
    
    Vector<int32_t> scanCodes;
    device->layoutMap->findScancodes(keycode, &scanCodes);
    const size_t N = scanCodes.size();
    for (size_t i=0; i<N && i<=KEY_MAX; i++) {
        int32_t sc = scanCodes.itemAt(i);
        if (sc >= 0 && sc <= KEY_MAX && test_bit(sc, device->keyBitmask)) {
            return true;
        }
    }
    
    return false;
}

int ReadEventHub::scanDir(const char *dirname)
{
    char devname[PATH_MAX];
    char *filename;
    DIR *dir;
    struct dirent *de;

    LOGD("ReadEventHub scanDir!\n");
    dir = opendir(dirname);
    if(dir == NULL)
        return -1;
    strcpy(devname, dirname);
    filename = devname + strlen(devname);
    *filename++ = '/';
    while((de = readdir(dir))) {
        if(de->d_name[0] == '.' &&
           (de->d_name[1] == '\0' ||
            (de->d_name[1] == '.' && de->d_name[2] == '\0')))
            continue;
        strcpy(filename, de->d_name);
        LOGD("ReadEventHub openDevice:%s!\n",devname);
        openDevice(devname);
    }
    closedir(dir);
    return 0;
}

bool ReadEventHub::openPlatformInput(void)
{
    /*
     * Open platform-specific input device(s).
     */
    int res;
    
    LOGD("ReadEventHub openPlatformInput!\n");

    mFDCount = 1;
    mFDs = (pollfd *)calloc(1, sizeof(mFDs[0]));
    mDevices = (device_t **)calloc(1, sizeof(mDevices[0]));
    mFDs[0].events = POLLIN;
    mFDs[0].revents = 0;
    mDevices[0] = NULL;


    /*
     * The code in EventHub::getEvent assumes that mFDs[0] is an inotify fd.
     * We allocate space for it and set it to something invalid.
     */
    mFDs[0].fd = -1;

    res = scanDir(device_path);
    if(res < 0) {
        LOGE("scan dir failed for %s\n", device_path);
    }

    return true;

}

bool ReadEventHub::getRawEvent(RawEvent* outEvent)
{
    LOGD("ReadEventHub getEvent!\n");
    
    bool mError;
    initRawEvent(outEvent);

    // Note that we only allow one caller to getEvent(), so don't need
    // to do locking here...  only when adding/removing devices.

    if (!mOpened) {
        mError = openPlatformInput() ? NO_ERROR : UNKNOWN_ERROR;
        mOpened = true;
        mNeedToSendFinishedDeviceScan = true;
    }
    
for (;;) {
        // Report any devices that had last been added/removed.
        if (mClosingDevices != NULL) {
            device_t* device = mClosingDevices;
            LOGV("Reporting device closed: id=0x%x, name=%s\n",
                 device->id, device->path.string());
            mClosingDevices = device->next;
            if (device->id == mFirstKeyboardId) {
                outEvent->deviceId = 0;
            } else {
                outEvent->deviceId = device->id;
            }
            outEvent->type = DEVICE_REMOVED;
            outEvent->when = systemTime(SYSTEM_TIME_MONOTONIC);
            delete device;
            mNeedToSendFinishedDeviceScan = true;
            return true;
        }

        if (mOpeningDevices != NULL) {
            device_t* device = mOpeningDevices;
            LOGV("Reporting device opened: id=0x%x, name=%s\n",
                 device->id, device->path.string());
            mOpeningDevices = device->next;
            if (device->id == mFirstKeyboardId) {
                outEvent->deviceId = 0;
            } else {
                outEvent->deviceId = device->id;
            }
            outEvent->type = DEVICE_ADDED;
            outEvent->when = systemTime(SYSTEM_TIME_MONOTONIC);
            mNeedToSendFinishedDeviceScan = true;
            return true;
        }

        if (mNeedToSendFinishedDeviceScan) {
            mNeedToSendFinishedDeviceScan = false;
            outEvent->type = FINISHED_DEVICE_SCAN;
            outEvent->when = systemTime(SYSTEM_TIME_MONOTONIC);
            return true;
        }

// Grab the next input event.
        for (;;) {
            // Consume buffered input events, if any.
            if (mInputBufferIndex < mInputBufferCount) {
                const struct input_event& iev = mInputBufferData[mInputBufferIndex++];
                const device_t* device = mDevices[mInputDeviceIndex];

                LOGV("%s got: t0=%d, t1=%d, type=%d, code=%d, v=%d", device->path.string(),
                     (int) iev.time.tv_sec, (int) iev.time.tv_usec, iev.type, iev.code, iev.value);
                if (device->id == mFirstKeyboardId) {
                    outEvent->deviceId = 0;
                } else {
                    outEvent->deviceId = device->id;
                }
                outEvent->type = iev.type;
                outEvent->scanCode = iev.code;
                if (iev.type == EV_KEY) {
                    status_t err = device->layoutMap->map(iev.code,
                            & outEvent->keyCode, & outEvent->flags);
                    LOGV("iev.code=%d keyCode=%d flags=0x%08x err=%d\n",
                        iev.code, outEvent->keyCode, outEvent->flags, err);
                    if (err != 0) {
                        outEvent->keyCode = AKEYCODE_UNKNOWN;
                        outEvent->flags = 0;
                    }
                } else {
                    outEvent->keyCode = iev.code;
                }
                outEvent->value = iev.value;

                // Use an event timestamp in the same timebase as
                // java.lang.System.nanoTime() and android.os.SystemClock.uptimeMillis()
                // as expected by the rest of the system.
                outEvent->when = systemTime(SYSTEM_TIME_MONOTONIC);
                return true;
            }

            // Finish reading all events from devices identified in previous poll().
            // This code assumes that mInputDeviceIndex is initially 0 and that the
            // revents member of pollfd is initialized to 0 when the device is first added.
            // Since mFDs[0] is used for inotify, we process regular events starting at index 1.
            mInputDeviceIndex += 1;
            if (mInputDeviceIndex >= mFDCount) {
                break;
            }

            const struct pollfd& pfd = mFDs[mInputDeviceIndex];
            if (pfd.revents & POLLIN) {
                int32_t readSize = read(pfd.fd, mInputBufferData,
                        sizeof(struct input_event) * INPUT_BUFFER_SIZE);
                if (readSize < 0) {
                    if (errno != EAGAIN && errno != EINTR) {
                        LOGW("could not get event (errno=%d)", errno);
                    }
                } else if ((readSize % sizeof(struct input_event)) != 0) {
                    LOGE("could not get event (wrong size: %d)", readSize);
                } else {
                    mInputBufferCount = readSize / sizeof(struct input_event);
                    mInputBufferIndex = 0;
                }
            }
        }

//Temp mark
#if 0// HAVE_INOTIFY
        // readNotify() will modify mFDs and mFDCount, so this must be done after
        // processing all other events.
        if(mFDs[0].revents & POLLIN) {
            readNotify(mFDs[0].fd);
            mFDs[0].revents = 0;
            continue; // report added or removed devices immediately
        }
#endif

        mInputDeviceIndex = 0;

        // Poll for events.  Mind the wake lock dance!
        // We hold a wake lock at all times except during poll().  This works due to some
        // subtle choreography.  When a device driver has pending (unread) events, it acquires
        // a kernel wake lock.  However, once the last pending event has been read, the device
        // driver will release the kernel wake lock.  To prevent the system from going to sleep
        // when this happens, the EventHub holds onto its own user wake lock while the client
        // is processing events.  Thus the system can only sleep if there are no events
        // pending or currently being processed.

        //Temp mark
        release_wake_lock(WAKE_LOCK_ID);

        int pollResult = poll(mFDs, mFDCount, -1);
        
        //Temp mark
        acquire_wake_lock(PARTIAL_WAKE_LOCK, WAKE_LOCK_ID);

        if (pollResult <= 0) {
            if (errno != EINTR) {
                LOGW("poll failed (errno=%d)\n", errno);
                usleep(100000);
            }
        }



    }
    //return true;
}

