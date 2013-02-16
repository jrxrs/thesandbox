package org.thesandbox.quotes;

public interface EodQuoteFieldGetter<V> {

    public V getField(EodQuote eodQuote);

    public String getFieldName();

}
