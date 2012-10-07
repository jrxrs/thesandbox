package questions.ivlists;

/**
 *
 */
public interface DataSet
{
    public int getNumnerOfPoints();

    public double getPoint(int index) throws IndexOutOfBoundsException;
}
