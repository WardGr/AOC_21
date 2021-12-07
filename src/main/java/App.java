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
        Day day5 = new Day5("resources/inputDay5.txt");
        Day day6 = new Day6("resources/inputDay6.txt");
        Day day7List = new Day7List("resources/inputDay7.txt");
        //Day day7Map = new Day7Map("resources/inputDay7.txt");

        ArrayList<Day> days = new ArrayList<>() {{
                    add(day1);
                    add(day2);
                    add(day3);
                    add(day4);
                    add(day5);
                    add(day6);
                    add(day7List);
                    //add(day7Map);
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
