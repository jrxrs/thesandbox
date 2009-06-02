#include <stdio.h>

void foo(int f)
{
	printf("%d\n", f);


int main(void)
{
	printf("Calling foo!\n");
	foo(5);
	return 0;
}
