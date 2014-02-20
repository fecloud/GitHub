/*
 * led.h
 *
 *  Created on: 2013年12月22日
 *      Author: braver
 */

#ifndef LED_H_
#define LED_H_

#include <avr/io.h>

#ifndef LED_DDR
# warning "LED_DDR not defined ,please set led DDR?"
#define LED_DDR DDRD
#endif

#ifndef LED_PORT
# warning "LED_PORT not defined ,please set led PORT?"
#define LED_PORT PORTD
#endif

/**
 * 哪个led亮
 */
void led_open(int number);

/**
 * 哪个led熄灭
 */
void led_close(int number);

#endif /* LED_H_ */
