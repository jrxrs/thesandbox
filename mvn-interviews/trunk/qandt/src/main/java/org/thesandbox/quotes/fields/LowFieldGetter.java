package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;

import java.math.BigDecimal;

public class LowFieldGetter extends BigDecimalGetter {

    @Override
    public BigDecimal getField(EodQuote eodQuote) {
        return eodQuote.getLow();
    }

    @Override
    public String getFieldName() {
        return "Low";
    }
}
