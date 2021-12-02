package main.java.days;

import main.java.utilities.Utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Day2 implements Day {

    private int x_coord = 0;
    private int y_coord = 0;
    private int aim = 0;
    private String file;

    public Day2(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        calculateCoords();
        return x_coord * y_coord;
    }

    @Override
    public Object part2() {
        x_coord = 0;
        y_coord = 0;
        calculateAim();
        return y_coord * x_coord;
    }

    private void calculateCoords() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] part;
            while ((line = br.readLine()) != null) {
                part = line.split("(?<=\\D)(?=\\d)");
                switch (part[0]) {
                    case "forward " -> adapt_x(Integer.parseInt(part[1]));
                    case "down " -> adapt_y(Integer.parseInt(part[1]));
                    case "up " -> adapt_y(-Integer.parseInt(part[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateAim() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] part;
            while ((line = br.readLine()) != null) {
                part = line.split("(?<=\\D)(?=\\d)");
                switch (part[0]) {
                    case "forward " -> {
                        adapt_x(Integer.parseInt(part[1]));
                        adapt_y(Integer.parseInt(part[1]) * aim);
                    }
                    case "down " -> adapt_aim(Integer.parseInt(part[1]));
                    case "up " -> adapt_aim(-Integer.parseInt(part[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adapt_x(int x) {
        this.x_coord += x;
    }

    private void adapt_y(int y) {
        this.y_coord += y;
    }

    private void adapt_aim(int aim) {
        this.aim += aim;
    }
}

