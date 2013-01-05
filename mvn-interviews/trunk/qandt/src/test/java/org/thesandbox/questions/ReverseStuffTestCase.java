package org.thesandbox.questions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class ReverseStuffTestCase {

    @Test
    public void testPrivateConstructor() throws Exception {
        Whitebox.invokeConstructor(ReverseStuff.class);
    }

    @Test
    public void testReverseString() {
        assertEquals("Check reverse(String) odd chars", "A0987654321", ReverseStuff.reverse("1234567890A"));
        assertEquals("Check reverse(String) even chars", "0987654321", ReverseStuff.reverse("1234567890"));
    }

    @Test
    public void testReverseInt() throws Exception {
        assertEquals("Check reverse(int) short", 4321, ReverseStuff.reverse(1234));
        assertEquals("Check reverse(int) longer", 12345678, ReverseStuff.reverse(876543210));
    }

}
