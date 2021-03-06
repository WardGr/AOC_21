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
        Day day8 = new Day8("resources/inputDay8.txt");
        Day day9 = new Day9("resources/inputDay9.txt");
        Day day10 = new Day10("resources/inputDay10.txt");
        Day newDay10 = new NewDay10("resources/inputDay10.txt");
        Day day11 = new Day11("resources/inputDay11.txt");
        Day day12 = new Day12("resources/inputDay12.txt");
        Day day13 = new Day13("resources/inputDay13.txt");
        Day day14 = new Day14("resources/inputday14.txt");
        ArrayList<Day> days = new ArrayList<>() {{
                    add(day1);
                    add(day2);
                    add(day3);
                    add(day4);
                    add(day5);
                    add(day6);
                    add(day7List);
                    //add(day7Map);
                    add(day8);
                    add(day9);
                    //add(day10);
                    add(newDay10);
                    add(day11);
                    add(day12);
                    add(day13);
                    add(day14);
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
