#define LOG_TAG "Main"

#include <utils/Log.h>
#include <utils/threads.h>
#include "MyThread.h"

using namespace android;

int main()
{
	sp<MyThread>  thread = new MyThread;//此处一定要这么写
	thread->run("MyThread", PRIORITY_URGENT_DISPLAY);
	while(1);
	LOGD("main end");
	return 0;
}
