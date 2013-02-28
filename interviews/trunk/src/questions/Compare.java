package questions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Write a function that determines whether two integers are equal without using
 * any comparative operators. Hint: Try using bit operators.
 */
public class Compare
{
    public static boolean compare(int i, int j) {
        int r = i ^ j;

        return 0 == r;
    }

    @Test
    public void testCompare() {
        assertEquals(true, compare(123, 123));
        assertEquals(false, compare(123, 321));
    }
}
