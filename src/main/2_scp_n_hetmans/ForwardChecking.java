package main;

import java.util.Date;
import java.util.HashSet;

public class ForwardChecking {
    public static void main(String args[]) {
        int noQuins = 100;
        ForwardChecking backtraking = new ForwardChecking();
        Long start = new Date().getTime();
        backtraking.solve(noQuins);
        Long end = new Date().getTime();
        System.out.println("Czas obliczeń " + (end - start) + "ms");
        System.out.println("Ilość odwiedzonych kombinacji " + backtraking.count);
    }

    public long count = 0;

    boolean isSafe(int board[][], int row, int col) {
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

    private static HashSet<String> initialFields = null;

    boolean forward(int board[][], HashSet<String> domainFields, int max) {
        if (max == 0)
            return true;
        for (String s : domainFields) {
            HashSet<String> subDomainFields = new HashSet<String>(domainFields);
            String[] split = s.split(" ");
            int row = Integer.parseInt(split[0]);
            int col = Integer.parseInt(split[1]);
                count++;
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                deleteDomains(subDomainFields, row, col, board.length);
                if (forward(board, subDomainFields, max - 1))
                    return true;
                board[row][col] = 0;
            }
        }
        return false;
    }

    private void deleteDomains(HashSet<String> domain, int row, int col, int max) {
        for (int _row = 0; _row < max; _row++) {
            domain.remove(_row + " " + col);
        }
        for (int _col = 0; _col < max; _col++) {
            domain.remove(row + " " + _col);
        }
        domain.remove((row - 1) + " " + (col - 1));
        domain.remove((row - 1) + " " + (col + 1));
        domain.remove((row + 1) + " " + (col - 1));
        domain.remove((row + 1) + " " + (col + 1));
    }

    private HashSet<String> prepareFields(int max) {
        HashSet fields = new HashSet<String>();
        for (int row = 0; row < max; row++) {
            for (int col = 0; col < max; col++) {
                fields.add(col + " " + row);
            }
        }
        return fields;
    }

    boolean solve(int n) {
        int board[][] = new int[n][n];
        initialFields = prepareFields(n);
        if (forward(board, initialFields, n) == false) {
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
