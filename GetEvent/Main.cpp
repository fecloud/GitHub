#define LOG_TAG "GetEvent"

#include <utils/Log.h>

#include "EventMonitor.h"

int main()
{
	LOGD("Main Begin...");
	char *filename = "/dev/input/event0";
	EventMonitor *key = new EventMonitor;
	key->keyInit(filename);
	key->keyStart();
	int i = 0;
	while(true){
	  i++;
	}
	LOGD("Main End...");
	return 0;
}
