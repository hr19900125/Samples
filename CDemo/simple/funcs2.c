#include "stdio.h"

int getint(void);

int getmax(int a, int b, int c);

int main(int argc, char const *argv[])
{
	int x, y, z;

	x = getint();
	y = getint();
	z = getint();
	printf("The largest of %d, %d, and %d is %d\n", x, y, z, getmax(x, y, z));
	return 0;
}

int getint(void) {
	int a;

	printf("Please enter an integer > ");
	scanf("%d", &a);
	return a;
}

int getmax(int a, int b, int c){
    int m = a;

    if(m < b) 
    	m = b;
    if(m < c)
    	m = c;
    return m;
}