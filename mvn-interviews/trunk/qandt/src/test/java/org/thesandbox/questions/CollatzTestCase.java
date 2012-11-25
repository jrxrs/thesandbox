package org.thesandbox.questions;

import junit.framework.Assert;
import org.junit.Test;

public class CollatzTestCase {

    @Test
    public void testCollatz() {
        Assert.assertEquals(0, Collatz.collatz(1));
        Assert.assertEquals(1, Collatz.collatz(2));
        Assert.assertEquals(7, Collatz.collatz(3));
        Assert.assertEquals(2, Collatz.collatz(4));
        Assert.assertEquals(5, Collatz.collatz(5));
        Assert.assertEquals(8, Collatz.collatz(6));
    }

}
