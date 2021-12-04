package main.java.tests;

import main.java.days.Day;
import main.java.days.Day3;
import main.java.days.Day4;
import org.junit.Test;
import org.junit.Assert;

public class TestDay4 {
    Day day = new Day4("resources/day4.txt");

    @Test
    public void testx1() {
        Assert.assertEquals(4512, day.part1());
    }

    @Test
    public void test2() { Assert.assertEquals(1924, day.part2()); }
}
