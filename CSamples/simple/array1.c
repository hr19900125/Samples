#include "stdio.h"

#ifndef  N
#define N 10
#endif

void oneWay(void);
void anotherWay(void);

int main(int argc, char const *argv[])
{
	printf("\noneWay:\n");
	oneWay();
	printf("\nantherWay:\n");
	anotherWay();
	return 0;
}

void oneWay(void) {
	int vect[N] = {1,2,3,4,5,6,7,8,9,0};
    int i;

    for (i=0; i<N; i ++){
    	printf("i = %2d vect[i] = %2d\n", i, vect[i]);
    }
}

void anotherWay(void){
     int vect[N];
     int i;

     for(i=0; i<N; i++){
     	vect[i] = i + 1;
     }

     for(i=0; i<N; i++){
     	printf("i = %2d  vect[i] = %2d\n", i, vect[i]);
     }
}