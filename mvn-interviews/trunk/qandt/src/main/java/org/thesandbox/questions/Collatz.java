package org.thesandbox.questions;

/**
 * The Collatz Conjecture
 * Take any natural number n.
 * If n is even, divide it by 2 to get n / 2.
 * If n is odd, multiply it by 3 and add 1 to obtain 3n + 1.
 * Repeat the process indefinitely.
 * The conjecture is that no matter what number you start with, you will always eventually reach 1.
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

    private Collatz() {}

    public static int collatz(int n) {
        int result = 0;

        while (n != 1) {
            if ((n & 1) == 0) {
                n /= 2;
            } else {
                n = (3 * n) + 1;
            }
            result++;
        }

        return result;
    }

}
