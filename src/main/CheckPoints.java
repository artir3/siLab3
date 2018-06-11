package main;

import javax.swing.*;

import static java.lang.Math.min;

public class CheckPoints {
    private static int rowIsFull(int row) {
        int score = 0;
        for (int i = 0; i < S.s.MAX; i++) {
            if (!isChousen(S.s.MATRIX[row][i])) {
                return 0;
            }
            score++;
        }
        return score;
    }

    private static int columnIsFull(int column) {
        int score = 0;
        for (int i = 0; i < S.s.MAX; i++) {
            if (!isChousen(S.s.MATRIX[i][column])) {
                return 0;
            }
            score++;
        }
        return score;
    }

    private static int crossIsFull(int row, int column) {
        int score = 0, max = S.s.MAX - 1;
        score += checkLineToDown(row,column,max);
        score += checkLineToUp(row,column,max);
        return score;
    }

    private static int checkLineToUp(int row, int column, int max){
        int d_toLow = min(max - row, column);
        int _row = row + d_toLow;
        int _col = column - d_toLow;

        Integer count = 0;
        for (int i = min(_row, _col); i < S.s.MAX; i++) {
            if (count != null) {
                if (_col < S.s.MAX && _row > -1) {
                    if (isChousen(S.s.MATRIX[_row][_col])) {
                        count++;
                    } else {
                        count = null;
                    }
                _row--;
                _col++;
                }
            }
        }
        return count == null? 0: count;
    }
    private static int checkLineToDown(int row, int column, int max){
        int d_toUpper = min(row, column);
        int _row = row - d_toUpper;
        int _col = column - d_toUpper;

        Integer count = 0;
        for (int i = min(_row,_col); i < S.s.MAX; i++) {
            if (count != null) {
                if (_row < S.s.MAX & _col < S.s.MAX) {
                    if (isChousen(S.s.MATRIX[_row][_col])) {
                        count++;
                    } else {
                        count = null;
                    }
                _row++;
                _col++;
                }
            }
        }
        return count == null? 0: count;
    }

    public static int points(int row, int column) {
        return crossIsFull(row, column) + columnIsFull(column) + rowIsFull(row);
    }

    private static boolean isChousen(JButton button) {
        return button != null && !button.isEnabled();
    }


}
