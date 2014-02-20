/*
 * excute.h
 *
 *  Created on: 2013年12月23日
 *      Author: braver
 */

#ifndef EXECUTE_H_
#define EXECUTE_H_

#define LED_DDR DDRD
#define LED_PORT PORTD;

#define BUZZER_DDR DDRC
#define BUZZER_PORT PORTC
#define BUZZER_PIN PINC7

#include "interrupt_uart.h"
#include "led.h"
#include "buzzer.h"

#define LED 0x1#;
#define BUZZER 0x2#;

#define TRUE 0x1;
#define FLASE 0x0;

#define uchar unsigned char


uchar dispath_led(uchar c);

uchar dispath_buzzer(uchar c);

uchar dispath_command(uchar *command);

#endif /* EXECUTE_H_ */
