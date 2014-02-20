#define LOG_TAG "GetEvent"

#include "LocalLog.h"
#include "InputEventMonitor.h"
#include "ReadDeviceThread.h"
#include "ReadEventHub.h"

int main()
{
	LOGD("Main Begin...");
	//char *filename = "/dev/input/event0";
	InputEventMonitor *key = new InputEventMonitor;
	key->inputInit();
	key->inputStart();
        /*
        ReadEventHub* eventHub = new ReadEventHub();
        sp<ReadDeviceThread> readDeviceThread = new ReadDeviceThread(eventHub);
        readDeviceThread->run("EventThread", PRIORITY_URGENT_DISPLAY);
        */
	int i = 0;
	while(true){
	  i++;
	}
	LOGD("Main End...");
	return 0;
}
