package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class NewDay10 implements Day {

    private List<char[]> part1 = new ArrayList<>();
    private List<char[]> part2 = new ArrayList<>();
    private final String openings = "<({[";
    private final String closings = ">)]}";

    public NewDay10(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Originally the idea was to split into corrupt and unfinished.
                    part1.add(line.toCharArray());
                    part2.add(line.toCharArray());
            }
        } catch (IOException e) { throw new IllegalArgumentException(); }
    }

    private boolean isOpen(char x) {
        return openings.contains(Character.toString(x));
    }

    private int getValue(char x) {
        return switch (x) {
            case '>' -> 25137;
            case ')' -> 3;
            case '}' -> 1197;
            case ']' -> 57;
            default -> throw new IllegalArgumentException("unknown type:" + x);
        };
    }

    private boolean checkCorrect(char x, char y) {
        return switch (x) {
            case '<' -> y == '>';
            case '(' -> y == ')';
            case '{' -> y == '}';
            case '[' -> y == ']';
            default -> throw new IllegalArgumentException("unknown type:" + x + ":" + y);
        };
    }

    @Override
    public Object part1() {
        Stack<Character> stack;
        int sum = 0;
        for(char[] chars : part1) {
            stack = new Stack();
            for (char aChar : chars) {
                if (isOpen(aChar))
                    stack.add(aChar);
                else {
                    if (!(checkCorrect(stack.pop(), aChar))) {
                        sum += getValue(aChar);
                        break;
                    }
                }
            }
        }
        return sum;
    }

    private int part2Value(char x) {
        return switch (x) {
            case '<' -> 4;
            case '(' -> 1;
            case '{' -> 3;
            case '[' -> 2;
            default -> throw new IllegalArgumentException("unknown type:" + x);
        };
    }


    @Override
    public Object part2() {
        Stack<Character> stack;
        List<Long> scores = new ArrayList();
        for(char[] chars : part2) {
            long currSum = 0;
            stack = new Stack();
            for (char aChar : chars) {
                if (isOpen(aChar))
                    stack.add(aChar);
                else {
                    if (!(checkCorrect(stack.pop(), aChar))) {
                        // We are in a case from part 1: just empty stack and break;
                        stack = new Stack();
                        break;
                    }
                }
            }
            // Now we have remaining characters left;
            while(!(stack.isEmpty())) {
                currSum *= 5;
                currSum += part2Value(stack.pop());
            }
            // currSum is 0 when corrupt, but we want avg of unfinished
            if (currSum != 0)
                scores.add(currSum);
        }
        scores = scores.stream().sorted().collect(Collectors.toList());
        return scores.get(scores.size()/2);
    }
}
