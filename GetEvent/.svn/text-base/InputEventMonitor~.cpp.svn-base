#define LOG_TAG "InputEventMonitor"

#include <utils/Log.h>
#include <android_runtime/AndroidRuntime.h>

#include "InputEventMonitor.h"
#include "EventCallBack.h"
#include "ReadDevice.h"

static inline JNIEnv* jniEnv() {
        return android::AndroidRuntime::getJNIEnv();
}

InputEventMonitor::InputEventMonitor(jobject callbacks, jmethodID callBackMethod)
{
	JNIEnv *env = jniEnv();
	this->mCallbacksObj = env->NewGlobalRef(callbacks);
	this->callBackMethod = callBackMethod;
}

InputEventMonitor::InputEventMonitor()
{

}

InputEventMonitor::~InputEventMonitor()
{
	JNIEnv *env = jniEnv();
	env->DeleteGlobalRef(mCallbacksObj);
}

bool InputEventMonitor::callBack(InputEvent *mInputEvent)
{
	LOGD("InputEventMonitor::callBack!\n");
	JNIEnv *env = jniEnv();
	env->CallObjectMethod(mCallbacksObj, callBackMethod, mInputEvent->getType(), mInputEvent->getCode(), mInputEvent->getValue());
	return true;
}

bool InputEventMonitor::inputInit(char *filename)
{
	LOGD("InputEventMonitor::inputInit!\n");
	keyReadDevice = new ReadDevice(filename , this);
	LOGD("InputEventMonitor::inputInit-->TRUE!\n");
	return true;
}
bool InputEventMonitor::inputStart()
{
	LOGD("InputEventMonitor::inputStart!\n");
	status_t result = keyReadDevice->run("KeyEventMonitor", PRIORITY_URGENT_DISPLAY);
	if(result)
	{
		LOGD("InputEventMonitor::inputStart-->TRUE!\n");
		return true;
	}
	LOGD("InputEventMonitor::inputStart-->False!\n");
	return false;
	
}

bool InputEventMonitor::inputStop()
{
	LOGD("InputEventMonitor::inputStart!\n");
	keyReadDevice->stop();
	keyReadDevice->requestExitAndWait();
	return true;
}




