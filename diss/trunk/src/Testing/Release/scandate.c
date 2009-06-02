/* Write a line backwards. */

#include <stdio.h>

int main(void)
{
   int day, year;
   char k[10];
   printf("Enter the date (dd month name yyyy):  ");

   scanf("%d %s %d", day, k, &year);

   printf("\nThe date is %d/%s/%d\n", day, k, year);
   return 0;
}
