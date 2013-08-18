package org.thesandbox.questions;

class Colour {
    protected int red, green;
    private int blue;

    Colour() {
        this(10, 10, 10);
    }

    Colour(int r, int g, int b) {
        red = r;
        green = g;
        blue = b;
    }

    public String toString() {
        return "The colour is: " + red + green + blue;
    }

    protected String myToString() {
        return "The colour is: " + (red + green + blue);
    }

    public static void main(String[] args) {
        System.out.println(new Colour());
        System.out.println(new Colour().myToString());
    }
}
