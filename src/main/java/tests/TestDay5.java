package main.java.tests;

import main.java.days.Day;
import main.java.days.Day5;
import org.junit.Assert;
import org.junit.Test;

public class TestDay5 {
    Day day = new Day5("resources/day5.txt");

    @Test
    public void test1() {
        Assert.assertEquals(5, day.part1());
    }

    @Test
    public void test2() {
        Assert.assertEquals(12, day.part2());
    }

    @Test
    public void inputTest() {
        day = new Day5("resources/inputDay5.txt");
        Assert.assertEquals(7380, day.part1());
        Assert.assertEquals(21373, day.part2());
    }
}
