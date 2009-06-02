#include <stdio.h>

int globalVariable = 7;

void functionA(int fParamOne, char fParamTwo)
{
	int var1 = 1;
	int var2 = 2;
	int var3 = 3;

	printf("\tScope Level 1:\n");
	printf("\tvar1 = %d\n\tvar2 = %d\n\tvar3 = %d\n", var1, var2, var3);

	//Open new level of scope
	{
		int var1 = 4;
		int var2 = 5;
		int var3 = 6;

		printf("\t\tScope Level 2:\n");
		printf("\t\tvar1 = %d\n\t\tvar2 = %d\n\t\tvar3 = %d\n", var1, var2, var3);

		//Open another new level of scope
		{
			int var4 = 7;
			int var5 = 8;
			int var6 = 9;
			int globalVariable = 0;

			printf("\t\t\tScope Level 3:\n");
			printf("\t\t\tvar1 = %d\n\t\t\tvar2 = %d\n\t\t\tvar3 = %d\n", var1, var2, var3);
			printf("\t\t\tvar4 = %d\n\t\t\tvar5 = %d\n\t\t\tvar6 = %d\n", var4, var5, var6);
			printf("\t\t\tfParamOne = %d\n\t\t\tfParamTwo = %c\n", fParamOne, fParamTwo);
			printf("\t\t\tglobalVariable = %d\n", globalVariable);
		}
	}

	printf("\tDouble check original level 0 & 1 variables:\n");
	printf("\tvar1 = %d\n\tvar2 = %d\n\tvar3 = %d\n", var1, var2, var3);
	printf("\tglobalVariable = %d\n", globalVariable);
}

int main(void)
{
	printf("Global Scope (Level 0):\n");
	printf("globalVariable = %d\n", globalVariable);
	functionA(11111, 'R');

	return 0;
}
