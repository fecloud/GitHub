#ifndef _InputEvent_H
#define _InputEvent_H

class InputEvent {
public:
	InputEvent(void);
	InputEvent(int, int, int);
	void setFieldValues(int, int, int);
        int getType();
        int getCode();
        int getValue();
	~InputEvent();

private:
	int type;
	int code;
	int value;

};

#endif
