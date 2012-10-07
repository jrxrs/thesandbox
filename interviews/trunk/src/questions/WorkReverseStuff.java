package questions;

public class WorkReverseStuff
{
    public static String reverse(String s) {
        char[] string = s.toCharArray();
        int start = 0, end = string.length - 1;
        char temp;
        while (start < end) {
            temp = string[start];
            string[start] = string[end];
            string[end] = temp;
            start++;
            end--;
        }
        System.out.println("Complete in " + start + " iterations!");

        return String.valueOf(string);
    }

    public static int reverse(int in) {
        int result = 0;
        while (in > 0) {
            result = (result * 10) + (in % 10);
            in /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("0123456789A");
        System.out.println(WorkReverseStuff.reverse("0123456789A"));
        System.out.println(1234);
        System.out.println(WorkReverseStuff.reverse(1234));
        A a = new B();
        System.out.println(a.getGreeting());
        C c = new D();
        System.out.println(c.getGreeting());
    }

    public static class A
    {
        public String getGreeting() {
            return "Hello from A";
        }
    }

    public static class B extends A
    {
        @Override
        public String getGreeting() {
            return "Hello from B";
        }
    }

    public static class C
    {
        public static String getGreeting() {
            return "Hello from C";
        }
    }

    public static class D extends C
    {
        public static String getGreeting() {
            return "Hello from D";
        }
    }
}
