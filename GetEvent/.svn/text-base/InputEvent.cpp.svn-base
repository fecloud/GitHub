#define LOG_TAG "InputEvent"

#include "LocalLog.h"
#include "InputEvent.h"

InputEvent::InputEvent()
{
 LOGD("New Default InputEvent!\n");
 this->type = 0;
 this->code = 0;
 this->value = 0;
}


InputEvent::InputEvent(int mtype, int mcode, int mvalue) 
{
	LOGD("New InputEvent!\n");
	this->type = mtype;
	this->code = mcode;
	this->value = mvalue;
}

void InputEvent::setFieldValues(int mtype, int mcode, int mvalue) {
	LOGD("set setFieldValues value!\n");
	this->type = mtype;
	this->code = mcode;
	this->value = mvalue;
}

int InputEvent::getType()
{
    return this->type;
}

int InputEvent::getCode()
{
    return this->code;
}

int InputEvent::getValue()
{
    return this->value;
}

InputEvent::~InputEvent() {
	LOGD("Destory InputEvent!\n");
}

