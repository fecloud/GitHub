/*
 * uart.c
 *
 *  Created on: 2013年12月19日
 *      Author: braver
 */

#include "uart.h"

void uart_init(void)
{

	int baud = (F_CPU / 16 / BAUD) - 1;
	//设置波特率
	UBRRH = baud / 0xFF;
	UBRRL = baud % 0xFF;

	//使能发送和接收
	UCSRB = (1 << RXEN) | (1 << TXEN);

	//设置格式帧 8个数据位 1个停止位
	//(1 << USBS) |
	UCSRC = (1 << URSEL) |  (3 << UCSZ0);

}

void uart_send(unsigned char c)
{
	//等待发送缓冲为空
	while (!(UCSRA & (1 << UDRE)))
		;

	//将数据放入缓冲器,等待发送
	UDR = c;
}

void uart_send_chars(char * c)
{

	while (*c)
	{
		uart_send(*c);
		c++;
	}

}

unsigned char uart_receive()
{

	//等待接收数据
	while (!(UCSRA & (1 << RXC)))
		;
	//接收数据返回
	return UDR;

}

