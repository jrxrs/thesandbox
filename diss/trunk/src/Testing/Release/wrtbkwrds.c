/* Write a line backwards. */

#include <stdio.h>

void wrt_it(void)
{
   int   c;

   if ((c = getchar()) != '\n'){
      wrt_it();
   }
   putchar(c);
}

int min(void)
{

   printf("Input a line:  ");
   wrt_it();
   printf("\n\n");
   return 0;
}
