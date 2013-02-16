package org.thesandbox.quotes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * http://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
 * http://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
 * http://code.google.com/p/yahoo-finance-managed/wiki/csvHistQuotesDownload
 */
public class HistoricCsvRequester implements Callable<Collection<EodQuote>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoricCsvRequester.class);

    private static final String CHARSET = "UTF-8";
    private static final String BASE_URL = "http://ichart.yahoo.com/table.csv?s=";
    private static final String EXPECTED_COLS = "Date,Open,High,Low,Close,Volume,Adj Close";
    private static final int EXPECTED_TOKENS = EXPECTED_COLS.split(",").length;

    private final String symbol;

    public HistoricCsvRequester(String symbol) {
        LOGGER.info("Creating {} for Symbol: {}", getClass().getSimpleName(), symbol);
        this.symbol = symbol;
    }

    @Override
    public Collection<EodQuote> call() throws Exception {
        LOGGER.info("Requesting quotes for {}", symbol);
        final long start = System.currentTimeMillis();

        final List<EodQuote> quotes = new ArrayList<EodQuote>();
        try {
            BufferedReader response = null;
            try {
                URLConnection connection = new URL(BASE_URL + symbol).openConnection();
                connection.setRequestProperty("Accept-Charset", CHARSET);

                response = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                /* Get the first line and check the column order is as expected */
                String columnHeaders = response.readLine();
                if (!EXPECTED_COLS.equals(columnHeaders)) {
                    LOGGER.error("Column Headers did not match! {}", columnHeaders);
                    return quotes;
                }

                /* Read the rest of the lines and create EodQuotes for each */
                String quoteLine;
                String[] quote;
                while ((quoteLine = response.readLine()) != null) {
                    quote = quoteLine.split(",");
                    if (quote.length != EXPECTED_TOKENS) {
                        LOGGER.warn("The received line didn't contain enough tokens: {}", quoteLine);
                    } else {
                        quotes.add(new EodQuote(symbol, quote[0], quote[1], quote[2],
                                                quote[3], quote[4], quote[5], quote[6]));
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        LOGGER.info("Completed request for {} in {}ms, resulting in {} days of data.", symbol, System.currentTimeMillis() - start, quotes.size());

        Collections.sort(quotes);
        return Collections.unmodifiableList(quotes);
    }

}
