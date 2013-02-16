package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;

import java.math.BigDecimal;

public class CloseFieldGetter extends BigDecimalGetter {

    @Override
    public BigDecimal getField(EodQuote eodQuote) {
        return eodQuote.getClose();
    }

    @Override
    public String getFieldName() {
        return "Close";
    }
}
