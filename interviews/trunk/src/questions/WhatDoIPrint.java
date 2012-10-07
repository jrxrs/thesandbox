package questions;

/**
 * What does the following code output?
 */
public class WhatDoIPrint
{
    public static void change(String c, String[] d)
    {
        c = "y";
        d[0] = c;
    }

    public static void whatDoIPrint() {
        // Strings
        String a = new String("Hello");
        String b = new String("Hello");
        System.out.println( a == b ? "same" : "different" );

        String c = "X";
        String[] d = {" ", c};
        change(c, d);
        System.out.println("Output: " + c + " " + d[0] + " " + d[1]);

        // Generic
        System.out.println("1/2=" + 1/2);
        System.out.println("Floor=" + Math.floor(-1/2));
        System.out.println("1/2 + 1/2=" + 1/2 + 1/2);

        // From GR
        int x = 1/2;
        System.out.println("x = " + x);

        int y = 0;
        if (y == 0 || ++y == 1) {
            System.out.println("y = " + y);
        }

        int z = 0;
        while (z != 3) {
            z += 2;
        }
        System.out.println("z = " + z);
    }

    public static void main(String[] args) {
        whatDoIPrint();
    }
}
