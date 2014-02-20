#define LOG_TAG "ReadDevice"

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <linux/input.h>
#include <utils/Log.h>

#include "ReadDevice.h"
#include "InputEvent.h"

static int event_fd = -1;
struct input_event ev0[64];

ReadDevice::ReadDevice(char *filename, EventCallBack *eventCallBack) : Thread(false) {
	LOGD("ReadDevice::ReadDevice()--> %s", filename);

	this->filename = filename;
	this->eventCallBack = eventCallBack;
}

ReadDevice::~ReadDevice() {
}

void ReadDevice::start() {
	LOGD("ReadDevice::start()");

	this->flag = true;
	readDevice();
}

void ReadDevice::readDevice() {
	LOGD("ReadDevice::readDevice()");

	//printf("check filename %s\n",this->filename);
	event_fd = open(this->filename, O_RDWR);

	if (event_fd < 0)
	{
		LOGD("Open Failed\n");
		return;
	}

	LOGD("Check flag %d\n",this->flag);
	InputEvent *inputEvent = NULL;
	if(inputEvent == NULL)
	{
		inputEvent = new InputEvent();
	}

	while (this->flag) {
		int i, rd;
		rd = read(event_fd, ev0, sizeof(input_event) * 64);

		LOGD("Check rd and input_event %d,%d\n",rd,sizeof(input_event));
		if (rd < sizeof(input_event))
		{
			LOGD("rd too small , return");
			return;
		}

		for (i = 0; i < rd / sizeof(input_event); i++) {
			LOGD("Event we get,type=%d,code=%d,value=%d", ev0[i].type, ev0[i].code, ev0[i].value);

			/*InputEvent *inputEvent = new InputEvent(ev0[i].type, ev0[i].code,
					ev0[i].value);*/

			inputEvent->setFieldValues(ev0[i].type, ev0[i].code, ev0[i].value);

			eventCallBack->callBack(inputEvent);
		}

	}

}

void ReadDevice::stop() {
	LOGD("ReadDevice::stop()");
	this->flag = false;

	if (event_fd > 0) {
		close(event_fd);
		event_fd = -1;
	}
}

bool ReadDevice::threadLoop()
{
	LOGD("ReadDevice::threadLoop()");
	start();
	return false;
}
