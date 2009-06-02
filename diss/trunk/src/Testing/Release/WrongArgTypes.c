#include <stdio.h>

int add(int x, int y)
{
	return x + y;
}

int main(void)
{
	float a = 0.05, b = 9.95, c = 8.08;

	printf("Sum = %d\n", add(a, b));

	return 0;
}
