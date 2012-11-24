package org.thesandbox.questions;

import junit.framework.Assert;
import org.junit.Test;

public class BitCountTestCase {
    @Test
    public void testCountBits() {
        BitCount bc = new BitCount();
        Assert.assertEquals(2, bc.countBits(5));
    }
}
