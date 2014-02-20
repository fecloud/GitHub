#ifndef _InputEventMonitor_H
#define _InputEventMonitor_H

extern "C" {
#include "jni.h"
}

#include "EventCallBack.h"
#include "ReadEventHub.h"
#include "ReadDeviceThread.h"

class InputEventMonitor: public EventCallBack {

public:
	InputEventMonitor(JavaVM*, jobject, jmethodID);
	InputEventMonitor();
	virtual ~InputEventMonitor(void);
	bool callBack(InputEvent *mInputEvent);
	void inputInit();
	bool inputStart();
	bool inputStop();
private:
	sp<ReadDeviceThread> readDeviceThread;
	jmethodID callBackMethod;
	jobject mCallbacksObj;
	JavaVM *gJavaVM;

};

#endif
