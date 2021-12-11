package main.java.tests;

import main.java.days.Day;
import main.java.days.NewDay10;
import org.junit.Assert;
import org.junit.Test;

public class TestDay10 {
    Day day = new NewDay10("resources/day10.txt");

    @Test
    public void test() {
        Assert.assertEquals(26397, day.part1());
        Assert.assertEquals((long)288957, day.part2());
    }
}
