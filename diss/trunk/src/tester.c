#include <stdio.h>

int global = 0;

int function(float y)
{
	return 6;
}

int function(int x, char y)
{
	int day, year;
	char monthname[20], *p;
	
	p = monthname;
	
	if(day == 0){
		//
	}
	else if(day == 9){
		//
	}
	else{
		//
	}
		
//	getchar();
//	putchar('f');
	scanf("%d %s %d %s", &day, monthname, &year, p);
//	printf("Day: %d, Month: %s, Year: %d, %s", day, monthname, year, p);
	
//	u = 0;
	
	return 0;
}

int xyz(int array[], int size)
{
	return -1;
}

int main(void)
{
	int x = 7;
	char y = 'v';
	function(x, y);
	printf("The value of x is %d\n", x);

	return 0
}
