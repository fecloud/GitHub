/*
 * uart.h
 * 同步模式UART
 *  Created on: 2013年12月19日
 *      Author: braver
 */

#ifndef UART_H_
#define UART_H_

#ifndef F_CPU //晶振频率
# warning "F_CPU not defined"
#endif

#ifndef BAUD
#define BAUD 9600 //波特率
#endif

#include <avr/io.h>

/**
 * 初始化UART
 */
void uart_init(void);

/**
 * 发送一位数据
 */
void uart_send(unsigned char);

/**
 *发送字符串
 */
void uart_send_chars(char *);

/**
 *接收一位数据
 */
unsigned char uart_receive();

#endif /* UART_H_ */
