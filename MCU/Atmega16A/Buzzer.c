/*
 * Buzzer.c
 *
 * Created: 2013-12-7 17:14:34
 *  Author: ouyangfeng
 */ 
#include <avr/io.h>
#define  F_CPU 16000000
#include <util/delay.h>

#define TRUE 1
#define FALSE 0
#define BOOLEAN int

int main(void)
{
	DDRC |= 0x80; //DDR7为输出
	PORTC |= 0x80; //高电平
	BOOLEAN con = TRUE; 
	while(1)
	{
		if(con)
		{	
			PORTC &= ~(0x80); //低电平时,响
			con = FALSE;
		}
		else
		{
			PORTC |= 0x80;
			con = TRUE;
		}
		_delay_ms(1000);

	}
}