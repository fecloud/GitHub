#define LOG_TAG "InputEventMonitor"

#include "LocalLog.h"

#include <android_runtime/AndroidRuntime.h>

#include "InputEventMonitor.h"
#include "EventCallBack.h"
#include "ReadDeviceThread.h"
#include "ReadEventHub.h"

static inline JNIEnv* jniEnv() {
	return android::AndroidRuntime::getJNIEnv();
}

InputEventMonitor::InputEventMonitor(JavaVM *mJavaVM, jobject callbacks,
		jmethodID mCallBackMethod) {
	JNIEnv *env = jniEnv();
	this->gJavaVM = mJavaVM;
	this->mCallbacksObj = env->NewGlobalRef(callbacks);
	this->callBackMethod = mCallBackMethod;
}

InputEventMonitor::InputEventMonitor() {

}

InputEventMonitor::~InputEventMonitor() {
	JNIEnv *env = jniEnv();
	env->DeleteGlobalRef(mCallbacksObj);
}

bool InputEventMonitor::callBack(InputEvent *mInputEvent) {
	LOGD("InputEventMonitor::callBack!\n");
	JNIEnv *env = jniEnv();

	bool isAttached = false;
	int status = -1;
	if (!isAttached && status < 0) {
		LOGE("callback_handler: failed to get JNI environment assuming native thread");
		status = gJavaVM->AttachCurrentThread(&env, NULL);
		if (status < 0) {
			LOGE("callback_handler: failed to attach current thread");
			return false;
		}
		isAttached = true;
	}

//	if (callBackMethod && mInputEvent) {
//		LOGE("callback_handler: failed to get method ID");
//		if (isAttached)
//			gJavaVM->DetachCurrentThread();
//		return false;
//	}

	env->CallBooleanMethod(mCallbacksObj, callBackMethod,
			mInputEvent->getDeviceId(), mInputEvent->getType(),
			mInputEvent->getScanCode(), mInputEvent->getKeyCode(),
			mInputEvent->getValue());

	if (isAttached) {
		gJavaVM->DetachCurrentThread();
		isAttached = false;
	}
	if (mInputEvent)
		delete mInputEvent;

	return true;
}

void InputEventMonitor::inputInit() {
	LOGD("InputEventMonitor::inputInit!\n");
	ReadEventHub* eventHub = new ReadEventHub();
	readDeviceThread = new ReadDeviceThread(eventHub, this);
	LOGD("InputEventMonitor::inputInit-->TRUE!\n");

}
bool InputEventMonitor::inputStart() {
	LOGD("InputEventMonitor::inputStart!\n");
	readDeviceThread->run("ReadDeviceThread", PRIORITY_URGENT_DISPLAY);
	return true;

}

bool InputEventMonitor::inputStop() {
	LOGD("InputEventMonitor::inputStop!\n");
	readDeviceThread->requestExitAndWait();
	readDeviceThread->flag = false;
	return true;

}

