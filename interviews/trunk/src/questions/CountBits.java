package questions;

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

    public static void main(String[] args) {
        System.out.println(2 + " -> " + countBits(2));
        System.out.println(5 + " -> " + countBits(5));
        System.out.println(11 + " -> " + countBits(11));
        System.out.println(123 + " -> " + countBits(123));
        System.out.println(1111 + " -> " + countBits(1111));
    }
}
