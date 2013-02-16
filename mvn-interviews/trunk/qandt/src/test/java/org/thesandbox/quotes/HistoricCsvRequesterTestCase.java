package org.thesandbox.quotes;

import org.junit.Before;
import org.junit.Test;

public class HistoricCsvRequesterTestCase {

    private HistoricCsvRequester request;

    @Before
    public void setUp() throws Exception {
        request = new HistoricCsvRequester("LSE.L");
    }

    @Test
    public void testRun() throws Exception {
        request.call();
    }

}
