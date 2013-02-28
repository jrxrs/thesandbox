package org.thesandbox.quotes;

import java.util.Collection;
import java.util.concurrent.*;

/**
 * Note: Strictly speaking this isn't a true cache, for instance there is no
 * eviction policy included, it's just a toy example coded to test this code and
 * experiment with executors and other concurrent features of the language.
 * If you really wanted to cache this kind of data you'd need to refresh it
 * daily after the close, you might do this if you were computing a core set of
 * historical statistics when the application started and then completing other
 * ad-hoc calculations throughout the day reusing the original data set,
 * manually expiring the contents of the cache at a fixed point after today's
 * data is available would probably be the best approach.
 */
public class RequestCache {

    private final ConcurrentMap<String, Future<Collection<EodQuote>>> cache;

    public RequestCache() {
        cache = new ConcurrentHashMap<String, Future<Collection<EodQuote>>>();
    }

    public Collection<EodQuote> getQuotes(String symbol) throws InterruptedException {
        while (true) {
            Future<Collection<EodQuote>> f = cache.get(symbol);
            if (f == null) {
                HistoricCsvRequester request = new HistoricCsvRequester(symbol);
                FutureTask<Collection<EodQuote>> ft = new FutureTask<Collection<EodQuote>>(request);

                f = cache.putIfAbsent(symbol, ft);
                if (f == null) {
                    f = ft; ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(symbol, f);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }

    /**
     * If the Throwable is an Error, throw it;
     * If it is a RuntimeException return it, otherwise throw IllegalStateException
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }

}
