#include "stdio.h"

int a = 0;

void foo(void);

int main(int argc, char const *argv[])
{
	int a = 2;
	int b = 3;

	printf("1. main_b = %d\n", b);
	printf("main_a = %d\n", a);
    foo();
    printf("2. main_b = %d\n", b);

	return 0;
}

void foo(void){
    int b = 4;

    printf("foo_a = %d\n", a);
    printf("foo_b = %d\n", b);
}