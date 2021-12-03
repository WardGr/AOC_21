package main.java.tests;
import main.java.days.Day;
import main.java.days.Day3;
import org.junit.Test;
import org.junit.Assert;

public class testDay3 {
    Day day = new Day3("resources/day3.txt");

    @Test
    public void test1() {
        Assert.assertEquals(day.part1(), 198);
    }

    @Test
    public void test2() {

        Assert.assertEquals((day.part2()), 230);
    }

    @Test
    public void answers() {
        day = new Day3("resources/inputDay3.txt");
        Assert.assertEquals(2250414, day.part1());
        Assert.assertEquals(6085575, day.part2());
    }

    /*@Test
    public void stringTest() {
        String[] list = new String[];
        System.out.println(list);
        System.out.println(list.length);

        list[0] = "Hello";
        System.out.println(list);
        System.out.println(list.length);
    }*/
}
