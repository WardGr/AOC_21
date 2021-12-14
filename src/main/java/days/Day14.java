package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day14 implements Day {

    private String polymer;
    private Map<String, Long> pairs;
    private final Map<String, String> template = new HashMap<>();
    private final Map<String, Long> frequencies = new HashMap<>();

    public Day14(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            polymer = br.readLine();
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                template.put(line.split(" -> ")[0], line.split(" -> ")[1]);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Input invalid!");
        }
    }

    @Override
    public Object part1() {
        String backup = new String(polymer);
        exec(10);
        calculateFrequencies();
        long max = frequencies.values().stream().max(Long::compareTo).get();
        long min = frequencies.values().stream().min(Long::compareTo).get();
        polymer = backup;
        return max-min;
    }

    @Override
    public Object part2() {
        calc(40);
        return calcResult();
    }

    public void exec(int times) {
        if (times == 0)
            return;
        StringBuilder newString = new StringBuilder();
        char[] chars = polymer.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            newString.append(chars[i]);
            String pair = polymer.substring(i, i + 2);
            newString.append(template.get(pair));
        }
        newString.append(chars[chars.length-1]);
        polymer = newString.toString();
        exec(times-1);
    }

    public void calculateFrequencies() {
        for (int i = 0; i < polymer.length(); i++) {
            String character = polymer.substring(i, i + 1);
            frequencies.put(character, frequencies.getOrDefault(character, 0L) + 1);
        }
    }

    public void calc(int times) {
        pairs = new HashMap<>();
        // Add all pairs from the original string
        // In this method we won't be updating the string,
        // only keeping the pairs in a map
        for (int i = 0; i < polymer.length() - 1; i++) {
            String pair = polymer.substring(i, i + 2);
            pairs.put(pair, pairs.getOrDefault(pair, 0L) + 1);
        }

        for (int i = 0; i < times; i++) {
            Map<String, Long> newPairs = new HashMap<>();
            for (String pair : pairs.keySet()) {
                String mid = template.get(pair);
                String first = pair.charAt(0) + mid, snd = mid + pair.charAt(1);
                // pairs.get(pair) because it might have SN 3 times, we want the count to be 3 times aswell
                newPairs.put(first, newPairs.getOrDefault(first, 0L) + pairs.get(pair));
                newPairs.put(snd, newPairs.getOrDefault(snd, 0L) + pairs.get(pair));
            }
            pairs = newPairs;
        }
    }

    public long calcResult()
    {
        Map<Character, Long> counts = new HashMap<>();

        for(String pair : pairs.keySet()) {
            char first = pair.charAt(0);
            char snd = pair.charAt(1);
            counts.put(first, counts.getOrDefault(first, 0L) + pairs.get(pair));
            counts.put(snd, counts.getOrDefault(snd, 0L) + pairs.get(pair));
        }

        long max = counts.values().stream().max(Long::compareTo).get();
        long min = counts.values().stream().min(Long::compareTo).get();
        if ((isEven(max) && isEven(min))) {
            return (max - min) /2;
        } else if (isEven(max) && isUneven(min))
            return (max - min) /2;
        else
            return (max/2) - (min/2) +1;
    }

    public boolean isEven(long x) {
        return x%2 == 0;
    }

    public boolean isUneven(long x) {
        return x%2 != 0;
    }
}
