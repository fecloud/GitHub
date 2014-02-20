#ifndef _InputEvent_H
#define _InputEvent_H

class InputEvent {
public:
	InputEvent(void);
	InputEvent(int, int, int, int, int);
	void setFieldValues(int, int, int, int, int);
	int getDeviceId();
	int getType();
	int getScanCode();
	int getKeyCode();
	int getValue();
	~InputEvent();

private:
	int deviceId;
	int type;
	int scanCode;
	int keyCode;
	int value;

};

#endif
