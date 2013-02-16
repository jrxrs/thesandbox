package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;

import java.math.BigDecimal;

public class OpenFieldGetter extends BigDecimalGetter {

    @Override
    public BigDecimal getField(EodQuote eodQuote) {
        return eodQuote.getOpen();
    }

    @Override
    public String getFieldName() {
        return "Open";
    }
}
