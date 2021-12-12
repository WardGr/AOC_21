package main.java.tests;

import main.java.days.Day12;
import org.junit.Assert;
import org.junit.Test;

public class TestDay12 {

    Day12 day = new Day12("resources/day12.txt");

    @Test
    public void test() {
        Assert.assertEquals((long) 10, day.part1());
        System.out.println("PART TWO" + day.part2());
        day.printTest();
    }
}
