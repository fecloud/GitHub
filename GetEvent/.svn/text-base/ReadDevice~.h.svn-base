#ifndef _ReadDevice_H
#define _ReadDevice_H

#include <utils/threads.h>

#include "EventCallBack.h"
using namespace android;

class ReadDevice : public Thread
{
public:
	ReadDevice(char *filename, EventCallBack *eventCallBack);
	virtual ~ReadDevice(void);
	void start();
	void readDevice();
	void stop();

private:
	char *filename;
	bool flag;
	EventCallBack *eventCallBack;
	virtual bool threadLoop();
};

#endif
