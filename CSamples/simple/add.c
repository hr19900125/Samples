#include "stdio.h"

#ifndef SENTINEL
#define SENTINEL 0
#endif

int main(int argc, char const *argv[]){
	/* code */
	int sum = 0;
	int current;

	do {
		printf("\nEnter an integer > ");
		scanf("%d", &current);
		if (current > SENTINEL) {
			sum += current;
		}
	}while(current > SENTINEL);
	printf("\n The sum is %d\n", sum);
	return 0;
}