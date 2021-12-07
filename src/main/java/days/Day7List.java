package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day7List implements Day {

    // Implementation is about 2.5 times as fast as map implementation.

    private List<Integer> positions = new ArrayList<>();
    private List<Integer> costs = new ArrayList<>();
    private int lowest, highest;
    private String file;

    public Day7List(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        getInput();
        IntStream.range(lowest, highest).forEach(position -> {
            int count = positions.stream().mapToInt(crab -> fuelStep(position, crab)).sum();
            costs.add(count);
        });
        return costs.stream().min(Integer::compareTo).orElse(0);
    }

    @Override
    public Object part2() {
        costs = new ArrayList<>();
        IntStream.range(lowest, highest).forEach(position -> {
            int count = positions.stream().mapToInt(crab -> fuelSteps(position, crab)).sum();
            costs.add(count);
        });
        return costs.stream().min(Integer::compareTo).orElse(0);
    }

    public void getInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            positions = Arrays.stream(line.trim().split(",")).map(Integer::parseInt).toList();
            lowest = positions.stream().min(Integer::compareTo).orElse(0);
            highest = positions.stream().max(Integer::compareTo).orElse(0);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public int fuelStep(int start, int stop) {
        return Math.abs(start - stop);
    }

    public int fuelSteps(int start, int stop) {
        // 1 + 2 + ... + n-1 + n = n(n+2)/2 :)
        int steps = Math.abs(start - stop);
        return ((steps * (steps + 1)) / 2);
    }
}
