/**
 * Created by IntelliJ IDEA.
 * @author jrxrs
 * @date 19-Nov-2009
 *
 * This is one of Vadim's evil interview questions.
 */
public class HelloInheritance
{
    public HelloInheritance() {
        new TestB();
    }

    public class TestA {
        private String strA = " Hello class A";

        public TestA() {
            init();
        }

        public void init() {
            System.out.println("Class A: " + strA);
        }
    }

    public class TestB extends TestA {
        private String strB = " Hello class B";

        public TestB() {
            init();
        }

        @Override
        public void init() {
            System.out.println("Class B: " + strB);
        }
    }

    public static void main(String args[]) {
        new HelloInheritance();
    }
}
