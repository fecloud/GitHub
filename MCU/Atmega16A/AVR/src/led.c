/*
 * led.c
 *
 *  Created on: 2013年12月22日
 *      Author: braver
 */

#include "led.h"

void led_open(int number)
{
	LED_DDR |= 1 << number;
	LED_PORT &= ~(1 << number);
}

void led_close(int number)
{
	LED_DDR &= ~(1 << number);
	LED_PORT |= 1 << number;
}
