package org.thesandbox.quotes;

import java.util.Collection;
import java.util.concurrent.*;

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
