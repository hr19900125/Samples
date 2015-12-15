#include "stdio.h"

int main(int argc, char const *argv[])
{
	int a[2] = {1, 2};
	int b[2] = {2, 3};
    int i;

    for(i = 0; i < 2; i++) {
    	a[i] = b[i];
    }

    if (a == b) {
    	printf("They are equal\n");
    } else {
    	printf("They are not equal\n");
    }

    if (a == a) {
    	printf("Of course a is equal to a\n");
    } else {
    	printf("No, a is not equal to a\n");
    }

    for(i = 0; i < 2; i ++){
    	printf("a[%1d] = %3d\n", i, a[i]);
    }
	return 0;
}