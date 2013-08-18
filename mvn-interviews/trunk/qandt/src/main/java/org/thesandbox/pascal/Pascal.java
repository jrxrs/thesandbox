package org.thesandbox.pascal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pascal {

    public int pascal(int c, int r) {
        if (c == 0 || r == c) {
            return 1;
        } else if (c < r) {
            return pascal(c - 1, r - 1) + pascal(c, r - 1);
        } else return 0;
    }

    public int pascal(String c, String r) {
        int ci = Integer.parseInt(c);
        int ri = Integer.parseInt(r);

        return pascal(ci, ri);
    }

    public void printPascalsTriangle(final int noRows) {
        Pascal pascal = new Pascal();
        for (int r = 0; r < noRows; r++) {
            for (int c = 0; c <= r; c++) {
                System.out.print(pascal.pascal(c, r) + " ");
            }
            System.out.println();
        }
    }

    public static void test0() {
        int a = 10;
        int b = 3;
        double c = a / b;
        System.out.println("c = " + c);
        System.out.println("(double)a / b = " + ((double)a / b));
        System.out.println("a / (double)b = " + (a / (double)b));
        System.out.println("(double)a / (double)b = " + (a / (double)b));
    }

    public static void test1() {
        Map<String, Double> a = new HashMap<String, Double>();
//        Map<String, Double> b = new HashMap<Object, Double>();
//        Map<Object, Double> c = new HashMap<String, Double>();
        Map<?, Double> d = new HashMap<String, Double>();
        Map<String, ?> e = new HashMap<String, Double>();
//        Map<?, ?> f = new HashMap<>();
    }

    public static void test2(List<String> a) {
        a.add(null);
        a.add("test");
//        a.add(new Object());
        String b = a.get(0);
        Object c = a.get(0);
    }

    public static void test3(List<Object> a) {
        a.add(null);
        a.add("test");
        a.add(new Object());
//        String b = a.get(0);
        Object c = a.get(0);
    }

    public static void test4(List<?> a) {
        a.add(null);
//        a.add("test");
//        a.add(new Object());
//        String b = a.get(0);
        Object c = a.get(0);
    }

    public static void main(String[] args) {
        test0();
    }
}
