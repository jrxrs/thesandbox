package org.thesandbox.questions;

/**
 * The Collatz Conjecture
 * Take any natural number n.
 * If n is even, divide it by 2 to get n / 2.
 * If n is odd, multiply it by 3 and add 1 to obtain 3n + 1.
 * Repeat the process indefinitely.
 * The conjecture is that no matter what number you start with, you will always
 * eventually reach 1.
 *
 * Examples
 * 1 -> 1 (0)
 * 2 -> 1 (1)
 * 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1 (7)
 * 4 -> 2 -> 1 (2)
 * 5 -> 16 -> 8 -> 4 -> 2 -> 1 (5)
 * 6 -> 3 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1 (8)
 *
 * The Question posed is to write a function which returns the number of
 * steps it takes to reach 1 for the input and record the number of occurrences
 * of each Collatz number for integer from 1 - 1,000,000.
 */
public final class Collatz {

    /**
     * Private no-arg constructor.
     */
    private Collatz() { }

    /**
     * Define a constant for three to stop CheckStyle complaining about magic
     * number.
     */
    private static final int THREE = 3;

    /**
     * Calculate the number of iterations of the Collatz function required for
     * i to be reduced to 1.
     * @param i the number to perform the Collatz conjecture on
     * @return the number iterations required to reach 1 for i
     */
    public static int collatz(final int i) {
        int result = 0;

        int n = i;
        while (n != 1) {
            if ((n & 1) == 0) {
                n /= 2;
            } else {
                n = (THREE * n) + 1;
            }
            result++;
        }

        return result;
    }

}
