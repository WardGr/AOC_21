package main.java.tests;

import main.java.days.Day;
import main.java.days.Day8;
import org.junit.Assert;
import org.junit.Test;

public class TestDay8 {

    Day day = new Day8("resources/day8.txt");

    @Test
    public void test1() {
        Assert.assertEquals(26, day.part1());
    }

    @Test
    public void test2() {
        Assert.assertEquals(61229, day.part2());
    }
}
