package main.java.days;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Day5 implements Day {

    private String file;
    private Map<NonEqualPoint, NonEqualPoint> points = new HashMap<>();
    private Map<Point, Integer> mappings = new HashMap();
    private int result;

    public Day5(String file) {
        this.file = file;
        mapInput();
    }

    @Override
    public Object part1() {
        calcHorizontalVertical();
        calcResult();
        return result;
    }

    @Override
    public Object part2() {
        mappings = new HashMap<>();
        calcHorizontalVertical();
        calcRest();
        calcResult();
        return result;
    }

    public void calcHorizontalVertical() {
        points.forEach((p1, p2) -> {
            if (p1.x == p2.x) {
                int max_y = Math.max(p1.y, p2.y);
                int min_y = Math.min(p1.y, p2.y);
                for (int i = min_y; i <= max_y; i++) {
                    Point p = new Point(p1.x,i);
                    mappings.put(p, mappings.getOrDefault(p, 0) + 1);
                }
            } else if (p1.y == p2.y) {
                int max_x = Math.max(p1.x, p2.x);
                int min_x = Math.min(p1.x, p2.x);
                for (int i = min_x; i <= max_x; i++) {
                    Point p = new Point(i, p1.y);
                    mappings.put(p, mappings.getOrDefault(p, 0) + 1);
                }
            }
        });
    }

    public void calcRest() {
        points.forEach((p1, p2) -> {
            if ((p1.y != p2.y) && (p1.x != p2.x))  {
                // y = ax + b
                int amt = Math.abs(p2.x - p1.x);
                int a = (p2.y - p1.y) / (p2.x - p1.x);
                int y, x, startx;
                int b = p2.y - a*p2.x;
                startx = Math.min(p1.x, p2.x);
                for (int i = 0; i <= amt; i++ ) {
                    x = startx + i;
                    y = a * x + b;
                    Point p = new Point(x, y);
                    mappings.put(p, mappings.getOrDefault(p, 0) + 1);
                }
            }
        });
    }

    public void mapInput() {
        String line;
        String[] lineParts;
        String[] sndLineParts;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                lineParts = line.split(" -> ");
                sndLineParts = lineParts[0].split(",");
                NonEqualPoint p1 = new NonEqualPoint(Integer.parseInt(sndLineParts[0]), Integer.parseInt(sndLineParts[1]));
                sndLineParts = lineParts[1].split(",");
                NonEqualPoint p2 = new NonEqualPoint(Integer.parseInt(sndLineParts[0]), Integer.parseInt(sndLineParts[1]));
                points.put(p1, p2);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        } }

    public void calcResult() {
        result = (int) mappings.entrySet().stream().filter(entry -> entry.getValue() > 1).count();
    }

    // Class so for example 0,9->2,9 and 0,9->5,9 are both mapped on points Map
    // Otherwise only the first one in the list is added
    public class NonEqualPoint {

        public int x;
        public int y;

        public NonEqualPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Point {

        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
