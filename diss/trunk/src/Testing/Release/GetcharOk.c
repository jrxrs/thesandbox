#include <stdio.h>

int main(void)
{
	char c;

	printf("Please enter a character: ");

	c = getchar();

	printf("\nYou entered: %c", c);

	return 0;
}
