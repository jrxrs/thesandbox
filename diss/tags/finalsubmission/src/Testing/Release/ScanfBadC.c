#include <stdio.h>

int main(void)
{
	int a;
	char b;
	float c;
	char d[10];
	printf("Enter an int char float:\n");
	scanf("%d %c %f %s", d, &c, &a, &b);
	printf("Entered: %d %c %f %s", a, b, c, d);

	return 0;
}
