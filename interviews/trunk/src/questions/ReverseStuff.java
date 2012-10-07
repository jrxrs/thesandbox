package questions;

/**
 * Write some simple but efficient code to return the reverse of a String
 * passed by value without using StringBuffer.reverseString().
 */
public class ReverseStuff
{
    public static String reverse(String in) {
        char[] inA = in.toCharArray();
        int start = 0, end = inA.length - 1;
        char temp;
        while (start < end) {
            temp = inA[start];
            inA[start] = inA[end];
            inA[end] = temp;
            start++; end--;
        }

        return new String(inA);
    }

    public static int reverse(int in) {
        int result = 0;
        while (in >= 1) {
            result = result * 10 + (in % 10);
            in /= 10;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("1234567890");
        System.out.println(reverse("1234567890"));
        System.out.println("123456789");
        System.out.println(reverse("123456789"));
        System.out.println(1234567890);
        System.out.println(reverse(1234567890));
        System.out.println(123456789);
        System.out.println(reverse(123456789));
    }
}
