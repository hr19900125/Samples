#include "stdio.h"

int x = 2;
int y = 3;
int z = 4;

void moo(int x, int *y){
	int z;
    x = x + 3;
    *y = *y + 3;
    z = z + 3;
    printf("moo : x = %1d, *y = %1d, y = %p, z = %1d\n", x, *y, y, z);

}

int main(int argc, char const *argv[])
{
	moo(x, &y);
	printf("main : x = %1d, y = %1d, z = %1d\n", x, y, z);
	return 0;
}