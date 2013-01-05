package org.thesandbox.questions;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Map;
import java.util.TreeMap;

@RunWith(PowerMockRunner.class)
public class CollatzTestCase {

    @Test
    public void testPrivateConstructor() throws Exception {
        Whitebox.invokeConstructor(Collatz.class);
    }

    @Test
    public void testCollatz() {
        Assert.assertEquals(0, Collatz.collatz(1));
        Assert.assertEquals(1, Collatz.collatz(2));
        Assert.assertEquals(7, Collatz.collatz(3));
        Assert.assertEquals(2, Collatz.collatz(4));
        Assert.assertEquals(5, Collatz.collatz(5));
        Assert.assertEquals(8, Collatz.collatz(6));
    }

    @Test
    public void testAnalysis() throws Exception {
        Map<Integer, Integer> occurrences = new TreeMap<Integer, Integer>();

        for (int i = 1; i < 1000; i++) {
            int c = Collatz.collatz(i);
            Integer oGet = occurrences.get(c);
            if (oGet == null) {
                occurrences.put(c, 1);
            } else {
                occurrences.put(c, oGet + 1);
            }
        }

        for (Map.Entry entry : occurrences.entrySet()) {
            System.out.println(entry.getKey() + " occurred: " + entry.getValue() + " times.");
        }

        Assert.assertTrue(true);
    }
}
