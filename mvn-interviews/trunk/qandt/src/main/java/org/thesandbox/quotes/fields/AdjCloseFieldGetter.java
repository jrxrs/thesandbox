package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;

import java.math.BigDecimal;

public class AdjCloseFieldGetter extends BigDecimalGetter {

    @Override
    public BigDecimal getField(EodQuote eodQuote) {
        return eodQuote.getAdjClose();
    }

    @Override
    public String getFieldName() {
        return "AdjClose";
    }

}
