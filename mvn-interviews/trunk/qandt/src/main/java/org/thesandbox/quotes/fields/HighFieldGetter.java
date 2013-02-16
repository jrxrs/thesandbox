package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;

import java.math.BigDecimal;

public class HighFieldGetter extends BigDecimalGetter {

    @Override
    public BigDecimal getField(EodQuote eodQuote) {
        return eodQuote.getHigh();
    }

    @Override
    public String getFieldName() {
        return "High";
    }
}
