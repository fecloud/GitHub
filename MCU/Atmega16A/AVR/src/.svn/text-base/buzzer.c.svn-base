/*
 * buzzer.c
 *
 *  Created on: 2013年12月23日
 *      Author: Feng OuYang
 */
#include "buzzer.h"

void buzzer_open() {
	BUZZER_DDR |= 1 << BUZZER_PIN;
	BUZZER_PORT &= ~(1 << BUZZER_PIN);
}
void buzzer_close() {
	BUZZER_DDR &= ~(1 << BUZZER_PIN);
	BUZZER_PORT |= 1 << BUZZER_PIN;
}
