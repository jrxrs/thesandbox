package org.thesandbox.quotes;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.math.BigDecimal;

public class EodQuote implements Comparable<EodQuote> {

    private static final DateTimeFormatter dtf = ISODateTimeFormat.date();

    private final String symbol;
    private final LocalDate date;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;
    private final BigDecimal volume;
    private final BigDecimal adjClose;

    public EodQuote(String symbol,
                    String date,
                    String open,
                    String high,
                    String low,
                    String close,
                    String volume,
                    String adjClose) {
        this.symbol = symbol;
        this.date = LocalDate.parse(date, dtf);
        this.open = new BigDecimal(open);
        this.high = new BigDecimal(high);
        this.low = new BigDecimal(low);
        this.close = new BigDecimal(close);
        this.volume = new BigDecimal(volume);
        this.adjClose = new BigDecimal(adjClose);
    }

    public String getSymbol() {
        return symbol;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    @Override
    public int compareTo(EodQuote o) {
        if (symbol.equals(o.getSymbol())) {
            return date.compareTo(o.getDate());
        } else return symbol.compareTo(o.getSymbol());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof EodQuote) {
            EodQuote otherEodQuote = (EodQuote)other;
            return symbol.equals(otherEodQuote.getSymbol()) && date.equals(otherEodQuote.getDate());

        } else return false;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + symbol.hashCode();
        hash = hash * 31 + date.hashCode();

        return hash;
    }

}
