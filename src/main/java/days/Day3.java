package main.java.days;

import main.java.utilities.Utils.Utils;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Day3 implements Day {

    private int[] posBitCount;
    private int[] negBitCount;
    private String gammaRate = "";
    private String epsilonRate = "";

    private String file;

    public Day3 (String file) {
        this.file = file;
    }


    @Override
    public Object part1() {
        List<String> lines = Utils.fileToList(file);
        int length = lines.get(0).length();
        posBitCount = new int[length];
        negBitCount = new int[length];
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
        for (String line: lines) {
            String[] splitted = line.split("");
            int i = 0;
            for (String letter : splitted) {
                if (letter.equals("1"))
                    posBitCount[i]++;
                else
                    negBitCount[i]++;
                i++;
            }
        }
        for (int i = 0; i < posBitCount.length; i++) {
            if (posBitCount[i] > negBitCount[i]) {
                gammaRate += "1";
                epsilonRate += "0";
            } else {
                gammaRate += "0";
                epsilonRate += "1";
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
        for (String line: lines) {
            String[] splitted = line.split("");
            if (splitted[idx].equals("1"))
                count[1]++;
            else
                count[0]++;
        }
        return count;
    }

    private LinkedList<String> addOnes(LinkedList<String> adaptedLines, List<String> lines, int idx) {
        for (String line : lines) {
            String[] splitted = line.split("");
            if (splitted[idx].equals("1"))
                adaptedLines.add(line);
        }
        return adaptedLines;
    }

    private LinkedList<String> addZeros(LinkedList<String> adaptedLines, List<String> lines, int idx) {
        for (String line : lines) {
            String[] splitted = line.split("");
            if (splitted[idx].equals("0"))
                adaptedLines.add(line);
        }
        return adaptedLines;
    }

    private int calculate() {

        return turnToInt(gammaRate).intValue() * turnToInt(epsilonRate).intValue();
    }

    private BigInteger turnToInt(String binary) {
        return (new BigInteger(binary, 2));
    }
}
