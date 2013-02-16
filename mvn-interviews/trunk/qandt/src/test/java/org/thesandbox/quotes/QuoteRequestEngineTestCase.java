package org.thesandbox.quotes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuoteRequestEngineTestCase {

    private QuoteRequestEngine engine;

    @Before
    public void setUp() throws Exception {
        List<String> symbols = new ArrayList<String>(10);
        symbols.add("LSE.L");
        symbols.add("VOD.L");
        symbols.add("BATS.L");
        symbols.add("BP.L");
        symbols.add("HSBA.L");
        symbols.add("HILS.L");
        symbols.add("BT-A.L");
        symbols.add("^FTSE");
        symbols.add("GOO.L");
        symbols.add("GOOG");
        engine = new QuoteRequestEngine(symbols);
    }

    @Test
    public void testBegin() throws Exception {
        engine.begin();
    }

}
