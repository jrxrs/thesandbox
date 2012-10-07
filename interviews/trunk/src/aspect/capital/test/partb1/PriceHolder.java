package aspect.capital.test.partb1;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public final class PriceHolder
{
    /**
     * The class calculates prices for instruments in a thread safe manner.
     * The most recent price is always favoured when a request is made.
     */
    private ConcurrentHashMap<String, LinkedBlockingDeque<BigDecimal>> priceHolder;

    public PriceHolder() {
        priceHolder =
                new ConcurrentHashMap<String, LinkedBlockingDeque<BigDecimal>>();
    }

    /** Called when a price 'p' is received for an entity 'e' */
    public void putPrice(String e, BigDecimal p) {
        LinkedBlockingDeque<BigDecimal> d = priceHolder.get(e);
        if (null == d) {
            d = new LinkedBlockingDeque<BigDecimal>();
            priceHolder.put(e, d);
        }
        try {
            d.putLast(p);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /** Called to get the latest price for entity 'e' */
    public BigDecimal getPrice(String e) throws Exception {
        LinkedBlockingDeque<BigDecimal> d = priceHolder.get(e);
        if (null == d) {throw new  Exception("No prices received for \"" + e + "\" yet.");}

        BigDecimal latestPrice = d.peekLast();
        while (!d.peekFirst().equals(latestPrice)) {
            d.pop();
        }

        return latestPrice;
    }

    /**
     * Called to determine if the price for entity 'e' has
     * changed since the last call to getPrice(e).
     */
    public boolean hasPriceChanged(String e) {
        LinkedBlockingDeque<BigDecimal> d = priceHolder.get(e);
        return null != d && d.size() > 1;
    }

    /* Main method for testing */
    public static void main(String[] args) {
        try {
            PriceHolder ph = new PriceHolder();
            ph.putPrice("a", new BigDecimal(10));
            System.out.println(ph.getPrice("a")); // 10
            ph.putPrice("a", new BigDecimal(12));
            ph.putPrice("b", new BigDecimal(2));
            ph.putPrice("a", new BigDecimal(11));
            System.out.println(ph.getPrice("a")); // 11
            System.out.println(ph.getPrice("b")); // 2
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
