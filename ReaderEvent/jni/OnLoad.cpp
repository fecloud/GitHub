#include "InputEventMonitor.h"
extern "C" {
	#include "jni.h"
}

static const char *ipnutClassName = "com/aspire/mbre/agent/monitor/event/InputEventReader";
static const char *InputEventReaderCallBack = "com/aspire/mbre/agent/monitor/event/InputEventReader$CallBack";

static jmethodID callBackMethodID;
static InputEventMonitor *eventMonitor;
static JavaVM *gJavaVM;

//监控键盘初始化
JNIEXPORT jboolean JNICALL nativeInit(JNIEnv *env, jclass clazz, jobject callbacks)
{
	//查找回调方法
	eventMonitor = new InputEventMonitor(gJavaVM , callbacks , callBackMethodID);
	eventMonitor->inputInit();
	return JNI_TRUE;
}

 JNIEXPORT jboolean JNICALL nativeStart(JNIEnv *env, jclass clazz)
 {
	if(eventMonitor->inputStart())
		return JNI_TRUE;
	return JNI_FALSE;
 }

 JNIEXPORT jboolean JNICALL nativeStop(JNIEnv *env, jclass clazz)
 {
	 if(eventMonitor->inputStop())
		 return JNI_TRUE;
	return JNI_FALSE;
 }


 //绑定函数指针
static JNINativeMethod gMethods[] = {
	{"nativeInit","(Lcom/aspire/mbre/agent/monitor/event/InputEventReader$CallBack;)Z" , (void *)nativeInit},
	{"nativeStart","()Z" , (void *)nativeStart},
	{"nativeStop","()Z" , (void *)nativeStop}
};


JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved)
{
	gJavaVM = vm;
	JNIEnv *env;
	//
	if(vm->GetEnv((void**)&env , JNI_VERSION_1_4) != JNI_OK)
	{
		return JNI_ERR;
	}
	jclass claz = env->FindClass(ipnutClassName);
	jclass callBack = env->FindClass(InputEventReaderCallBack);

	env->RegisterNatives(claz,gMethods,sizeof(gMethods) /sizeof(gMethods[0]));

	//查找回调函数
	callBackMethodID = env->GetMethodID(callBack, "nativeCallBack", "(IIIII)Z");

	return JNI_VERSION_1_4;
}
