#include "stdio.h"

int main(int argc, char const *argv[])
{
	int answer;
	short x = 1;
	long y = 2;
	float u = 3.0;
	double v = 4.4;
	long double w = 5.54;
	char c = 'p';

	printf("Date : %s\n", __DATE__);
	printf("Time : %s\n", __TIME__);
	printf("File : %s\n", __FILE__);
	printf("Line : %d\n", __LINE__);
	printf("Enter 1 or 0 : ");
	scanf("%d", &answer);

	printf("%s\n", answer?"You said YES":"You said NO");

	printf("The size of int %lu\n", sizeof(answer));
	printf("The size of short %lu\n", sizeof(x));
	printf("The size of long %lu\n", sizeof(y));
	printf("The size of float %lu\n", sizeof(u));
	printf("The size of double %lu\n", sizeof(v));
	printf("The size of long double %lu\n", sizeof(w));
	printf("The size of char %lu\n", sizeof(c));
	return 0;
}