#include "stdio.h"

int main(int argc, char const *argv[])
{
	
	int pennies;
	int nickels;
	int dimes;
	int quarters;
	int temp, left;

	printf("Enter the number of quarters, dimes, nickels, and pennies: \n");
	scanf("%d %d %d %d", &quarters, &dimes, &nickels, &pennies);

	left = 25 * quarters + 10 * dimes + 5 * nickels + pennies;

	printf("Your collection is worth\n");
	temp = left / 100;
	printf("\t%d dollar", temp);
	if (temp == 1)
		printf(", ");
	else 
		printf("s, ");
	left = left % 100;

	temp = left / 25;
	printf("%d quarter", temp);
	if (temp == 1)
		printf(", ");
	else 
		printf("s, ");
	left = left % 25;

	temp = left / 10;
	printf("%d dime", temp);

	printf((temp == 1)?", ":"s, ");
	left = left % 10;

	temp = left / 5;
	printf("%d nickel", temp);
	if (temp == 1) 
		printf(", and ");
	else 
		printf("s, and ");
	left = left % 5;

	printf("%d penn", left);
	if (left == 1)
		printf("y\n");
	else
		printf("ies\n");

	return 0;
}