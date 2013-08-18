package org.thesandbox.f1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FormulaOne {

    public static final String BGC_REGEX = "\\|bgcolor\\=\\\".*\\\"\\|";
    public static final String CATCH_REST_REGEX = "(\\{\\{.*\\}\\}|<.*>|(align|valign)\\=\\\".*\\\"\\||valign\\=\\\".*\\\"|'|&nbsp;)";

    private boolean compileChars = false;

    private Set<Character> uniqueChars = new HashSet<Character>();

    private enum PointsSystem {
        _1981_1990(1981, 1990, Arrays.asList(0, 9, 6, 4, 3, 2, 1)),
        _1991_2002(1991, 2002, Arrays.asList(0, 10, 6, 4, 3, 2, 1)),
        _2003_2009(2003, 2009, Arrays.asList(0, 10, 8, 6, 5, 4, 3, 2, 1)),
        _2010_9999(2010, 9999, Arrays.asList(0, 25, 18, 15, 12, 10, 8, 6, 4, 2, 1));

        private final int from;
        private final int to;
        private final List<Integer> points;

        private PointsSystem(int from, int to, List<Integer> points) {
            this.from = from;
            this.to = to;
            this.points = points;
        }

        public boolean inRange(int year) {
            return year >= from && year <= to;
        }

        @Override
        public String toString() {
            return from + "-" + to;
        }
    }

    public PointsSystem getOriginalPointsSystem(int year) {
        for (PointsSystem ps : PointsSystem.values()) {
            if (ps.inRange(year)) {
                return ps;
            }
        }
        return null;
    }

    private String prepare(String fileName) {
        String contents = "";
        try {
            contents = load(fileName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents.replaceAll(BGC_REGEX, "|").replaceAll(CATCH_REST_REGEX, "");
    }

    private String load(String fileName) throws URISyntaxException, IOException {
        @SuppressWarnings("all")
        File f = new File(FormulaOne.class.getClassLoader().getResource(fileName).toURI());
        if (f.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line, ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(ls);
            }

            reader.close();

            return sb.toString();
        } else return "";
    }

    private class DriverAndResult implements Comparable<DriverAndResult> {
        protected final String driver;
        protected final int result;

        public DriverAndResult(String driver, int result) {
            this.driver = driver;
            this.result = result;
        }

        @Override
        public String toString() {
            return driver + " - " + result;
        }

        @Override
        public int compareTo(DriverAndResult o) {
            if (result == o.result) {
                return driver.compareTo(o.driver);
            } else {
                return -(result - o.result);
            }
        }
    }

    private class OrigDriverAndResult extends DriverAndResult {
        public OrigDriverAndResult(String driver, int result) {
            super(driver, result);
        }

        @Override
        public String toString() {
            return result + " - " + driver;
        }
    }

    private DriverAndResult driverAndResultFactory(boolean orig, String driver, int result) {
        return orig ? new OrigDriverAndResult(driver, result) : new DriverAndResult(driver, result);
    }

    private void process(int season, String fileName) {
        LinkedHashMap<String, LinkedList<Integer>> rawResults = new LinkedHashMap<String, LinkedList<Integer>>();

        String[] rows = prepare(fileName).split("\\|\\-");
        for (int i = 2; i < rows.length - 1; i++) {
            DriverWithResults dwr = processRow(rows[i]);
            rawResults.put(dwr.driver, dwr.results);
        }

        PointsSystem ops = getOriginalPointsSystem(season);
        DriverAndResult[] original = calculateChampionship(true, ops, rawResults);

        for (PointsSystem ps : PointsSystem.values()) {
            if (ops == ps) continue;
            System.out.println("The " + season + " F1 Championship under the " + ps + " Points System | Pos | " +
                               "The same F1 Championship under the original " + ops + " Points System");
            DriverAndResult[] championship = calculateChampionship(false, ps, rawResults);

            for (int i = 0; i < championship.length; i++) {
                System.out.printf(String.format("%58s | %2d. | %s\n", championship[i], i + 1, original[i]));
            }
            System.out.println();
        }
    }

    private DriverAndResult[] calculateChampionship(boolean orig, PointsSystem ps, LinkedHashMap<String, LinkedList<Integer>> rawResults) {
        DriverAndResult[] championship = new DriverAndResult[rawResults.size()];
        int cIdx = 0;
        for (Map.Entry<String, LinkedList<Integer>> entry : rawResults.entrySet()) {
            int runningTotal = 0;
            for (Integer i : entry.getValue()) {
                if (i < ps.points.size()) {
                    runningTotal += ps.points.get(i);
                }
            }
            championship[cIdx++] = driverAndResultFactory(orig, entry.getKey(), runningTotal);
        }

        Arrays.sort(championship);

        return championship;
    }

    private class DriverWithResults {
        private final String driver;
        private final LinkedList<Integer> results;

        public DriverWithResults(String driver, LinkedList<Integer> results) {
            this.driver = driver;
            this.results = results;
        }
    }

    private DriverWithResults processRow(String row) {
        String[] cols = row.split("\\|");
        if (compileChars) {
            for (Character c : cols[1].toCharArray()) {
                uniqueChars.add(c);
            }
        }
        String driver = cols[1].replaceAll("[^A-Za-zÉáäéíóôöüš\\(\\)\\-\\s]", "").trim();

        LinkedList<Integer> results = new LinkedList<Integer>();
        for (int i = 2; i < cols.length - 1; i++) {
            try {
                results.add(Integer.parseInt(cols[i].trim()));
            } catch (NumberFormatException nfe) {
                results.add(0);
            }
        }

        return new DriverWithResults(driver, results);
    }

    private void dumpCharMap() {
        if (compileChars) {
            List<Character> chars = new ArrayList<Character>(uniqueChars);
            Collections.sort(chars);
            for (char c : chars) {
                System.out.print(c);
            }
        }
    }

    public static void main(String[] args) {
        FormulaOne f1 = new FormulaOne();

        for (int i = 1990; i < 2013; i++) {
            f1.process(i, "F1 - " + i + ".txt");
        }

        f1.dumpCharMap();
    }

}
