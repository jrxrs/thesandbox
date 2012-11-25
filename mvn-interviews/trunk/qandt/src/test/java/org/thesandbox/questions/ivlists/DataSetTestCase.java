package org.thesandbox.questions.ivlists;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List<Double> list = [5.0,3.0,6.0,9.0,2.0,8.0,1.0]
 * List<List<Double>> list = [[5.0,3.0,6.0][9.0,2.0,8.0][1.0]];
 */
public class DataSetTestCase {

    private final Double[] doubles = new Double[] {5d, 3d, 6d, 9d, 2d, 8d, 1d};
    private final List<Double> sl1 = Arrays.asList(doubles[0], doubles[1], doubles[2]);
    private final List<Double> sl2 = Arrays.asList(doubles[3], doubles[4], doubles[5]);
    private final List<Double> sl3 = Arrays.asList(doubles[6]);

    @SuppressWarnings("unchecked")
    private final List<List<Double>> ll = Arrays.asList(sl1, sl2, sl3);

    @Test
    public void testDataSetImplementations() {
        DoubleListDataSet doubleListDataSet = new DoubleListDataSet(ll);

        for (int i = 0; i < doubles.length; i++) {
            Assert.assertEquals((Object)doubles[i], doubleListDataSet.getPoint(i));
        }

        List<Double> list = new ArrayList<Double>();
        for (int i = 0; i < doubleListDataSet.getNumberOfPoints(); i++) {
            list.add(doubleListDataSet.getPoint(i));
        }

        DoubleDataSet doubleDataSet = new DoubleDataSet(list);
        for (int i = 0; i < doubles.length; i++) {
            Assert.assertEquals((Object)doubles[i], doubleDataSet.getPoint(i));
        }
    }

    @Test
    public void testDoubleDataSetGetNumberOfPoints() throws Exception {
        DoubleDataSet doubleDataSet = new DoubleDataSet(sl1);
        Assert.assertEquals(3, doubleDataSet.getNumberOfPoints());
    }

    @Test
    public void testDoubleDataSetGetPoint() {
        DoubleDataSet doubleDataSet = new DoubleDataSet(sl2);
        Assert.assertEquals(new Double(2), (Double)doubleDataSet.getPoint(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
     public void testDoubleDataSetGetPointNeg() {
        DoubleDataSet doubleDataSet = new DoubleDataSet(sl2);
        doubleDataSet.getPoint(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDoubleDataSetGetPointRange() {
        DoubleDataSet doubleDataSet = new DoubleDataSet(sl2);
        doubleDataSet.getPoint(5);
    }

    @Test
    public void testDoubleListDataSetGetNumberOfPoints() {
        DoubleListDataSet doubleListDataSet = new DoubleListDataSet(ll);
        Assert.assertEquals(7, doubleListDataSet.getNumberOfPoints());
    }

    @Test
    public void testDoubleListDataSetGetPoint() {
        DoubleListDataSet doubleListDataSet = new DoubleListDataSet(ll);
        Assert.assertEquals(new Double(2), (Double)doubleListDataSet.getPoint(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDoubleListDataSetGetPointNeg() {
        DoubleListDataSet doubleListDataSet = new DoubleListDataSet(ll);
        doubleListDataSet.getPoint(-4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDoubleListDataSetGetPointRange() {
        DoubleListDataSet doubleListDataSet = new DoubleListDataSet(ll);
        doubleListDataSet.getPoint(15);
    }
}
