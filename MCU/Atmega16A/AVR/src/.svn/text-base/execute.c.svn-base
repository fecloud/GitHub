/*
 * execute.c
 *
 *  Created on: 2013年12月23日
 *      Author: braver
 */

#include "execute.h"

/**
 *led处理
 */
uchar dispath_led(uchar c)
{
	int i;
	int j;
	for (i = 0; i < 8; ++i)
	{
		j = (1 << i);

		//该led开
		if (c & j)
		{
			led_open(i);
		} else //该led关
		{
			led_close(i);
		}
	}
	return TRUE;
}

/**
 * 处理蜂鸣器
 */
uchar dispath_buzzer(uchar c)
{
	if (c)
	{
		buzzer_open();
	} else
	{
		buzzer_close();
	}
	return TRUE;
}

uchar dispath(uchar *command)
{
	int i = ((int) *command);
	uchar result = FLASE
	;
	switch (i)
	{
	case 1:
		result = dispath_led(*command++);
		break;

	case 2:
		result = dispath_buzzer(*command++);
		break;
	default:
		break;

	}
	return result;
}

