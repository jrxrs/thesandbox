#include <stdio.h>

int add(int x, int y)
{
	return x + y;
}

int main(void)
{
	int a = 5, b = 9, c = 8;

	printf("Sum = %d\n", add(a, b, c));

	return 0;
}
