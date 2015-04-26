#include "stdio.h"
#include <stdlib.h>

int * rpermute(int n){
	int * a = malloc(n*sizeof(int));
	int k;
	for (k = 0; k < n; k ++) {
		a[k] = k;
	}
	for (k = n - 1; k > 0; k --) {
		int j = rand() % (k+1);
		int temp = a[j];
		a[j] = a[k];
		a[k] = temp;
	}
	return a;
}

void printarray(int n, int a[n]) {
	int k = 0;
	for (k = 0; k < n; k ++) {
		printf("%6d     ", a[k]);
		if (k % 8 == 7) {
			printf("\n");
		}
	}
}

int main(int argc, char const *argv[])
{
	int limit = 6;
	int * a;
	int k;

	for (k = 0; k < 7; k ++) {
		a = rpermute(limit);
		printarray(limit, a);
		printf("\n");
	}
	return 0;
}
