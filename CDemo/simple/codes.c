#include "stdio.h"

int main(int argc, char const *argv[])
{
	int c;

	printf("\tCharacter Code\n"
		   "\t==============\n");

	for (c = 32; c < 127; c ++) {
		printf("\t  %c      %4d\n", c, c);
	}
	return 0;
}