package aspect.capital.test.partb2;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public final class PriceHolder
{
    /**
     * The class calculates prices for instruments in a thread safe manner.
     * The most recent price is always favoured when a request is received.
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
            notifyAll(); // notify all threads waiting for a new price
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /** Called to get the latest price for entity 'e' */
    public BigDecimal getPrice(String e) throws IllegalArgumentException {
        LinkedBlockingDeque<BigDecimal> d = priceHolder.get(e);
        if (null == d) {throw new  IllegalArgumentException("No prices received for \"" + e + "\" yet.");}

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

    /**
     * Returns the next price for entity 'e'. If the price has changed since the last
     * call to getPrice() or waitForNextPrice(), it returns immediately that price.
     * Otherwise it blocks until the next price change for entity 'e'.
     */
    public BigDecimal waitForNextPrice(String e) throws InterruptedException, IllegalArgumentException {
        if (!hasPriceChanged(e)) {
            do {
                wait();
            } while (!hasPriceChanged(e));
        }
        return getPrice(e);
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
