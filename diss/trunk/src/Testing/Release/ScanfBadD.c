#include <stdio.h>

int main(void)
{
	int a;
	char b;
	float c;
	printf("Enter an int char float:\n");
	scanf("%d %c %f", a, b, c);
	printf("Entered: %d %c %f", a, b, c);

	return 0;
}
