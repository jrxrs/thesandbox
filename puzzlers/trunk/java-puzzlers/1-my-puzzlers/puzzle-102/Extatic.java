/**
 * Created by IntelliJ IDEA.
 *
 * @author jrxrs
 * @date 19-Nov-2009
 */
public class Extatic
{
    public Extatic() {
        A a = new A();
        B b = new B();
        A ab = new B();

        printFoo("a",  a);
        printFoo("b",  b);
        printFoo("ab", ab);
    }

    public void printFoo(String name, A a) {
        System.out.println("printFoo.A");
        System.out.println(name + ".x = " + a.x);
        System.out.println(name + ".getX() = " + a.getX());
        System.out.println(name + ".y = " + a.y);
        System.out.println(name + ".getY() = " + a.getY());
    }

    public void printFoo(String name, B b) {
        System.out.println("printFoo.B");
        System.out.println(name + ".x = " + b.x);
        System.out.println(name + ".getX() = " + b.getX());
        System.out.println(name + ".y = " + b.y);
        System.out.println(name + ".getY() = " + b.getY());
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
        new Extatic();
    }
}
