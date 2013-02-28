package questions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * What happens if you attempt to reinsert an existing key into a map?
 */
public class MapPlay
{
    private static LinkedHashMap<String, Integer> lMap = new LinkedHashMap<String, Integer>();
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();

    public static void printMap(Map<String, Integer> m) {
        for (Map.Entry e : m.entrySet()) {
            System.out.println(e.getKey() + " maps to " + e.getValue());
        }
    }

    public static void printMaps() {
        System.out.println("Printing LinkedHashMap:");
        printMap(lMap);
        System.out.println("Printing HashMap:");
        printMap(map);
    }

    public static void main(String[] args) {
        String[] numbers = new String[] {"Zero", "One", "Two", "Three", "Four", "Five"};
        int i = 0;
        for (String num : numbers) {
            lMap.put(num, i);
            map.put(num, i);
            i++;
        }
        printMaps();

        lMap.put(numbers[3], 300);
        map.put(numbers[0], -1);
        printMaps();
    }
}
