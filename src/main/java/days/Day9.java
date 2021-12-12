package main.java.days;

import main.java.utilities.Utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day9 implements Day {

    private int[][] map;
    private List<Point> lowPoints = new ArrayList<>();

    public Day9(String file) {
        // Worst possible way to get sizes, will fix
        List<String> strings = Utils.fileToList(file);
        map = new int[strings.size()][strings.get(0).toCharArray().length];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (int col = 0; col < chars.length; col++) {
                    map[row][col] = Integer.parseInt(String.valueOf(chars[col]));
                }
                row++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object part1() {
        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (check(row, col))
                    count += map[row][col] + 1;
            }
        }
        return count;
    }

    private  boolean check(int row, int col) {
        int current = map[row][col];
        if (row-1 >= 0 && map[row-1][col] <= current)
            return false;
        if (row+1 < map.length && map[row+1][col] <= current)
            return false;
        if (col-1 >= 0 && map[row][col-1] <= current)
            return false;
        if (col+1 < map[0].length && map[row][col+1] <= current)
            return false;
        lowPoints.add(new Point(row, col));
        return true;
    }

    List<Point> visited, toVisit;
    @Override
    public Object part2() {
        List<Long> bassins = new ArrayList<>();
        for (Point p : lowPoints) {
            visited = new ArrayList<>();
            toVisit = new ArrayList<>();
            visited.add(new Point(p.getX(), p.getY()));
            addNeighs(p);
            while (!toVisit.isEmpty()) {
                Point next = toVisit.remove(0);
                visited.add(next);
                addNeighs(next);
            }
            bassins.add((long) visited.size());
        }
        bassins.sort(Long::compare);
        return bassins.get(bassins.size() - 1) * bassins.get(bassins.size() - 2) * bassins.get(bassins.size() - 3);
    }

    private void addNeighs(Point p) {
        int x = p.getX(), y = p.getY();
        if (x-1 >= 0 && (map[x-1][y] != 9) &&
                !toVisit.contains(new Point(x-1, y)) &&
                !visited.contains(new Point(x-1, y)))
            toVisit.add(new Point(x-1, y));
        if (x+1 < map.length && (map[x+1][y] != 9) &&
                !toVisit.contains(new Point(x+1, y)) &&
                !visited.contains(new Point(x+1, y)))
            toVisit.add(new Point(x+1, y));
        if (y-1 >= 0 && map[x][y-1] != 9 &&
                !toVisit.contains(new Point(x, y-1)) &&
                !visited.contains(new Point(x, y-1)))
            toVisit.add(new Point(x, y-1));
        if (y+1 < map[0].length && map[x][y+1] != 9 &&
                !toVisit.contains(new Point(x, y+1)) &&
                !visited.contains(new Point(x, y+1)))
            toVisit.add(new Point(x, y+1));
    }



    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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
