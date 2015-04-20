#include <stdio.h>
#ifndef N
#define N 16
#endif

int main(int argc, char const *argv[])
{
	/* code */
	int val = 1;
    
    printf("\t n \t 2^n\n");
    for (int i=1; i <= N; i ++) {
    	val *= 2;
    	printf("\t %d \t %d\n", i, val);
    	
    }
	return 0;
}