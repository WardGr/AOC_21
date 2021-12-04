package main.java.days;

import main.java.utilities.Utils.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day4 implements Day {
    private final String file;
    private int solution;
    private boolean win = false;

    public Day4(String file) {
        this.file = file;
    }

    @Override
    public Object part1() {
        List<String> input = Utils.fileToList(file);
        List<int[][]> boards = generateBoards(input);
        String[] bingoNumbers = input.get(0).split(",");
        Arrays.stream(bingoNumbers).forEach(number -> boards.forEach(board -> {
            if(!(this.win) && check(board, number))
                if(check_win(board))
                    calculateWinnings(board, number);
        }));
        return solution;
    }

    @Override
    public Object part2() {
        List<String> input = Utils.fileToList(file);
        List<int[][]> boards = generateBoards(input);
        String[] bingoNumbers = input.get(0).split(",");
        while (boards.size() > 1) {
            for (String i : bingoNumbers) {
                ArrayList removeIdx = new ArrayList();
                for (int idx = 0; idx < boards.size(); idx++)
                    if (check(boards.get(idx), i) && check_win(boards.get(idx))) {
                        calculateWinnings(boards.get(idx), i);
                        removeIdx.add(idx);
                    }
                removeAll(removeIdx, boards);
            }
        }
        return solution;
    }

    private void removeAll(ArrayList removeIdx, List<int[][]> boards) {
        Collections.sort(removeIdx, Collections.reverseOrder());
        removeIdx.stream().forEach(idx -> {
            boards.remove(boards.get((int) idx));
        });
    }

   private List<int[][]> generateBoards(List<String> input) {
        List<int[][]> boards = new ArrayList<>();
        int[][] board;
        String line;
        for (int i = 0; i < 5; i++) {
            int currLine = 2 + i;
            while (currLine < input.size() ) {
                if (i == 0) {
                    board = new int[5][5];
                    boards.add(board);
                } else
                    board = boards.get((currLine - i - 2) / 6);

                line = input.get(currLine);
                String[] lineParted = line.trim().split("\\s+");
                for (int j = 0; j < 5; j++)
                    board[i][j] = Integer.parseInt(lineParted[j]);
                currLine += 6;
            }
        }

        return boards;
   }

    public boolean check(int[][] board, String number){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == Integer.parseInt(number)) {
                    board[i][j] = -1;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check_win(int[][] board) {
        for (int i = 0; i < 5; i++) {
            boolean won = true;
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != -1) {
                    won = false;
                    break;
                }
            }
            if (won)
                return true;
        }
        for (int j = 0; j < 5; j++) {
            boolean won = true;
            for (int i = 0; i < 5; i++)
                if (board[i][j] != -1) {
                    won = false;
                    break;
                }
            if (won)
                return true;
        }
        return false;
    }

    public void calculateWinnings(int[][] board, String number) {
        win = true;
        int sum = 0;
        for( int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ( board[i][j] != -1)
                    sum += board[i][j];
            }
        }
        solution = sum * Integer.parseInt(number);
    }
}