package main.java.days;

import main.java.utilities.Utils.Utils;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Day3 implements Day {

    private String gammaRate = "";
    private String epsilonRate = "";
    private String file;

    public Day3 (String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        List<String> lines = Utils.fileToList(file);
        mostFrequent(lines);
        return calculate();
    }

    @Override
    public Object part2() {
        List<String> lines = Utils.fileToList(file);
        gammaRate = oxigen(lines, 0).get(0);
        epsilonRate = scrubber(lines, 0).get(0);
        return calculate();
    }

    private void mostFrequent(List<String> lines) {
        for (int i = 0; i < lines.get(0).length(); i++) {
            int finalI = i;
            int ones = (int) lines.stream().filter(line -> line.charAt(finalI) == '1').count();
            int zeros = lines.size() - ones;

            if (ones > zeros) {
                gammaRate += '1';
                epsilonRate += '0';
            } else {
                gammaRate += '0';
                epsilonRate += '1';
            }
        }
    }

    private List<String> oxigen(List<String> lines, int idx) {
        if (lines.size() == 1) {
            gammaRate = lines.get(0);
        } else {
            LinkedList<String> adaptedLines = new LinkedList();
            int[] count = count(lines, idx);
            if ((count[1] == count[0]) || (count[1] > count[0])) {
                adaptedLines = addOnes(adaptedLines, lines, idx);
            } else {
                adaptedLines = addZeros(adaptedLines, lines, idx);
            }
            return oxigen(adaptedLines, idx + 1);
        }
        return lines;
    }

    private List<String> scrubber(List<String> lines, int idx) {
        if (lines.size() == 1) {
            epsilonRate = lines.get(0);
        } else {
            LinkedList<String> adaptedLines = new LinkedList();
            int[] count = count(lines, idx);
            if ((count[1] < count[0])) {
                adaptedLines = addOnes(adaptedLines, lines, idx);
            } else {
                adaptedLines = addZeros(adaptedLines, lines, idx);
            }
            return scrubber(adaptedLines, idx + 1);
        }
        return lines;
    }

    private int[] count(List<String> lines, int idx) {
        int[] count = new int[2];
        count[0] = (int) lines.stream().filter(line -> line.charAt(idx) == '0').count();
        count[1] = (int) lines.stream().filter(line -> line.charAt(idx) == '1').count();
        return count;
    }

    private LinkedList<String> addOnes(LinkedList<String> adaptedLines, List<String> lines, int idx) {
        lines.stream().forEach(line -> {
            if (line.charAt(idx) == '1')
                adaptedLines.add(line);
        });
        return adaptedLines;
    }

    private LinkedList<String> addZeros(LinkedList<String> adaptedLines, List<String> lines, int idx) {
        lines.stream().forEach(line -> {
            if (line.charAt(idx) == '0')
                adaptedLines.add(line);
        });
        return adaptedLines;
    }

    private int calculate() {
        return turnToInt(gammaRate).intValue() * turnToInt(epsilonRate).intValue();
    }

    private BigInteger turnToInt(String binary) {
        return (new BigInteger(binary, 2));
    }
}
