package org.thesandbox.questions;

public class Packaging {

    public final String iName;

    public Packaging() {
        iName = "Package" + Math.random();
    }

    public void attemptAccess() {
        Colour colour = new Colour();
        colour.myToString();
        int r = colour.red;
//        int b = colour.blue; Error as blue is private in Colour
    }

    public static void main(String[] args) {
        Packaging packaging = new Packaging();
        packaging.attemptAccess();
    }
}
