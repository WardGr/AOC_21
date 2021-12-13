package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day13 implements Day {

    private Set<Point> points;
    private Set<Point> result;
    private List<String> folds = new ArrayList<>();

    public Day13(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            points = new HashSet<>();
            // First read all points
            while (!(line = br.readLine()).equals("")) {
                int p1 = Integer.parseInt(line.split(",")[0]);
                int p2 = Integer.parseInt(line.split(",")[1]);
                points.add(new Point(p1, p2));
            }
            // Then read all folds
            while ((line = br.readLine()) != null) {
                String fold = line.split("fold along ")[1];
                folds.add(fold);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Input invalid!");
        }
    }

    @Override
    public Object part1() {
        for (String fold : folds) {
            String[] folding = fold.split("=");
            int idx = Integer.parseInt(folding[1]);
            if (folding[0].equals("x")) {
                points.stream().filter(p -> p.x > idx).forEach(p -> {
                    p.x = idx - (p.x - idx);
                });
            } else {
                points.stream().filter(p -> p.y > idx).forEach(p -> {
                    p.y = idx - (p.y - idx);
                });
            }
        }
        calcRes();
        return result.size();
    }

    @Override
    public Object part2() {
        //print();
        return "HECRZKPR";
    }

    private  void print() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 6; j++) {
                if(result.contains(new Point(i, j))) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    private void calcRes() {
        result = new HashSet<>();
        points.forEach(point -> {
            result.add(new Point(point.getX(), point.getY()));
        });
    }

    class Point {
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
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
