/*
 * buzzer.h
 *
 *  Created on: 2013年12月23日
 *      Author: Feng OuYang
 */

#ifndef BUZZER_H_
#define BUZZER_H_

#include <avr/io.h>

#ifndef BUZZER_DDR
# warning "BUZZER_DDR not defined ,please set buzzer DDR?"
#define BUZZER_DDR DDRC
#endif

#ifndef BUZZER_PORT
# warning "BUZZER_PORT not defined ,please set buzzer PORT?"
#define BUZZER_PORT PORTC
#endif

/*蜂鸣器所在的引脚*/
#ifndef BUZZER_PIN
# warning "BUZZER_PIN not defined ,please set buzzer PIN?"
#define BUZZER_PIN 7
#endif

/**
 *蜂鸣器叫
 */
void buzzer_open();

/**
 * 蜂鸣器
 */
void buzzer_close();

#endif /* BUZZER_H_ */
