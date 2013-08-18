package org.thesandbox.questions;

import org.junit.Assert;
import org.junit.Test;

public class General {
    @Test
    public void testPlusPlus() throws Exception {
        int counter = 0;
        int delay = counter++;
        Assert.assertEquals(counter, 1);
        Assert.assertEquals(delay, 0);

        delay = ++counter;
        Assert.assertEquals(counter, 2);
        Assert.assertEquals(delay, 2);
    }

    private int newCapacity(int oldCapacity) {
        return (oldCapacity * 3) / 2 + 1;
    }

    @Test
    public void testCapacity() throws Exception {
        int in = 75 / 2;
        Assert.assertEquals(in, 37);

        int capacity = newCapacity(10);
        Assert.assertEquals(capacity, 16);

        capacity = newCapacity(capacity);
        Assert.assertEquals(capacity, 25);

        capacity = newCapacity(capacity);
        Assert.assertEquals(capacity, 38);

        capacity = newCapacity(capacity);
        Assert.assertEquals(capacity, 58);
    }
}
