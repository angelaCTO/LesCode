#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* ascii   = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*(){}[]|:;'<>,.?/";

int main(void) {
	int i;
	for (i=0; i < strlen(ascii); i++) {
		printf("Printing character %d: %c\n", i, ascii[i]);
	}
	return 0;		
}
