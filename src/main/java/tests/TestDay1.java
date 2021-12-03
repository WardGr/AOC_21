package main.java.tests;

import main.java.days.Day;
import main.java.days.Day1;
import org.junit.Test;
import org.junit.Assert;

public class TestDay1 {
    @Test
    public void test1() {
        Day day = new Day1("resources/day1.txt");
        System.out.println(day.part1());
        Assert.assertEquals(7, (int) day.part1());
    }

    @Test
    public void test2() {
        Day day = new Day1("resources/day1.txt");
        Assert.assertEquals(5, day.part2());
    }
}
