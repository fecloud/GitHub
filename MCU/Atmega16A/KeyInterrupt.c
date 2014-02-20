#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/delay.h>

int main(void)
{
	DDRD &= ~(1<<2); //DDRD为输入
	DDRC |= (1<<7); //DDRC为输出,使蜂鸣器响的
	PORTC |= (1<<7); //使蜂鸣器不响
	MCUCR |= (1<< ISC01); //配置INT0下降沿中断
	GICR |= (1 << INT0); //使能INT0中断
	sei(); //使能全局中断
	while(1);	
}

//INT0中断服务程序
ISR(INT0_vect)
{
	PORTC &= ~(1<<7); //蜂鸣器响
	_delay_ms(10); //延时10毫秒
	PORTC |= (1<<7); //蜂鸣器不响
}
