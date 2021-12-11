package main.java.tests;

import main.java.days.Day;
import main.java.days.Day11;
import org.junit.Assert;
import org.junit.Test;

public class TestDay11 {
    Day day = new Day11("resources/day11.txt");

    @Test
    public void test() {
        Assert.assertEquals((long) 1656, day.part1());
        Assert.assertEquals(195, day.part2());
    }
}
