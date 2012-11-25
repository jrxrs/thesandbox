package org.thesandbox.questions;

import java.util.Map;
import java.util.TreeMap;

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
 * steps it takes to reach 1 for the input and record the number of occurances
 * of each Collatz number for integer from 1 - 1,000,000.
 */
public class Collatz
{
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

    public static void main(String[] args) {
        Map<Integer, Integer> occurances = new TreeMap<Integer, Integer>();

        for (int i = 1; i < 1000; i++) {
            int c = collatz(i);
            Integer oGet = occurances.get(c);
            if (oGet == null) {
                occurances.put(c, 1);
            } else {
                occurances.put(c, oGet + 1);
            }
        }

        for (Map.Entry entry : occurances.entrySet()) {
            System.out.println(entry.getKey() + " occurred: " + entry.getValue() + " times.");
        }
    }
}
