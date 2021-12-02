package main.java.days;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 implements Day {

    private int increased;
    private Scanner input;
    private List<Integer> allInts;

    @Override
    public int part1() {
        this.increased = 0;
        makeList();

        for (int i = 0; i < allInts.size() - 1; i++) {
            // TODO increased = allInts.get(i) < allInts.get(i + 1) ? increased : increased++;
            int curr = allInts.get(i);
            int next = allInts.get(i+1);
            if ((curr < next))
                increased++;
        }
        /*int prev = input.nextInt(), curr = input.nextInt();
        while (input.hasNextInt()) {
            if ((curr > prev))
                increased++;
            prev = curr;
            curr = input.nextInt();
        }
        if ((curr > prev))
            increased++;*/
        return increased;
    }



    @Override
    public int part2() {
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
            //Scanner input = new Scanner(new FileReader("/Users/wardgrosemans/Desktop/AOC_2021/day1/src/input.txt"));
            //Scanner input = new Scanner(new FileReader("src/main/Resources/inputDay1.txt"));
            this.input = new Scanner(new FileReader("src/main/Resources/inputDay1.txt"));
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
