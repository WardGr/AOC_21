package main.java.tests;

import main.java.days.Day;
import main.java.days.Day2;
import org.junit.Test;
import org.junit.Assert;

public class TestDay2 {
    Day day = new Day2("resources/day2.txt");

    @Test
    public void TestCase1() {
        Assert.assertEquals(150, day.part1());
    }

    @Test
    public void TestCase2() {
        Assert.assertEquals(900, day.part2());
    }
}
