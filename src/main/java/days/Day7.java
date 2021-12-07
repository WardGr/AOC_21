package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 implements Day {
    // Extremely inefficient, might fix later

    private String file;
    private Map<Integer, Long> positions = new HashMap<>();
    private Map<Integer, Long> costs = new HashMap<>();
    private int maxVal = 0;

    public Day7(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        defineInput();
        calculateP1();
        return getResult();
    }

    @Override
    public Object part2() {
        defineInput();
        calculateP2();
        return getResult();
    }

    public void calculateP2() {
        costs = new HashMap<>();
        long cost, currVal;
        for (int pos = 0; pos < maxVal; pos++) {
            cost = 0;
            for (Map.Entry<Integer, Long> crab : positions.entrySet()) {
                currVal = 0;
                for (int i = 1; i <= Math.abs(pos - crab.getKey()); i++)
                    currVal += i;
                cost += (currVal * crab.getValue());
            }
            costs.put(pos, cost);
        }
    }

    public void calculateP1() {
        long cost;
        /*for(Map.Entry<Integer, Long> i : positions.entrySet()) {
            int j = i.getKey();
            cost = 0;
            for (Map.Entry<Integer, Long> crab : positions.entrySet()) {
                cost += Math.abs(j - crab.getKey()) * crab.getValue();
            }
            costs.put(j, cost);
        }*/
        for (int pos = 0; pos < maxVal; pos++) {
            cost = 0;
            for (Map.Entry<Integer, Long> crab : positions.entrySet()) {
                cost += Math.abs(pos - crab.getKey()) * crab.getValue();
            }
            costs.put(pos, cost);
        }
    }

    public long getResult() {
        return Collections.min(costs.values());
    }

    public void defineInput() {
        try (BufferedReader br = new BufferedReader((new FileReader(file)))) {
            String line = br.readLine();
            String[] lineSplitted = line.trim().split(",");
            positions = Arrays.stream(lineSplitted).collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));
            // Calculate the max horizontal value, so that we can easily determine all points to be checked
            Arrays.stream(lineSplitted).forEach(val -> {
                if (Integer.parseInt(val) > maxVal)
                    maxVal = Integer.parseInt(val);
            });
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
