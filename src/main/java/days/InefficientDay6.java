package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class InefficientDay6 implements Day {
    // Code is fine for part 1 but way too inefficient for part2

    private String file;
    private List<Integer> fishes = new ArrayList<>();
    private long[] fishies;

    public InefficientDay6(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        getInput();
        calculateDays(80);
        return fishes.size();
    }

    @Override
    public Object part2() {
        getInput();
        calculateDays(256);
        return fishes.size();
    }

    public void getInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            String[] fishString = line.split(",");
            Arrays.stream(fishString).forEach(fish -> fishes.add(Integer.parseInt(fish)));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    // Extremely inefficient, might fix
    public void calculateDays(int days) {
        for (int i = 0; i < days; i++) {
            int amt = fishes.size();
            int currFish;
            for (int fish = 0; fish < amt; fish++) {
                if (fishes.get(fish) == 0) {
                    fishes.set(fish, 6);
                    fishes.add(8);
                } else {
                    currFish = fishes.get(fish);
                    fishes.set(fish, currFish -1);
                }
            }
        }
    }
}
