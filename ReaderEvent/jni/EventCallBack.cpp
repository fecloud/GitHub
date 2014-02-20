#define LOG_TAG "EventCallBack"

#include "LocalLog.h"

#include "InputEvent.h"
#include "EventCallBack.h"

EventCallBack::EventCallBack() {
	LOGD("New EventCallBack!\n");
}

EventCallBack::~EventCallBack() {
	LOGD("Destory EventCallBack!\n");
}

bool EventCallBack::callBack(InputEvent *mInputEvent) {
	LOGD("callBack EventCallBack!\n");
	return true;
}

