package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day10 implements Day {

    private List<char[]> characters = new ArrayList<>();
    private List<char[]> copy = new ArrayList<>(characters);
    private int sum;
    private int idx;

    public Day10(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null) {
                characters.add(line.toCharArray());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Object part1() {
        copy = new ArrayList<>(characters);
        for (int i = 0; i < characters.size(); i++) {
            boolean solved = false;
            while (!solved) {
                char close = findClose(i);
                char open = findOpen(i);
                if (close == 0 && open == 0) {
                    solved = true;
                } else if ((close == 0 && open != 0) || (close != 0 && open == 0)) {
                    /*System.out.println("open:" + open);
                    System.out.println("Close:" + close);
                    //System.out.println("Sum 1: " + findFirstFail(characters.get(i), i));
                    sum += findScore(close);*/
                    solved = true;
                } else if (close != open) {
                    sum += findScore(close);
                    solved = true;
                }
            }
        }
        return sum;
    }

    @Override
    public Object part2() {
        characters = new ArrayList<>(copy);
        sum = 0;
        List<char[]> incompletes = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            boolean solved = false;
            while (solved != true) {
                char close = findClose(i);
                char open = findOpen(i);
                if (close == 0 && open == 0) {
                    solved = true;
                } else if ((close == 0 && open != 0)) { // hier een systeem te vinden dat enkel de closeds gefixt worden met blabla
                    incompletes.add(copy.get(i));
                }
            }
        }
        for (int i = 0; i < incompletes.size(); i++) {
            int curr = countLoss('<', i) - countLoss('>', i); // Enkel tellen gaat niet, want de som wordt aangepast PER closing, dus echt kijken welke gecloset moet worden :(
            while (curr > 0) {
                sum *= 5;
                sum += part2score('>');
            }
            curr = countLoss('(', i) - countLoss(')', i);
            while (curr > 0) {
                sum *= 5 ;
                sum += part2score(')');
            }
            curr = countLoss('{', i) - countLoss('}', i);
            if (curr > 0) {
                sum *= 5;
                sum += curr * part2score('}');
            }
            curr = countLoss('[', i) - countLoss(']', i);
            if (curr > 0) {
                sum *= 5;
                sum += curr * part2score(']');
            }
        }
        return null;
    }

    private int countLoss(char x, int i) {
        int count = 0;
        for (char y : copy.get(i)) {
            if (y == x)
                count++;
        }
        return count;
    }

    public int part2score(char x) {
        switch (x) {
            case ')' -> {
                return 1;
            }
            case ']' -> {
                return 2;
            }
            case '}' -> {
                return 3;
            }
            case '>' -> {
                return 4;
            }
            default -> {
                return 0;
            }
        }
    }

    private char findClose(int i) {
        for (int j = 0; j < characters.get(i).length; j++) {
            if (characters.get(i)[j] == ')') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                idx = j;
                return ')';
            }
            if (characters.get(i)[j] == ']') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                idx = j;
                return ']';
            }
            if (characters.get(i)[j] == '}') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                idx = j;
                return '}';
            }
            if (characters.get(i)[j] == '>') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                idx = j;
                return '>';
            }
        }
        return 0;
    }

    private char findOpen(int i) {
        for (int j = idx; j >= 0; j--) {
            if (characters.get(i)[j] == '(') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                //return '(';
                return ')';
            }
            if (characters.get(i)[j] == '[') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                return ']';
            }
            if (characters.get(i)[j] == '{') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                return '}';
            }
            if (characters.get(i)[j] == '<') {
                characters.set(i, adaptIndex(characters.get(i), j, '2'));
                return '>';
            }
        }
        return 0;
    }

    public char[] adaptIndex(char[] chars, int j, char setTo) {
        chars[j] = setTo;
        return chars;
    }

    public int findScore(char x) {
        System.out.println(x);
        switch (x) {
            case ')' -> {
                return 3;
            }
            case ']' -> {
                return 57;
            }
            case '}' -> {
                return 1197;
            }
            case '>' -> {
                return 25137;
            }
            default -> {
                // Apparently we don't look at the ones that are unfinished, so return 10 to ensure no mistakes
                return 10;
            }
        }
    }
}
