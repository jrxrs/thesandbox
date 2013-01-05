package org.thesandbox.questions;

/**
 * This class aims to show that when you override a method you cannot make it
 * more private in the sub-class than it was in the parent (super) class,
 * however you can make it more public.
 */
public class IllegalOverride {

    private int number;

    public IllegalOverride(int number) {
        this.number = number;
    }

    protected int getNumber() {
        return number;
    }

}
