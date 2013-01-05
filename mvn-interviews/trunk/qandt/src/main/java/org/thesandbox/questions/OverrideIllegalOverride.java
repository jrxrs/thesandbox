package org.thesandbox.questions;

/**
 * This class aims to show that when you override a method you cannot make it
 * more private in the sub-class than it was in the parent (super) class,
 * however you can make it more public.
 */
public class OverrideIllegalOverride extends IllegalOverride {

    public OverrideIllegalOverride(int number) {
        super(number);
    }

        /* Uncomment this to see the problem!
        @Override
        private int getNumber() {
            return 7;
        }*/

    /* This is ok. */
    @Override
    public int getNumber() {
        return 42;
    }

}
