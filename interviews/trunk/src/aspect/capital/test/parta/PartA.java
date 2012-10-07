package aspect.capital.test.parta;

import java.util.ArrayList;
import java.util.List;

public class PartA
{
    /**
     * Design and implement a static method map that takes two arguments,
     * a "function" f and a list l, and returns a new list generated from
     * applying f to each element of l.
     * The original list l and its elements remain unchanged.
     * @param f - The function to apply
     * @param l - The list on which to apply f
     * @param <G> - Generic type information
     * @return The result of applying f each element of l
     */
    public static <G> List<G> map(Function<G> f, List<G> l) {
        if (null == l) {throw new IllegalArgumentException("List l cannot be null.");}

        List<G> rv = new ArrayList<G>(l.size());
        for (G g : l) {
            rv.add(f.apply(g));
        }
        return rv;
    }

    public interface Function<F>
    {
        public abstract F apply(F arg);
    }

    private static class PlusOne implements Function<Integer>
    {
        @Override
        public Integer apply(Integer arg) {
            return arg + 1;
        }
    }

    /**
     * Main method for testing map function.
     * @param args ...
     */
    public static void main(String[] args) {
        ArrayList<Integer> three = new ArrayList<Integer>(3);
        for (int i = 1; i < 4; i++) {
            three.add(i);
        }
        System.out.println("Before applying PlusOne: " + three);
        List result = map(new PlusOne(), three);
        System.out.println(">After applying PlusOne: " + result);
    }
}
