package main.java.tests;

import main.java.days.Day;
import main.java.days.Day7List;
import main.java.days.Day7Map;
import org.junit.Assert;
import org.junit.Test;

public class TestDay7 {
    Day day = new Day7Map("resources/day7.txt");

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
        day = new Day7List("resources/inputDay7.txt");
        long start = System.nanoTime();
        day.part1();
        day.part2();
        System.out.println("List implementation took: \n" + (System.nanoTime() - start) + " ns!");

        day = new Day7Map("resources/inputDay7.txt");
        day.part1();
        day.part2();
        System.out.println("Map implementation took: \n" + (System.nanoTime() - start) + " ns!");
    }
}
