#include "stdio.h"

int main(int argc, char const *argv[])
{
	char c;
	int count;

	for(;;) {
		count = 0;
		printf("Please enter a line [blank line to terminate]> ");
		do {
			c = getchar();
			putchar(c);
			count ++;
		} while(c != '\n');
		if (count == 1)break;
	}
	return 0;
}