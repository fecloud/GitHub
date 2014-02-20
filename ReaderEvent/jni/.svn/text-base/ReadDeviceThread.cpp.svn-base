#define LOG_TAG "ReadDeviceThread"

#include "ReadEventHub.h"
#include "ReadDeviceThread.h"
#include "LocalLog.h"

using namespace android;

ReadDeviceThread::ReadDeviceThread(ReadEventHub* hub,
		EventCallBack *mEventCallBack) :
		Thread(true) {
	LOGD("New ReadDeviceThread\n");
	this->eventHub = hub;
	this->eventCallBack = mEventCallBack;
	this->flag = false;
}

ReadDeviceThread::~ReadDeviceThread() {
	LOGD("Destory ReadDeviceThread\n");
	if(eventHub)
		delete eventHub;
}

status_t ReadDeviceThread::readyToRun() {
	flag = true;
	return Thread::readyToRun();
}
bool ReadDeviceThread::threadLoop() {
	LOGD("ReadDeviceThread threadLoop\n");
	return loopOnce();;
}

bool ReadDeviceThread::loopOnce() {
	RawEvent outEvent;
	LOGD("ReadDeviceThread loopOnce\n");
	eventHub->getRawEvent(&outEvent);
	//LOGD("Input event: device=0x%x type=0x%x scancode=%d keycode=%d value=%d", outEvent.deviceId, outEvent.type, outEvent.scanCode, outEvent.keyCode, outEvent.value);
	if (eventCallBack) {
		InputEvent *event = new InputEvent(outEvent.deviceId, outEvent.type,
				outEvent.scanCode, outEvent.keyCode, outEvent.value);
		eventCallBack->callBack(event);
	}
	return flag;
}

