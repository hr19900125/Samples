#include "stdio.h"

void square1(void);

void square2(int i);

int square3(void);

int square4(int i);

int area(int b, int h);

int main(int argc, char const *argv[])
{
	square1();
	square2(7);

    printf("The value of square3() is %d\n", square3());

    printf("The value of square4(5) is %d\n", square4(5));

    printf("The value of area(3, 7) is %d\n", area(3, 7));

	return 0;
}

void square1(void){
    int x;

    printf("Please enter an integer > ");
    scanf("%d", &x);
    printf("The square of %d is %d\n", x, x*x);

}

void square2(int i) {
	printf("The square of %d is %d\n", i, i*i);
}

int square3(void) {
	int x;
	printf("Please enter an integer > ");
	scanf("%d", &x);
	return (x*x);
}

int square4(int i){
    return (i*i);
}

int area(int b, int h) {
	return (b*h);
}