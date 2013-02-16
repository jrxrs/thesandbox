package org.thesandbox.quotes;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class EodQuoteTestCase {

    private static final String eSymbol = "VOD.L";
    private static final String eDate = "2012-09-17";
    private static final String eOpen = "172.04";
    private static final String eHigh = "173.0";
    private static final String eLow = "172.04";
    private static final String eClose = "172.52";
    private static final String eVolume = "11000000";
    private static final String eAdjClose = "172.52";

    private EodQuote quote;

    @Before
    public void setUp() throws Exception {
        quote = new EodQuote(eSymbol, eDate, eOpen, eHigh, eLow, eClose, eVolume, eAdjClose);
    }

    @Test
    public void testGetSymbol() throws Exception {
        Assert.assertEquals(eSymbol, quote.getSymbol());
    }

    @Test
    public void testGetDate() throws Exception {
        Assert.assertEquals(new LocalDate(2012, 9, 17), quote.getDate());
    }

    @Test
    public void testGetOpen() throws Exception {
        Assert.assertEquals(new BigDecimal(eOpen), quote.getOpen());
    }

    @Test
    public void testGetHigh() throws Exception {
        Assert.assertEquals(new BigDecimal(eHigh), quote.getHigh());
    }

    @Test
    public void testGetLow() throws Exception {
        Assert.assertEquals(new BigDecimal(eLow), quote.getLow());
    }

    @Test
    public void testGetClose() throws Exception {
        Assert.assertEquals(new BigDecimal(eClose), quote.getClose());
    }

    @Test
    public void testGetVolume() throws Exception {
        Assert.assertEquals(new BigDecimal(eVolume), quote.getVolume());
    }

    @Test
    public void testGetAdjClose() throws Exception {
        Assert.assertEquals(new BigDecimal(eAdjClose), quote.getAdjClose());
    }

    @Test
    public void testCompareTo() throws Exception {
        EodQuote quote1 = new EodQuote("LSE.L", "2012-09-17", "0.1", "0.2", "0.3", "0.4", "100", "0.5");
        Assert.assertTrue(quote1.compareTo(quote) < 0);
        Assert.assertTrue(quote.compareTo(quote1) > 0);
        Assert.assertTrue(quote.compareTo(quote) == 0);
    }

    @Test
    public void testEquals() throws Exception {
        EodQuote quote1 = new EodQuote("LSE.L", "2012-09-17", "0.1", "0.2", "0.3", "0.4", "100", "0.5");
        EodQuote quote2 = new EodQuote("LSE.L", "2012-09-18", "0.1", "0.2", "0.3", "0.4", "100", "0.5");
        EodQuote quoteClone = new EodQuote(eSymbol, eDate, eOpen, eHigh, eLow, eClose, eVolume, eAdjClose);

        Assert.assertTrue(quote.equals(quote));
        Assert.assertTrue(quote.equals(quoteClone));
        Assert.assertFalse(quote.equals(quote1));
        Assert.assertFalse(quote.equals(new Object()));
        Assert.assertFalse(quote1.equals(quote2));
    }

    @Test
    public void testHashCode() throws Exception {
        EodQuote quote1 = new EodQuote("LSE.L", "2012-09-17", "0.1", "0.2", "0.3", "0.4", "100", "0.5");
        EodQuote quote2 = new EodQuote("LSE.L", "2012-09-18", "0.1", "0.2", "0.3", "0.4", "100", "0.5");
        EodQuote quoteClone = new EodQuote(eSymbol, eDate, eOpen, eHigh, eLow, eClose, eVolume, eAdjClose);

        Assert.assertEquals(quote.hashCode(), quoteClone.hashCode());
        Assert.assertFalse(quote.hashCode() == quote1.hashCode());
        Assert.assertFalse(quote1.hashCode() == quote2.hashCode());
    }

}
