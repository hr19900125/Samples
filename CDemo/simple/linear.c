#include "stdio.h"

#ifndef NMAX
#define NMAX 10
#endif

int getIntArray(int a[], int nmax, int sentinel);
void printIntArray(int a[], int n);
int linear(int a[], int n, int who);

int main(int argc, char const *argv[])
{
	int x[NMAX];
	int hmny;
	int who;
	int where;

	hmny = getIntArray(x, NMAX, 0);
	printf("The array was: \n");
	printIntArray(x, hmny);
	printf("Now we do linear searches on this data\n");
	do {
		printf("Enter integer to search for [0 to terminate] : \n");
		scanf("%d", &who);
		if (who == 0)break;
		where = linear(x, hmny, who);
		if (where < 0) 
			printf("Sorry, %d is not in the array\n", who);
	    else 
	    	printf("%d is at position %d\n", who, where);
	}while(1);
	return 0;
}

void printIntArray(int a[], int n){
    int i;

    for (i = 0; i<n; i ++){
    	printf("\t%d", a[i]);
    	if ((i+1)%5 == 0) {
    		printf("\n");
    	}
    }
    printf("\n");
}

int getIntArray(int a[], int nmax, int sentinel){
    int n = 0;
    int temp;

    do {
    	printf("Enter integer [%d to terminate] : ", sentinel);
    	scanf("%d" , &temp);
    	if (temp == sentinel) break;
    	if (n == nmax) printf("array is full\n");
    	else a[n ++] = temp;
    }while(1);
    return n;
}

int linear(int a[], int n, int who){
    int lcv;
    for (lcv = 0; lcv < n; lcv ++) {
    	if(who == a[lcv])return lcv;
    }
    return (-1);
}