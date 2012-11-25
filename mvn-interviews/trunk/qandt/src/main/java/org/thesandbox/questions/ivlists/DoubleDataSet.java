package org.thesandbox.questions.ivlists;

import java.util.List;

/**
 *
 */
public class DoubleDataSet implements DataSet
{
    private final List<Double> origList;

    public DoubleDataSet(List<Double> list) {
        origList = list;
    }

    @Override
    public int getNumberOfPoints() {
        return origList.size();
    }

    @Override
    public double getPoint(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= getNumberOfPoints()) {
            throw new IndexOutOfBoundsException();
        }

        return origList.get(index);
    }
}
