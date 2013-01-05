package org.thesandbox.questions;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class IllegalOverrideTestCase {

    private IllegalOverride illegalOverride;

    @Before
    public void setUp() throws Exception {
        illegalOverride = new IllegalOverride(4);
    }

    @Test
    public void testGetNumber() throws Exception {
        Assert.assertEquals(4, illegalOverride.getNumber());
    }

}
