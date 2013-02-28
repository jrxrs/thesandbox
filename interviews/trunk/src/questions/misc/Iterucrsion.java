package questions.misc;

/**
 * Can you write a sample code that will count the number of "A"s in a given text?
 * Show both iterative and recursive approaches?
 *
 * http://java-success.blogspot.co.uk/2012/07/java-coding-questions-frequently-asked.html
 *
 * For instance assume an input string of "AAA rating"
 */
public class Iterucrsion
{
    public static int iterCount(String input) {
        int count = 0;
        if (null == input) return count;
        for (char c : input.toCharArray()) {
            if (c == 'A') {
                count++;
            }
        }
        return count;
    }

    public static int recursionCount(String input) {
        if (null == input || input.length() == 0) {
            return 0;
        }

        int count = 0;
        if (input.charAt(0) == 'A') {
            count++;
        }

        return count + recursionCount(input.substring(1));
    }

    public static void main(String[] args) {
        System.out.println("iterCount says \"AAA rating\" has " + iterCount("AAA rating") + " 'A's");
        System.out.println("recursionCount says \"AAA rating\" has " + recursionCount("AAA rating") + " 'A's");
    }
}
