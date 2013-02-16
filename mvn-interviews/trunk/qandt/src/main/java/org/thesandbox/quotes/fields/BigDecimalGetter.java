package org.thesandbox.quotes.fields;

import org.thesandbox.quotes.EodQuote;
import org.thesandbox.quotes.EodQuoteFieldGetter;

import java.math.BigDecimal;

public abstract class BigDecimalGetter implements EodQuoteFieldGetter<BigDecimal> {

    @Override
    public abstract BigDecimal getField(EodQuote eodQuote);

}
