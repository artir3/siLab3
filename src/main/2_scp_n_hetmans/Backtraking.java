package main;

import java.util.Date;

public class Backtraking {
    public static void main(String args[]) {
        int noQuins = 5;
        Backtraking backtraking = new Backtraking();
        Long start = new Date().getTime();
        backtraking.solve(noQuins);
        Long end = new Date().getTime();
        System.out.println("Czas obliczeń " + (end - start) + "ms");
        System.out.println("Ilość odwiedzonych kombinacji " + backtraking.count);
    }

    public long count =0;

    boolean checkBeating(int board[][], int row, int col) {
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        for (int i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1)
                return false;
        return true;
    }

    boolean backtrack(int board[][], int col) {
        if (col >= board.length)
            return true;
        for (int row = 0; row < board.length; row++) {
            count++;
            if (checkBeating(board, row, col)) {
                board[row][col] = 1;
                if (backtrack(board, col + 1) == true)
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    boolean solve(int n) {
        int board[][] = new int[n][n];
        if (backtrack(board, 0) == false) {
            System.out.print("brak rozwiazania");
            return false;
        }
        print(board);
        return true;
    }

    private void print(int board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }
}
