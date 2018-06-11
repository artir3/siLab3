package main;

import javax.swing.*;

import static java.lang.Math.min;

public class Check {
    //white, blue, red
    // 0< col, row< max
    private static int rowIsFull(JButton[][] matrix, int row) {
        int score = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (!isChousen(matrix[row][i])) {
                return 0;
            }
            score++;
        }
        return score;
    }

    private static int columnIsFull(JButton[][] matrix, int column) {
        int score = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (!isChousen(matrix[i][column])) {
                return 0;
            }
            score++;
        }
        return score;
    }

    private static int crossIsFull(JButton[][] matrix, int row, int column) {
        int score = 0, max = matrix.length - 1;
        score += checkLineToDown(matrix,row,column,max);
        score += checkLineToUp(matrix,row,column,max);
        return score;
    }

    private static int checkLineToUp(JButton[][] matrix, int row, int column, int max){
        int d_toLow = min(max - row, column);
        int _row = row + d_toLow;
        int _col = column - d_toLow;

        Integer count = 0;
        for (int i = min(_row, _col); i < matrix.length; i++) {
            if (count != null) {
                System.out.printf("%d Sprawdzam %s gore [%d, %d]\n",Points.POINTS.moves,Points.POINTS.who, _row, _col);
                if (_col < matrix.length && _row > -1) {
                    if (isChousen(matrix[_row][_col])) {
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
    private static int checkLineToDown(JButton[][] matrix, int row, int column, int max){
        int d_toUpper = min(row, column);
        int _row = row - d_toUpper;
        int _col = column - d_toUpper;

        Integer count = 0;
        for (int i = min(_row,_col); i < matrix.length; i++) {
            if (count != null) {
                System.out.printf("%d Sprawdzam %s w dół [%d, %d]\n",Points.POINTS.moves,Points.POINTS.who, _row, _col);
                if (_row < matrix.length & _col < matrix.length) {
                    if (isChousen(matrix[_row][_col])) {
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

    public static int points(JButton[][] matrix, int row, int column) {
        return
                crossIsFull(matrix, row, column)
//                columnIsFull(matrix, column)
//                        rowIsFull(matrix, row)
 ;
    }

    private static boolean isChousen(JButton button) {
        return button != null && !button.isEnabled();
    }


}
