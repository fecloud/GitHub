#ifndef _MYTHREAD_H
#define _MYTHREAD_H
extern "C" {
#include "jni.h"
}
#include <utils/threads.h>
#include "EventCallBack.h"
#include "ReadEventHub.h"

using namespace android;

class ReadDeviceThread: public Thread {
public:
	//ReadDevice(char *filename, EventCallBack *eventCallBack);
	ReadEventHub* eventHub;
	ReadDeviceThread(ReadEventHub*, EventCallBack *);
	virtual ~ReadDeviceThread();
	//void start();
	//void stop();
	bool flag;

private:
	//char *filename;
	//bool flag;
	virtual status_t readyToRun();
	virtual bool loopOnce();
	virtual bool threadLoop();
	EventCallBack *eventCallBack;

};

#endif
