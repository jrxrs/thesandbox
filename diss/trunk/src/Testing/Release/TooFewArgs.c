#include <stdio.h>

int add(int x, int y)
{
	return x + y;
}

int main(void)
{
	int a = 5, b = 9;

	printf("Sum = %d\n", add(a));

	return 0;
}
