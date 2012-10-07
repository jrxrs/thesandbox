package questions;

/**
 * Print a list of prime numbers:
 * http://www.wikihow.com/Check-if-a-Number-Is-Prime
 */
public class PrimePrinter
{
    public static void printPrimes(long lessThan) {
        trialDivision(lessThan);
    }

    public static void trialDivision(long lessThan) {
        int found = 0;
        /* Start looking from 3 so we can eliminate even numbers completely */
        if (2 < lessThan) {
            System.out.print(2 + ", ");
            found++;
        }

        for (long i = 3; i < lessThan; i += 2) {
            long limit = (long) Math.pow(i, 0.5d);
            boolean print = true;
            for (long j = 2; j <= limit; j++) {
                if (i % j == 0) {
                    print = false;
                }
            }
            if (print) {
                found++;
                System.out.print(i + ", ");
            }
        }
        System.out.println();
        System.out.println("trialDivision found " + found + " primes less than " + lessThan);
    }

    public static void fermatsLittleTheorem(long lessThan) {
        int found = 0;
        /* Start looking from 3 so we can eliminate even numbers completely */
        if (2 < lessThan) {
            System.out.print(2 + ", ");
            found++;
        }
        for (long i = 3; i < lessThan; i++) {
            //...
        }
    }

    public static void main(String[] args) {
        printPrimes(1000);
    }
}
