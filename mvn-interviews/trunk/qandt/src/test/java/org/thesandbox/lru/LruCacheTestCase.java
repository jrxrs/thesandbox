package org.thesandbox.lru;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LruCacheTestCase {

    private LruCache<String, Integer> lru;
    private static final int SIZE = 10;

    @Before
    public void setUp() throws Exception {
        lru = new LruCache<String, Integer>(SIZE);
        prePopulate(lru);
    }

    private void prePopulate(Map<String, Integer> m) {
        for (int i = 0; i < SIZE; i++) {
            m.put("Key " + i, i);
        }
    }

    @Test
    public void testNormalHashMap() throws Exception {
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        prePopulate(hash);

        System.out.println("Printing the HashMap contents:");
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            /* The HashMap is chaotic so we can't assert anything */
        }

        System.out.println("Printing the LruCache contents:");
        int counter = 0;
        for (Map.Entry<String, Integer> entry : lru.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            /* Initially our keys should be in the order they were added! */
            Assert.assertEquals(counter++, (long)entry.getValue());
        }

        System.out.println("Using some of the Lru keys...");
        lru.get("Key 7");
        lru.get("Key 3");

        System.out.println("Printing the LruCache contents again:");
        counter = 0;
        for (Map.Entry<String, Integer> entry : lru.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            if (counter == 3 || counter == 7) {
                counter++;
            } else if (counter == 10) {
                Assert.assertEquals(7, (long)entry.getValue());
                counter++;
                continue;
            } else if (counter == 11) {
                Assert.assertEquals(3, (long)entry.getValue());
                return;
            }
            Assert.assertEquals(counter++, (long)entry.getValue());
        }
    }

}
