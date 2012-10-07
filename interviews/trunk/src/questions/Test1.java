package questions;

/**
 * User: vashayke
 * Date: 2009-10-{DAY}
 * Time: 5:25:47 PM
 */
public class Test1 {

    public static void test() {
        new B();
    }

    public static class A {
        private String strA = "Hello Class A";

        public A() {
            init();
        }

        public void init() {
            System.out.println("class A: " + strA);
        }
    }

    public static class B extends A {
        private String strB = "Hello Class B";

        public B() {
            init();
        }

        @Override
        public void init() {
            System.out.println("class B: " + strB);
        }
    }

    public static void main(String args[]) {
        test();
    }
}
