package org.thesandbox.questions.ivlists;

/**
 *
 */
public interface DataSet
{
    public int getNumberOfPoints();

    public double getPoint(int index) throws IndexOutOfBoundsException;
}
