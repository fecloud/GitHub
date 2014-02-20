#ifndef _RUNTIME_EVENT_HUB_H
#define _RUNTIME_EVENT_HUB_H

//#include <utils/Log.h>
#include "LocalLog.h"

#include <android/input.h>
#include <utils/String8.h>
#include <utils/threads.h>
#include <utils/List.h>
#include <utils/Errors.h>

#include <linux/input.h>

#define ABS_MT_TOUCH_MAJOR 0x30  /* Major axis of touching ellipse */
#define ABS_MT_TOUCH_MINOR 0x31  /* Minor axis (omit if circular) */
#define ABS_MT_WIDTH_MAJOR 0x32  /* Major axis of approaching ellipse */
#define ABS_MT_WIDTH_MINOR 0x33  /* Minor axis (omit if circular) */
#define ABS_MT_ORIENTATION 0x34  /* Ellipse orientation */
#define ABS_MT_POSITION_X 0x35   /* Center X ellipse position */
#define ABS_MT_POSITION_Y 0x36   /* Center Y ellipse position */
#define ABS_MT_TOOL_TYPE 0x37    /* Type of touching device (finger, pen, ...) */
#define ABS_MT_BLOB_ID 0x38      /* Group a set of packets as a blob */
#define ABS_MT_TRACKING_ID 0x39  /* Unique ID of initiated contact */
#define ABS_MT_PRESSURE 0x3a     /* Pressure on contact area */

class KeyLayoutMap;

using namespace android;


struct pollfd;

struct RawEvent {
    nsecs_t when;
    int32_t deviceId;
    int32_t type;
    int32_t scanCode;
    int32_t keyCode;
    int32_t value;
    uint32_t flags;
};

/*
 * Input device classes.
 */
enum {
    /* The input device is a keyboard. */
    INPUT_DEVICE_CLASS_KEYBOARD      = 0x00000001,

    /* The input device is an alpha-numeric keyboard (not just a dial pad). */
    INPUT_DEVICE_CLASS_ALPHAKEY      = 0x00000002,

    /* The input device is a touchscreen (either single-touch or multi-touch). */
    INPUT_DEVICE_CLASS_TOUCHSCREEN   = 0x00000004,

    /* The input device is a trackball. */
    INPUT_DEVICE_CLASS_TRACKBALL     = 0x00000008,

    /* The input device is a multi-touch touchscreen. */
    INPUT_DEVICE_CLASS_TOUCHSCREEN_MT= 0x00000010,

    /* The input device is a directional pad (implies keyboard, has DPAD keys). */
    INPUT_DEVICE_CLASS_DPAD          = 0x00000020,

    /* The input device is a gamepad (implies keyboard, has BUTTON keys). */
    INPUT_DEVICE_CLASS_GAMEPAD       = 0x00000040,

    /* The input device has switches. */
    INPUT_DEVICE_CLASS_SWITCH        = 0x00000080,
};

class ReadEventHub {
public:
	ReadEventHub();
	virtual ~ReadEventHub();
        //virtual bool getEvent(RawEvent*) = 0;
        bool getRawEvent(RawEvent*);
        void initRawEvent(RawEvent*);

        // Synthetic raw event type codes produced when devices are added or removed.
        enum {
        // Sent when a device is added.
        DEVICE_ADDED = 0x10000000,
        // Sent when a device is removed.
        DEVICE_REMOVED = 0x20000000,
        // Sent when all added/removed devices from the most recent scan have been reported.
        // This event is always sent at least once.
        FINISHED_DEVICE_SCAN = 0x30000000,
        };
private:
        bool mOpened;
        bool mNeedToSendFinishedDeviceScan;
        List<String8>   mExcludedDevices;

        int mFDCount;
        int mNumDevicesById;
        
        bool mHaveFirstKeyboard;
        int32_t mFirstKeyboardId; // the API is that the built-in keyboard is id 0, so map it

        struct device_t {
        const int32_t   id;
        const String8   path;
        String8         name;
        uint32_t        classes;
        uint8_t*        keyBitmask;
        KeyLayoutMap*   layoutMap;
        String8         keylayoutFilename;
        int             fd;
        device_t*       next;
        
        device_t(int32_t _id, const char* _path, const char* name);
        ~device_t();
        };

    device_t* getDeviceLocked(int32_t deviceId) const;
    bool hasKeycodeLocked(device_t* device, int keycode) const;
    
    int32_t getScanCodeStateLocked(device_t* device, int32_t scanCode) const;
    int32_t getKeyCodeStateLocked(device_t* device, int32_t keyCode) const;
    int32_t getSwitchStateLocked(device_t* device, int32_t sw) const;
    bool markSupportedKeyCodesLocked(device_t* device, size_t numCodes,
            const int32_t* keyCodes, uint8_t* outFlags) const;


        device_t *mOpeningDevices;
        device_t *mClosingDevices;
    
        device_t **mDevices;

        struct device_ent {
        device_t* device;
        uint32_t seq;
        };
        device_ent *mDevicesById;

        // Protect all internal state.
        mutable Mutex   mLock;

        struct pollfd *mFDs;
        bool openPlatformInput(void);
        int scanDir(const char *dirname);
        int openDevice(const char *deviceName);

    static const int INPUT_BUFFER_SIZE = 64;
    struct input_event mInputBufferData[INPUT_BUFFER_SIZE];
    int32_t mInputBufferIndex;
    int32_t mInputBufferCount;
    int32_t mInputDeviceIndex;
};

#endif

