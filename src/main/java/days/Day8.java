package main.java.days;

import main.java.utilities.Utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8 implements Day {

    private final List<String> input;

    public Day8(String file) {
        input = Utils.fileToList(file);
    }

    @Override
    public Object part1() {
        AtomicInteger count = new AtomicInteger();
        input.forEach(line -> {
            String rightHalf = line.split(" \\| ")[1];
            Arrays.stream(rightHalf.split(" ")).forEach(x -> {
                if (x.length() == 2 || x.length() == 7 || x.length() == 3 || x.length() ==  4)
                    count.getAndIncrement();
            });
        });
        return count;
    }

    @Override
    public Object part2() {
        AtomicInteger sum = new AtomicInteger();
        /* Unique: 1, 4, 7, 8
            1: 2 (top right, bottom right)
            4: 4 (top left, top right, middle, bottom right)
            7: 3 (top, top right, bottom right)
            8: 7 (top, middle, bottom, top right, top left, bottom right, bottom left)
         */

        String[] bits = new String[10];
        input.forEach(line -> {
            String input = line.trim().split(" \\| ")[0];
            String output = line.trim().split(" \\| ")[1];


            // First we get the bits of all the unique numbers
            Arrays.stream(input.trim().split(" ")).forEach(bit -> {
                switch (bit.length()) {
                    case 2 -> bits[1] = bit;
                    case 4 -> bits[4] = bit;
                    case 3 -> bits[7] = bit;
                    case 7 -> bits[8] = bit;
                }
            });

            // We then check the input again and check wether it's another number
            Arrays.stream(input.trim().split(" ")).forEach(bit -> {
                switch (bit.length()) {
                    case 6 -> {
                        // Either 9, or 0
                        if (sameBits(bits[1], bit)) {
                            if (sameBits(bits[4], bit)) // its 9 because its got top right
                                bits[9] = bit;
                            else
                                bits[0] = bit;
                        }
                        else // it's 6
                            bits[6] = bit;
                    }
                    case 5 -> {
                        // Either 2, 3 or 5
                        if (sameBits(bits[1], bit))
                            bits[3] = bit;
                        else if (equalBits(bit, bits[4]) == 3)
                            bits[5] = bit;
                        else
                            bits[2] = bit;
                    }
                }
            });
                    int number = 0;
                    for(String s : output.trim().split(" "))
                    {
                        for(int i = 0; i < 10; i++)
                            if(sameBits(s, bits[i]) && sameBits(bits[i], s))
                                number = (number * 10) + i;
                    }
                    sum.addAndGet(number);

        });
        return sum;
    }

    public boolean sameBits(String x, String y) {
        char[] xChar = x.toCharArray(), yChar = y.toCharArray();
        for( char a : xChar) {
            boolean found = false;
            for (char b : yChar) {
                if (a == b) {
                    found = true;
                    break;
                }
            }
            if (!found)
                return false;
        }
        return true;
    }

    public int equalBits(String x, String y) {
        int equal = 0;
        char[] xChar = x.toCharArray(), yChar = y.toCharArray();
        for( char a : xChar) {
            for (char b : yChar) {
                if (a == b)
                    equal++;
            }
        }
        return equal;
    }
}
