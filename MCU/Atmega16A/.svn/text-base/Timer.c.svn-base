#include <avr/io.h>
#include <avr/interrupt.h>

int main(void)
{
	DDRD = 0xFF;
	PORTD = 0xFF;
	
	TCNT0 = 55;//计数200次 8位的
	TCCR0 |= (1 << CS01); //设置时钟分频 当前为8分频
	TIMSK |= (1 << TOIE0); //使能中断

	sei();
	while(1);
}

volatile unsigned int i = 0;

ISR(TIMER0_OVF_vect)
{
	TCNT0 = 55;//计数200次 8位的
	i++;
	
	if(i > 10000)
	{
		PORTD ^= 0xFF;
		i=0;
	}
	
}
