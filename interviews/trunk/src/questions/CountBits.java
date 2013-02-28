package questions;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Count the number of bits in an integer. e.g.
 * 5 has 2 bits set
 * 2 -> 1
 * 5 -> 2
 * 11 -> 3
 * 123 -> 6
 * 1111 -> 6
 */
public class CountBits
{
    public static int countBits(int x) {
        int result = 0;
        while (x > 0) {
            result += x & 1;
            x = x >> 1;
        }
        return result;
    }

    /*
     * Count the number of bits set in x.
     */
    public static int kernighanCountBits(int x) {
        int c; // c accumulates the total bits set in x
        for (c = 0; x != 0; c++) {
            x &= x - 1; // clear the least significant bit set
        }
        return c;
    }

    @Test
    public void testCountBits() {
        assertEquals(0, countBits(0));
        assertEquals(1, countBits(1));
        assertEquals(1, countBits(2));
        assertEquals(2, countBits(5));
        assertEquals(3, countBits(11));
        assertEquals(6, countBits(123));
        assertEquals(6, countBits(1111));
        assertEquals(0, kernighanCountBits(0));
        assertEquals(1, kernighanCountBits(1));
        assertEquals(1, kernighanCountBits(2));
        assertEquals(2, kernighanCountBits(5));
        assertEquals(3, kernighanCountBits(11));
        assertEquals(6, kernighanCountBits(123));
        assertEquals(6, kernighanCountBits(1111));
    }

    public static void main(String[] args) {
        System.out.println(2 + " -> " + countBits(2));
        System.out.println(5 + " -> " + countBits(5));
        System.out.println(11 + " -> " + countBits(11));
        System.out.println(123 + " -> " + countBits(123));
        System.out.println(1111 + " -> " + countBits(1111));
    }
}
