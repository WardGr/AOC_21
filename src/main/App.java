package main;

import main.java.days.Day;
import main.java.days.Day1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("AoC 2021");
        Day day1 = new Day1();

        ArrayList<Day> days = new ArrayList<Day>();
        days.add(day1);

        for (Day day : days) {
            System.out.println("Part 1:");
            System.out.println("Amt of increases: " + day.part1());

            System.out.println("Part 2:");
            System.out.println("Amt of increases: " + day.part2());
        }
    }
}
