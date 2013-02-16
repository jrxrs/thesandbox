package org.thesandbox.quotes;

import java.math.BigDecimal;

public class BigDecimalResult {

    private final String symbol;
    private final String field;
    private final BigDecimal result;

    public BigDecimalResult(String symbol, String field, BigDecimal result) {
        this.symbol = symbol;
        this.field = field;
        this.result = result;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getField() {
        return field;
    }

    public BigDecimal getResult() {
        return result;
    }

}
