package main.java.tests;

import main.java.days.Day;
import main.java.days.Day5;
import main.java.days.Day7;
import org.junit.Assert;
import org.junit.Test;

public class TestDay7 {
    Day day = new Day7("resources/day7.txt");

    @Test
    public void test1() {
        Assert.assertEquals((long) 37, day.part1());
    }

    @Test
    public void test2() {
        Assert.assertEquals((long) 168, day.part2());
    }

    @Test
    public void inputTest() {
        day = new Day7("resources/inputDay7.txt");
    }
}
