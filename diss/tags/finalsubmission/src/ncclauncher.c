#include <malloc.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

/*****************************************************************************/
/*                                                                           */
/* FILE: ncclauncher.c                                                       */
/* VERSION: 2.0                                                              */
/* AUTHOR: Richard Hill                                                      */
/*                                                                           */
/* At present there are a maximum of seven commmand line arguments that can  */
/* be invoked on NCC at any one time. Because this is C the name of the      */
/* executable produced and called counts as the first argument which is why  */
/* argv[0] is ignored throughout most of the program and why the number of   */
/* arguments being matched in the if statements is always one more than it   */
/* looks like it should be.                                                  */
/*                                                                           */
/*****************************************************************************/

/*****************************************************************************/
/*                                                                           */
/* FUNCTION: joinMePlusSpace                                                 */
/*                                                                           */
/* This function joins two strings together and appends a handy space to the */
/* end of the newly joined string.                                           */
/* It then returns a pointer to the new string object.                       */
/*                                                                           */
/*****************************************************************************/
char *joinMePlusSpace(char *one, char *two)
{
	char *t1;
	char *t2;
	char *space = " ";
	t1 = (char *)calloc(strlen(one) + strlen(two) + 1, sizeof(char));
	strcpy(t1, one);
	strcat(t1, two);
	t2 = (char *)calloc(strlen(t1) + strlen(space) + 1, sizeof(char));
	strcpy(t2, t1);
	strcat(t2, space);
	free(t1);

	return t2;
}

/*****************************************************************************/
/*                                                                           */
/* FUNCTION: joinMe                                                          */
/*                                                                           */
/* This function joins two strings together but does not add a space to the  */
/* end of the new joined string.                                             */
/* It then returns a pointer to the new string object.                       */
/*                                                                           */
/*****************************************************************************/
char *joinMe(char *one, char *two)
{
	char *t1;
	t1 = (char *)calloc(strlen(one) + strlen(two) + 1, sizeof(char));
	strcpy(t1, one);
	strcat(t1, two);

	return t1;
}

/*****************************************************************************/
/*                                                                           */
/* FUNCTION: callocCopy                                                      */
/*                                                                           */
/* This function performs a safe calloc copy of one string to another.       */
/*                                                                           */
/*****************************************************************************/
char *callocCopy(char *one)
{
	char *t;
	t = (char *)calloc(strlen(one), sizeof(char));
	strcpy(t, one);
	
	return t;
}

/*****************************************************************************/
/*                                                                           */
/* FUNCTION: main                                                            */
/*                                                                           */
/* The main function for the ncc application, all it does it process the     */
/* command line arguments and invoke ncc with those same arguments, it       */
/* essentially wraps the java program into an executable.                    */
/*                                                                           */
/*****************************************************************************/
int main(int argc, char *argv[])
{
	char *c = "java ";
	char *ncc = "ncc";
	char *command;
	int counter = 0;
	//First job should be to remove the ./ from ncc if we're running on *nix
	argv[0] = ncc;
	if(argc < 9){
//		printf("%d args", argc);
		for(counter = 0; counter < argc-1; counter++){
			char *t;
//			printf(" %s", argv[counter]);
			t = joinMePlusSpace(c, argv[counter]);
			c = callocCopy(t);
			free(t);
		}
//		printf(" %s\n", argv[argc-1]);
		command = joinMe(c, argv[argc-1]);
	}
	else{
//		printf("Too many args!\n");
		command = "java ncc";
	}
	
	system(command);

	return 0;
}

