#include <stdio.h>

int torf(int x)
{
	int y = x
	int z = y + x;
	if(x == 1){
		return 1;
	}
	else{
		return 0
	}
}

int main(int argc, char *argv[])
{
	if(argc != 1){
		printf("Illegal Command Line Argument\n");
	}
	else{
		if(torf((int) argv[0])){
			printf("TRUE\n")
			printf("2\n");
		}
		else{
			printf("FALSE\n")
		}
	}

	return 0;
}
