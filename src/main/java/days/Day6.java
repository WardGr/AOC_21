package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 implements Day {

    // This class was made because Day6.java is very inefficient. Part2 did not work because of not enough memory.

    private Map<Integer, Long> fish = new HashMap<>();
    private long answer;
    private final String file;

    public Day6(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        getInput();
        iterateDays(80);
        return answer;
    }

    @Override
    public Object part2() {
        getInput();
        iterateDays(256);
        return answer;
    }

    private void iterateDays(int days) {
        for (int day = 0; day < days; day++) {
            long buffer = fish.get(0);
            for (int i = 0; i < 8; i++) {
                fish.put(i, fish.get(i+1));
            }
            fish.put(6, fish.get(6) + buffer);
            fish.put(8, buffer);
        }
        answer = fish.values().stream().mapToLong(x -> x).sum();
    }

    private void getInput() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            fish = Arrays.stream(line.trim().split(",")).collect(Collectors.groupingBy(Integer::parseInt, Collectors.counting()));
            IntStream.range(0, 9).forEach(day -> fish.putIfAbsent(day, 0L));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
