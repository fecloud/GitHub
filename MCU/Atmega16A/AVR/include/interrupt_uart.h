/*
 * interrupt_uart.h
 * 中断模式UART
 *  Created on: 2013年12月19日
 *      Author: braver
 *
 */

#ifndef INTERRUPT_UART_H_
#define INTERRUPT_UART_H_

#include <avr/interrupt.h>

#include "uart.h"

/**
 *中断模式UART初始化
 */
void interrupt_uart_init(void);

/**
 * 非阻塞发送
 */
void interrupt_uart_send(unsigned char c);

extern void on_uart_recevie(unsigned char c);

#endif /* INTERRUPT_UART_H_ */
