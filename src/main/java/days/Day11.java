package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day11 implements Day {

    private int[][] energy = new int[100][100];
    private long result;
    private List<tuple> tuples = new ArrayList<tuple>();
    private String file;

    public Day11(String file) {
        this.file = file;
        makeInput(file);
    }

    private void makeInput(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                char[] chars = line.toCharArray();
                for (int j = 0; j < 10; j++) {
                    energy[i][j] = Integer.parseInt(String.valueOf(chars[j]));
                }
                i++;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object part1() {
        result = 0;
        for (int loop = 0; loop < 100; loop++) {
            tuples = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    update(i, j);
                }
            }
        }
        return result;
    }

    @Override
    public Object part2() {
        makeInput(file);
        int count = 0;
        while (true) {
            result = 0;
            count++;
            tuples = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    update(i, j);
                }
            }
            if (result == 100)
                break;
        }
        return count;
    }

    private void update(int i, int j) {
        if (tuples.contains(new tuple(i, j)))
            return;
        if (++energy[i][j] > 9)
            flash(i, j);
    }

    private void flash(int i, int j) {
        result++;
        energy[i][j] = 0;
        tuples.add(new tuple(i, j));
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (x == 0 && y == 0) {
                }
                else if (isValid(i+x, j+y))
                    update(i+x, j+y);
            }
        }
    }

    private boolean isValid(int i, int j) {
        return (i >= 0 && j >= 0 && i < 10 && j < 10 && !tuples.contains(new tuple(i, j)));
    }

    private class tuple {
        int x;
        int y;
        public tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            tuple tuple = (tuple) o;
            return x == tuple.x && y == tuple.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
