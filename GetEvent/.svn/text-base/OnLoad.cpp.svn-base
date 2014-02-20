#include "InputEventMonitor.h"
extern "C" {
	#include "jni.h"
}

static const char *keyClassName = "com/inspurworld/agent/monitor/event/KeyMonitor";
static const char *touchClassName = "com/inspurworld/agent/monitor/event/TouchMonitor";
static const char *inputMonitorCallBack = "com/inspurworld/agent/monitor/event/InputMonitor$CallBack";

static InputEventMonitor *keyEventMonitor;
static InputEventMonitor *touchEventMonitor;

static jmethodID callBackMethodID;

//监控键盘初始化
JNIEXPORT jboolean JNICALL nativeKeyInit(JNIEnv *env, jclass clazz, jstring filename, jobject callbacks)
{
	char *evtFileName = NULL;
    jint ret = 0;
    jclass clsString = env->FindClass("java/lang/String");
    jstring encode = env->NewStringUTF("utf-8");
    jmethodID getBytes = env->GetMethodID(clsString, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)env->CallObjectMethod(filename, getBytes, encode);
    jsize alen = env->GetArrayLength(barr);
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
    evtFileName = (char*) (ba);
	
	//查找回调方法
	keyEventMonitor = new InputEventMonitor(callbacks , callBackMethodID);
	keyEventMonitor->inputInit(evtFileName);
	return JNI_TRUE;
}
 
 JNIEXPORT jboolean JNICALL nativeKeyStart(JNIEnv *env, jclass clazz)
 {
	keyEventMonitor->inputStart();
	return JNI_TRUE;
 }
 
 JNIEXPORT jboolean JNICALL nativeKeyStop(JNIEnv *env, jclass clazz)
 {
	keyEventMonitor->inputStop();
	return JNI_TRUE;
 }
 
 //-------------------------------------------------------------------

////监控屏幕初始化
JNIEXPORT jboolean JNICALL nativeTouchInit(JNIEnv *env, jclass clazz , jstring filename, jobject callbacks)
{
	char *evtFileName = NULL;
    jint ret = 0;
    jclass clsString = env->FindClass("java/lang/String");
    jstring encode = env->NewStringUTF("utf-8");
    jmethodID getBytes = env->GetMethodID(clsString, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)env->CallObjectMethod(filename, getBytes, encode);
    jsize alen = env->GetArrayLength(barr);
    jbyte* ba = env->GetByteArrayElements(barr, JNI_FALSE);
    evtFileName = (char*) (ba);
	
	//查找回调方法
	touchEventMonitor = new InputEventMonitor(callbacks , callBackMethodID);
	touchEventMonitor->inputInit(evtFileName);
	return JNI_TRUE;
}

JNIEXPORT jboolean JNICALL nativeTouchStart(JNIEnv *env, jclass clazz)
 {
	touchEventMonitor->inputStart();
	return JNI_TRUE;
 }
 
 JNIEXPORT jboolean JNICALL nativeTouchStop(JNIEnv *env, jclass clazz)
 {
	touchEventMonitor->inputStop();
	return JNI_TRUE;
 }

 //绑定函数指针-key
static JNINativeMethod keyMethods[] = {
	{"nativeKeyInit","(Ljava/lang/String;Lcom/inspurworld/agent/monitor/event/InputMonitor$CallBack;)Z" , (void *)nativeKeyInit},
	{"nativeKeyStart","()Z" , (void *)nativeKeyStart},
	{"nativeKeyStop","()Z" , (void *)nativeKeyStop}
};

//绑定函数指针-touch
static JNINativeMethod touchMethods[] = {
	{"nativeTouchInit","(Ljava/lang/String;Lcom/inspurworld/agent/monitor/event/InputMonitor$CallBack;)Z" , (void *)nativeTouchInit},
	{"nativeTouchStart","()Z" , (void *)nativeTouchStart},
	{"nativeTouchStop","()Z" , (void *)nativeTouchStop}
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
	JNIEnv *env;
	//
	if(vm->GetEnv((void**)&env , JNI_VERSION_1_4) != JNI_OK)
	{
		return JNI_ERR;
	}
	jclass clazkey = env->FindClass(keyClassName);//
	jclass claztouch = env->FindClass(touchClassName);//
	jclass callBack = env->FindClass(inputMonitorCallBack);//

	env->RegisterNatives(clazkey,keyMethods,sizeof(keyMethods) /sizeof(keyMethods[0]));
	env->RegisterNatives(claztouch,touchMethods,sizeof(touchMethods) /sizeof(touchMethods[0]));

	//查找回调函数
	callBackMethodID = env->GetMethodID(callBack, "nativeCallBack", "(III)Z");

	return JNI_VERSION_1_4;
}
