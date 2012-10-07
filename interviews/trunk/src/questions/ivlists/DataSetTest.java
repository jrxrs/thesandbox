package questions.ivlists;

import java.util.List;
import java.util.ArrayList;

/**
 * List<Double> list = [5.0.0,3.0,6.0,9.0,2.0,8.0,1.0]
 * List<List<Double>> list = [[5.0,3.0,6.0][9.0,2.0,8.0][1.0]];
 */
public class DataSetTest
{
    public static void main(String[] args) {
        List<Double> sl1 = new ArrayList<Double>();
        sl1.add(5d);
        sl1.add(3d);
        sl1.add(6d);

        List<Double> sl2 = new ArrayList<Double>();
        sl2.add(9d);
        sl2.add(2d);
        sl2.add(8d);

        List<Double> sl3 = new ArrayList<Double>();
        sl3.add(1d);

        List<List<Double>> ll = new ArrayList<List<Double>>();
        ll.add(sl1);
        ll.add(sl2);
        ll.add(sl3);

        DoubleListDataSet dlds = new DoubleListDataSet(ll);
        List<Double> list = new ArrayList<Double>();
        System.out.println("Printing DoubleListDataSet:");
        for (int i = 0; i < dlds.getNumnerOfPoints(); i++) {
            System.out.println(i + " = " + dlds.getPoint(i));
            list.add(dlds.getPoint(i));
        }
//        System.out.println(-1 + " = " + dlds.getPoint(-1));
//        System.out.println(7 + " = " + dlds.getPoint(7));
//        System.out.println(9 + " = " + dlds.getPoint(9));

        DoubleDataSet dds = new DoubleDataSet(list);
        System.out.println();
        System.out.println("Printing DoubleDataSet:");
        for (int i = 0; i < dds.getNumnerOfPoints(); i++) {
            System.out.println(i + " = " + dlds.getPoint(i));
        }
//        System.out.println(-1 + " = " + dds.getPoint(-1));
//        System.out.println(7 + " = " + dds.getPoint(7));
//        System.out.println(9 + " = " + dds.getPoint(9));
    }
}
