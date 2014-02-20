#ifndef _InputKeyEventMonitor_H
#define _InputKeyEventMonitor_H

#include "JNIHelp.h"
extern "C" {
	#include "jni.h"
}


#include "EventCallBack.h"
#include "ReadDevice.h"

static ReadDevice *keyReadDevice;

class InputEventMonitor : public EventCallBack
{
	public : 
		InputEventMonitor(jobject callbacks, jmethodID callBackMethod);
		InputEventMonitor();
		virtual ~InputEventMonitor(void);
		bool callBack(InputEvent *mInputEvent);
		bool inputInit(char *filename);
		bool inputStart();
		bool inputStop();
	private :
		jmethodID callBackMethod;
		jobject mCallbacksObj;
};

#endif
