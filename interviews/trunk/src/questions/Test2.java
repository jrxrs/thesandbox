package questions;

/**
 * User: vashayke
 * Date: 2009-10-{DAY}
 * Time: 5:30:28 PM
 */
public class Test2 {

    public static void test() {

        A a = new A();
        B b = new B();
        A ab = new B();

        System.out.println("a.x = " + a.x);
        System.out.println("a.getX() = " + a.getX());
        System.out.println("a.y = " + a.y);
        System.out.println("a.getY() = " + a.getY());

        System.out.println("b.x = " + b.x);
        System.out.println("b.getX() = " + b.getX());
        System.out.println("b.y = " + b.y);
        System.out.println("b.getY() = " + b.getY());

        System.out.println("ab.x = " + ab.x);
        System.out.println("ab.getX() = " + ab.getX());
        System.out.println("ab.y = " + ab.y);
        System.out.println("ab.getY() = " + ab.getY());

    }

    public static class A {
        public int x = 10;
        public static int y = 20;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static class B extends A {
        public int x = 100;
        public static int y = 200;

        @Override
        public int getX() {
            return x;
        }

        @Override
        public int getY() {
            return y;
        }
    }

    public static void main(String args[]) {
        test();
    }
}
