package main.java.days;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 implements Day {

    private int increased;
    private Scanner input;
    private List<Integer> allInts;
    private String file;

    public Day1(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        this.increased = 0;
        makeList();

        for (int i = 0; i < allInts.size() - 1; i++) {
            increased = allInts.get(i) > allInts.get(i + 1) ? increased : (increased + 1);
        }
        return increased;
    }

    @Override
    public Object part2() {
        this.increased = 0;
        makeList();

        int size = allInts.size();
        int current, next;
        for (int i = 0; i < size ; i += 1) {
            if (i + 4 > size)
                break;
            current = allInts.get(i) + allInts.get(i+1) + allInts.get(i+2);
            next = allInts.get(i+1) + allInts.get(i+2) + allInts.get(i+3);
            if ((current < next))
                increased++;
        }
        return increased;
    }

    private void makeScanner() {
        try {

            this.input = new Scanner(new FileReader(file));
        } catch(Exception e) { throw new RuntimeException(e);}
    }

    private void makeList() {
        makeScanner();
        this.allInts = new ArrayList<>();
        while (input.hasNextInt()) {
            this.allInts.add(input.nextInt());
        }
    }
}
