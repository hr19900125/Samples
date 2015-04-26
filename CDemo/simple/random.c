#include "stdio.h"
#include <unistd.h>
#include <stdlib.h>

static long seed = 13;
static long a;
static long c;
static long m;

void random_init(long s){
	if (s != 0) seed = s;
}

long random() {
	seed = (a*seed + c) % m;
	return seed;
}

int main(int argc, char const *argv[])
{
	if(argc != 5) {
		printf("usage: %s a, c, m, seed\n", argv[0]);
		return 1;
	}
	a = atoi(argv[1]);
	c = atoi(argv[2]);
	m = atoi(argv[3]);
	long s = atoi(argv[4]);
	random_init(s);
	int k;
	for (k = 0; k < m-1; k ++) {
		printf("%8ld", random());
		if (k % 8 == 7) {
			printf("\n");
			sleep(1);
		}
	}
	printf("\n");
	return 0;
}
