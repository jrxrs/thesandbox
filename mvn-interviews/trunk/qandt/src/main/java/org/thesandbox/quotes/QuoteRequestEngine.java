package org.thesandbox.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thesandbox.quotes.fields.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

public class QuoteRequestEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteRequestEngine.class);

    private final List<String> symbols;
    private final ExecutorService executorService;

    private final List<EodQuoteFieldGetter<BigDecimal>> getters = new ArrayList<EodQuoteFieldGetter<BigDecimal>>(6);
    private final RequestCache cache = new RequestCache();

    public QuoteRequestEngine(List<String> symbols) {
        this.symbols = symbols;
        getters.add(new AdjCloseFieldGetter());
        getters.add(new VolumeFieldGetter());
        getters.add(new OpenFieldGetter());
        getters.add(new LowFieldGetter());
        getters.add(new HighFieldGetter());
        getters.add(new CloseFieldGetter());
        executorService = Executors.newFixedThreadPool(2);
    }

    public void begin() {
        final List<QuoteFieldAverage> tasks = new ArrayList<QuoteFieldAverage>(getters.size() * symbols.size());
        for (String symbol : symbols) {
            for (EodQuoteFieldGetter<BigDecimal> getter : getters) {
                tasks.add(new QuoteFieldAverage(cache, symbol, getter));
            }
        }

        try {
            LOGGER.info("Sending tasks to executor.");
            final long overallStart = System.currentTimeMillis();
            final List<Future<BigDecimalResult>> results = executorService.invokeAll(tasks);
            LOGGER.info("Compiling results...");
            /* This isn't concurrent so no need for concurrent map */
            final HashMap<String, Set<BigDecimalResult>> grouped = new HashMap<String, Set<BigDecimalResult>>(symbols.size());
            for (Future<BigDecimalResult> result : results) {
                final BigDecimalResult bdr = result.get();
                Set<BigDecimalResult> set = grouped.get(bdr.getSymbol());
                if (set == null) {
                    set = new HashSet<BigDecimalResult>(getters.size());
                    grouped.put(bdr.getSymbol(), set);
                }
                set.add(bdr);
            }

            for (Map.Entry<String, Set<BigDecimalResult>> entry : grouped.entrySet()) {
                LOGGER.info("Printing results for Symbol {}", entry.getKey());
                for (BigDecimalResult bdr : entry.getValue()) {
                    LOGGER.info("Symbol: {}, Field: {}, Result: {}", bdr.getSymbol(), bdr.getField(), bdr.getResult().toString());

                }
            }
            LOGGER.info("Analysis complete, in {}ms.", System.currentTimeMillis() - overallStart);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while invoking all.", e);
        } catch (ExecutionException e) {
            LOGGER.error("Exception while getting result from future.", e);
        }

        executorService.shutdown();
    }

}
