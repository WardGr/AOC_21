package main.java;

import main.java.days.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("AoC 2021");
        Day day1 = new Day1("resources/inputDay1.txt");
        Day day2 = new Day2("resources/inputDay2.txt");
        Day day3 = new Day3("resources/inputDay3.txt");
        Day day4 = new Day4("resources/inputDay4.txt");

        ArrayList<Day> days = new ArrayList<>() {{
                    add(day1);
                    add(day2);
                    add(day3);
                    add(day4);
        }};

        int i = 1;
        for (Day day : days) {
            System.out.println("day" + i + ":");
            System.out.println("Part 1:");
            System.out.println("Answer: " + day.part1());

            System.out.println("Part 2:");
            System.out.println("Answer: " + day.part2() + "\n");
            i++;
        }
    }
}