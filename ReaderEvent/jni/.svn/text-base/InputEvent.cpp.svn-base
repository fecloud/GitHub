#define LOG_TAG "InputEvent"

#include "LocalLog.h"
#include "InputEvent.h"

InputEvent::InputEvent() {
	LOGD("New Default InputEvent!\n");
	this->deviceId = 0;
	this->type = 0;
	this->scanCode = 0;
	this->keyCode = 0;
	this->value = 0;
}

InputEvent::InputEvent(int mDeviceId, int mType, int mScanCode, int mKeyCode,
		int mValue) {
	LOGD("New InputEvent!\n");
	this->deviceId = mDeviceId;
	this->type = mType;
	this->scanCode = mScanCode;
	this->keyCode = mKeyCode;
	this->value = mValue;
}

void InputEvent::setFieldValues(int mDeviceId, int mType, int mScanCode,
		int mKeyCode, int mValue) {
	LOGD("set setFieldValues value!\n");
	this->deviceId = mDeviceId;
	this->type = mType;
	this->scanCode = mScanCode;
	this->keyCode = mKeyCode;
	this->value = mValue;
}

int InputEvent::getDeviceId() {
	return this->deviceId;
}

int InputEvent::getType() {
	return this->type;
}
int InputEvent::getScanCode() {
	return this->scanCode;
}
int InputEvent::getKeyCode() {
	return this->keyCode;
}
int InputEvent::getValue() {
	return this->value;
}

InputEvent::~InputEvent() {
	LOGD("Destory InputEvent!\n");
}

