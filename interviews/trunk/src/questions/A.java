package questions;

/**
 * Given this definition of a class, how would you call the test() method in A?
 */
public class A
{
    public void test() {
        System.out.println("Called A.test()");
    }

    class B
    {
        public void test() {
            System.out.println("Called B.test()");
        }

        public void dom() {
            //call the test() method in A. How do you do this?
            A.this.test();
        }
    }
}
