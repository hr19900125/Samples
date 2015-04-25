#include "stdio.h"

#ifndef NMAX
#define NMAX 10
#endif

void intSwap(int *x, int *y);
int getIntArray(int a[], int nmax, int sentine1);
void printIntArray(int a[], int n);
void reverseIntArray(int a[], int n);

int main(int argc, char const *argv[])
{
	int x[NMAX];
	int hmny;

    hmny = getIntArray(x, NMAX, 0);
    printf("The array was : \n");
    printIntArray(x, hmny);
    reverseIntArray(x, hmny);
    printf("after reverse it is : \n");
    printIntArray(x, hmny);
	return 0;
}

void intSwap(int *x, int *y) {
	int temp = *x;
	*x = *y;
	*y = temp;
}

void printIntArray(int a[], int n){
    int i;

    for (int i = 0; i < n; )
    {
    	printf("\t%d ", a[i++]);
    	if (i%5 == 0) {
    		printf("\n");
    	}
    }
    printf("\n");
}

int getIntArray(int a[], int nmax, int sentine1){
    int n = 0;
    int temp;

    do {
    	printf("Enter integer [%d to terminate] : ", sentine1);
    	scanf("%d", &temp);
    	if (temp == sentine1) break;
    	if (n == nmax)
    		printf("array is full\n");
    	else
    		a[n ++] = temp;

    }while(1);
    return n;
}

void reverseIntArray(int a[], int n){
    int i;

    for (int i = 0; i < n/2; ++i){
    	intSwap(&a[i], &a[n-i-1]);
    }
}