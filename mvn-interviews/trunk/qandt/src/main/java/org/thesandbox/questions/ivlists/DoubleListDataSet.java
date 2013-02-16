package org.thesandbox.questions.ivlists;

import java.util.List;

/**
 * Now the original list must store a list of lists of type Double
 * For Example
 *
 * List<List<Double>> list = [[5,3,6][9,2,8][1]];
 *  - list.getNumberOfPoints() = 7
 *  - list.getPoint(1) = 3
 *  - list.getPoint(3) = 9
 *  - list.getPoint(6) = 1
 */
public class DoubleListDataSet implements DataSet {

    private final List<List<Double>> origList;

    public DoubleListDataSet(List<List<Double>> list) {
        origList = list;
    }

    @Override
    public int getNumberOfPoints() {
        int count = 0;
        for (List<Double> subList : origList) {
            count += subList.size();
        }
        return count;
    }

    @Override
    public double getPoint(int index) {
        boolean inRange = true;
        if (index < 0 || index >= getNumberOfPoints()) {
            inRange = false;
        }

        if (inRange) {
            for (List<Double> subList : origList) {
                if (index < subList.size()) {
                    return subList.get(index);
                }
                index -= subList.size();
            }
        }

        throw new IndexOutOfBoundsException();
    }

}
