/*
 * RuningLED.c
 *
 * Created: 2013-12-7 17:35:49
 *  Author: Administrator
 */ 

#include <avr/io.h>
#define F_CPU 16000000 // 晶振16Mhz
#include <util/delay.h>

#define  SLEEP_MS 100 //led流水灯亮和不亮时间 间隔
#define  LED_NUM 8 //LED灯个数

int main(void)
{
	DDRD |= 0xFF;
	PORTD = 0xFF;
	int i ;
	while(1)
	{

		//从0-7亮
		
		for (i = 0;i < LED_NUM; i++)
		{
			PORTD &= (~(1<<i));
			_delay_ms(SLEEP_MS);
		}

		//从7-0熄
		for (i --;i >=0 ; i--)
		{
			PORTD |= (1<<i);
			_delay_ms(SLEEP_MS);
		}
		
		//从7-0亮
		
		for (i = 7;i >= 0; i--)
		{
			PORTD &= (~(1<<i));
			_delay_ms(SLEEP_MS);
		}

		//从0-7熄
		for (i ++; i < LED_NUM ; i++)
		{
			PORTD |= (1<<i);
			_delay_ms(SLEEP_MS);
		}
		
		
	}
}