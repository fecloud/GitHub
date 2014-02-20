/*
 * interrupt_uart.c
 *
 *  Created on: 2013年12月19日
 *      Author: braver
 */
#include "interrupt_uart.h"

void interrupt_uart_init(void)
{
	uart_init();

	//使能接收,发送完成中断
	UCSRB |= (1 << RXCIE) | (1 << TXCIE);
}

/**
 * UART接收中断服务程序
 */
ISR(USART_RXC_vect)
{
	on_uart_recevie(UDR);
}

/**
 *UAST发送中断服务程序
 */
ISR(USART_TXC_vect)
{

}
