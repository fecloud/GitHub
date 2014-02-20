#define LOG_TAG "ScreenCapture"
#include <stdio.h>
#include "jni.h"
#include "ScreenEvent.h"
#include <utils/Log.h>

static jint JNIScreenCapture
  (JNIEnv* env, jobject obj, jbyteArray arr, jint startPos, jint len)
{
	//LOGD("JNIScreenCapture");
  jint ret = 0;
  char* arrObj = NULL;
  char* jpgBuf = NULL;

  arrObj = (char*) env->GetByteArrayElements(arr, 0);
  jpgBuf = arrObj + startPos;

  ret = ScreenCapture(jpgBuf, len);

  env->ReleaseByteArrayElements(arr, (jbyte*)arrObj, 0);

  return ret;
}

static const char *classPathName = "com/braver/android/screen/capture/NativeScreenCapture";

static JNINativeMethod methods[] = {
  {"screenCapture", "([BII)I", (void*)JNIScreenCapture },
};

/*
 * Register several native methods for one class.
 */
static int registerNativeMethods(JNIEnv* env, const char* className,
    JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;

    clazz = env->FindClass(className);
    if (clazz == NULL) {
        LOGE("Native registration unable to find class '%s'", className);
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("RegisterNatives failed for '%s'", className);
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

/*
 * Register native methods for all classes we know about.
 *
 * returns JNI_TRUE on success.
 */
static int registerNatives(JNIEnv* env)
{
  if (!registerNativeMethods(env, classPathName,
                 methods, sizeof(methods) / sizeof(methods[0]))) {
    return JNI_FALSE;
  }

  return JNI_TRUE;
}


// ----------------------------------------------------------------------------

/*
 * This is called by the VM when the shared library is first loaded.
 */
 
typedef union {
    JNIEnv* env;
    void* venv;
} UnionJNIEnvToVoid;

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    UnionJNIEnvToVoid uenv;
    uenv.venv = NULL;
    jint result = -1;
    JNIEnv* env = NULL;
    
    LOGI("JNI_OnLoad");

    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_4) != JNI_OK) {
        LOGE("ERROR: GetEnv failed");
        goto bail;
    }
    env = uenv.env;

    if (registerNatives(env) != JNI_TRUE) {
        LOGE("ERROR: registerNatives failed");
        goto bail;
    }
    
    result = JNI_VERSION_1_4;
    
bail:
    return result;
}
