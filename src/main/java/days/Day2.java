package main.java.days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 implements Day {

    private int x_coord = 0;
    private int y_coord = 0;
    private int aim = 0;

    @Override
    public int part1() {
        //BufferedReader br = new BufferedReader(new FileReader("src/main/Resources/inputDay2.txt"))
        //BufferedReader br = readFile("src/main/Resources/inputDay2.txt");
        calculateCoords("src/main/Resources/inputDay2.txt");
        return x_coord * y_coord;
    }

    @Override
    public int part2() {
        x_coord = 0;
        y_coord = 0;
        //BufferedReader br = readFile("src/main/Resources/inputDay2.txt");
        calculateAim("src/main/Resources/inputDay2.txt");
        return y_coord * x_coord;
    }

    private void calculateCoords(String file) {
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

    private void calculateAim(String file) {
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

    /*private BufferedReader readFile(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            System.out.println("complete");
            return br;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    private void adapt_x(int x) {
        this.x_coord += x;
    }

    private void adapt_y(int y) {
        this.y_coord += y;
    }

    private void adapt_aim(int aim) {
        this.aim += aim;
    }

    private void multiply_aim(int aim) {
        this.aim *= aim;
    }
}

