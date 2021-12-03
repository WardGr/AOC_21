package main.java;

import main.java.days.Day;
import main.java.days.Day1;
import main.java.days.Day2;
import main.java.days.Day3;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("AoC 2021");
        Day day1 = new Day1("resources/inputDay1.txt");
        Day day2 = new Day2("resources/inputDay2.txt");
        Day day3 = new Day3("resources/inputDay3.txt");

        ArrayList<Day> days = new ArrayList<>() {{
                    add(day1);
                    add(day2);
                    add(day3);
        }};

        for (Day day : days) {
            System.out.println("Part 1:");
            System.out.println("Answer: " + day.part1());

            System.out.println("Part 2:");
            System.out.println("Answer: " + day.part2());
        }
    }
}
