/*
 * main.c
 *
 *  Created on: 2013年12月18日
 *      Author: braver
 */

#include <avr/io.h>
#include <util/delay.h>
#include "interrupt_uart.h"
#include "execute.h"

static int index;
static uchar cmds[2];

int main(void)
{

	interrupt_uart_init();
	sei();

	while (1)
		;
}
void on_uart_recevie(unsigned char c)
{
	if (index >= 0)
	{
		cmds[index] = c;
		index++;
	}
	if (index == 5)
	{
		//uart_send(dispath(&c));
		uart_send(0x32);
		index = 0;
	}


}

