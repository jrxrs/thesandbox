package org.thesandbox.questions;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class OverrideIllegalOverrideTestCase {

    private OverrideIllegalOverride overrideIllegalOverride;

    @Before
    public void setUp() throws Exception {
        overrideIllegalOverride = new OverrideIllegalOverride(9);
    }

    @Test
    public void testGetNumber() throws Exception {
        Assert.assertEquals(42, overrideIllegalOverride.getNumber());
    }
}
