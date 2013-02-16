package org.thesandbox.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.concurrent.Callable;

public class QuoteFieldAverage implements Callable<BigDecimalResult> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteFieldAverage.class);

    private final RequestCache cache;
    private final String symbol;
    private final EodQuoteFieldGetter<BigDecimal> getter;

    public QuoteFieldAverage(RequestCache cache,
                             String symbol,
                             EodQuoteFieldGetter<BigDecimal> getter) {
        this.cache = cache;
        this.symbol = symbol;
        this.getter = getter;
    }

    @Override
    public BigDecimalResult call() {
        try {
            LOGGER.info("Requesting quotes for Field: {} of Symbol: {}", getter.getFieldName(), symbol);
            final long start = System.currentTimeMillis();
            final Collection<EodQuote> quotes = cache.getQuotes(symbol);
            LOGGER.info("Got quotes for Field: {} of Symbol: {}, took {}ms",
                    getter.getFieldName(), symbol, System.currentTimeMillis() - start);

            BigDecimal runningTotal = new BigDecimal(0);
            for (EodQuote quote : quotes) {
                runningTotal = runningTotal.add(getter.getField(quote));
            }

            BigDecimal result = runningTotal.divide(new BigDecimal(quotes.size()), RoundingMode.HALF_UP);
            LOGGER.info("Finished calculation for Field: {} of Symbol: {}, took {}ms in total, result was {}",
                    getter.getFieldName(), symbol, System.currentTimeMillis() - start, result.toString());
            return new BigDecimalResult(symbol, getter.getFieldName(), result);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while getting quotes from cache. ", e);
            Thread.currentThread().interrupt();
        }

        return null;
    }

}
