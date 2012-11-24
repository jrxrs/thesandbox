package org.thesandbox.questions;

/**
 * Write a function that counts the number of set bits in an integer.
 * e.g. 5 has 2 bits set (bit 0 and bit 2).
 */
public class BitCount {
    public int countBits(int x) {
        int result = 0;
        while (x > 0) {
            result += x & 1;
            x = x >> 1;
        }

        return result;
    }
}
