#include "stdio.h"

int main(int argc, char const *argv[])
{
	int n;
	int i;
	int current;
	int next;
	int twoaway;

	printf("How many Fibonacci numbers do you want to compute? \n");
	scanf("%d", &n);
	if (n <= 0) 
		printf("The number should be positive.\n");
	else {
		printf("\n\n\tI \t Fibonacci(I) \n\t=============\n");
		next = current = 1;
		for (i=1; i<n; i++){
			printf("\t%d \t   %d\n", i, current);
			twoaway = current + next;
			current = next;
			next = twoaway;
		}

	}
	return 0;
}