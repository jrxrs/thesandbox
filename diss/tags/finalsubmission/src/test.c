/*******************************************************************/
/*																   */
/*	Title:		ADT Stack										   */
/*	File name:	stack.c											   */
/*	Date:		04/04/06										   */
/*	Course:		CM10138 Systems 2: C Programming				   */
/*	Author: 	Richard Hill									   */
/*	Email:  	rgh21@bath.ac.uk								   */
/*	References:	"A Book on C - Programming in C", 4th Edition	   */
/*				by Al Kelley & Ira Pohl, ISBN 0-201-18399-4		   */
/*				http://www.shocknet.org.uk/defpage.asp?pageID=94   */
/*																   */
/*******************************************************************/

/* Standard C includes */
#include <stdio.h>
#include <malloc.h>

/* Include the stack header file */
/*******************************************************************/
/*																   */
/*	Title:		ADT Stack										   */
/*	File name:	stack.h											   */
/*	Date:		04/04/06										   */
/*	Course:		CM10138 Systems 2: C Programming				   */
/*	Author: 	Richard Hill									   */
/*	Email:  	rgh21@bath.ac.uk								   */
/*	References:	"A Book on C - Programming in C", 4th Edition	   */
/*				by Al Kelley & Ira Pohl, ISBN 0-201-18399-4		   */
/*				http://www.shocknet.org.uk/defpage.asp?pageID=94   */
/*																   */
/*******************************************************************/

#ifndef STACK_H
#define STACK_H

/* Hide data from the user of this library */
typedef struct _top* stack;

/* Declare prototype functions, to be found in stack.c file */
extern stack init(void);
extern int push(stack stk, int c);
extern int pop(stack stk, int *val);
extern int getTop(stack stk, int *val);
extern int empty(stack stk);
extern int destroy(stack stk);
extern int size(stack stk);
extern int a2s(stack stk, int *pa, int m);

#endif

/* Define structure for a stack node */
typedef struct _node {
	struct _node *next;
	int data;
} node;

/* Define structure for a stack */
typedef struct _top {
	struct _node *top;
} _top;

/* FUNCTION: init */
/* Initializes a stack, returns a pointer to the new stack */
stack init(void)
{
	stack stk = (stack) malloc(sizeof(_top));
	if(stk == NULL){
		/* malloc failed */
		return NULL;
	}
	/* Set stack top NULL */
	stk->top = NULL;
	return stk;
}

/* FUNCTION: push */
/* Places a value on the stack, returns 0 if successful, 1 otherwise */
int push(stack stk, int c)
{
	if(stk == NULL){
		return 1;
	}
	if(stk->top == NULL){
		node *n = (node*) malloc(sizeof(node));
		if(n == NULL){
			/* Malloc failed */
			return 1;
		}
		n->next = NULL;
		n->data = c;
		stk->top = n;
		return 0;
	}
	else{
		node *n = (node*) malloc(sizeof(node));

		/* Check malloc did not fail */
		if(n == NULL){
			return 1;
		}

		n->next = stk->top;
		n->data = c;
		stk->top = n;

		return 0;
	}
}

/* FUNCTION: pop */
/* Retrieves and destroys a value from the top of the stack, storing it's
 * value in the memory pointed to by val.
 * Returns a 1 if an error occurs, returns 0 if successful. */
int pop(stack stk, int *val)
{
	node *n; /* temp node */
	if( (stk == NULL) || (stk->top == NULL) ){
		return 1;
	}
	/* Get the top of the stack */
	n = stk->top;
	*val = n->data;

	stk->top = n->next;
	free(n); /* free the temp node */
	return 0;
}

/* FUNCTION: getTop */
/* Stores the value at the top of the stack in the memory pointed to
 * by the pointer val. Returns 0 if successful, 1 otherwise. */
int getTop(stack stk, int *val)
{
	if( (stk == NULL) || (stk->top == NULL) ){
		return 1;
	}

	*val = stk->top->data;
	return 0;
}

/* FUNCTION: empty */
/* Returns 1 if the stack is empty, returns 0 otherwise */
int empty(stack stk)
{
	if(stk == NULL){
		return 1;
	}
	return (stk->top == NULL);
}

/* FUNCTION: destroy */
/* Returns 0 when complete, 1 if failed. */
int destroy(stack stk)
{
	node *tbf, *tmp; /* temp stack nodes */
	if(stk == NULL){
		return 1;
	}
	tmp = stk->top;
	while(tmp != NULL)
	{
		tbf = tmp;
		tmp = tmp->next;

		/* Free the last stack node struct */
		free(tbf);
	}

	stk->top = NULL;

	return 0;
}

/* FUNCTION: size */
/* Returns the number of stack nodes currently in the stack pointed to
 * by t. */
int size(stack stk)
{
	int q = 0;
	node *temp;
	if(stk == NULL){
		return 0;
	}
	temp = stk->top;

	if(temp == NULL){
		return 0; /* Empty stack! */
	}
	else{
		do{
			q++;
			temp = temp->next;
		} while(temp != NULL);
	}
	return q;
}

/* FUNCTION: a2s */
/* This function allows the user input an existing array into a stack,
 * it returns 0 if the operation is successful. */
int a2s(stack stk, int *pa, int m)
{
	int i;
	if(stk == NULL){
		return 1;
	}

	for(i = 0; i < m; i++){
		if(! push(stk, *(pa + i))){
			/* Push success, carry on */
		}
		else{
			/* Push failed, possible malloc error */
			printf("Malloc Error?!\n");
			break;
		}
	}
	return 0;
}
