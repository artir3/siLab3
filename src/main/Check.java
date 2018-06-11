package main;

import javax.swing.*;

import static java.lang.Math.min;

public class Check {
    //white, blue, red
    // 0< col, row< max
    private static int rowIsFull(JButton[][] matrix, int row) {
        int score = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (isExist(matrix[row][i])) {
                score++;
            }
            return 0;
        }
        return score;
    }

    private static int columnIsFull(JButton[][] matrix, int column) {
        int score = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (isExist(matrix[i][column])) {
                score++;
            }
            return 0;
        }
        return score;
    }

    private static int crossIsFull(JButton[][] matrix, int row, int column) {
        int score = 0, max = matrix.length - 1;
        Integer su = 0, sl = 0;
        int d_toUpper = min(row, column);
        int d_toLow = min(max - row, column);
        int _lowRow = row + d_toLow, _lowCol = column - d_toLow;
        for (int i = min(_lowRow, _lowCol); i < matrix.length; i++) {
            if (sl != null) {
                _lowRow--;
                _lowCol++;
//                System.out.printf("Sprawdzam lower [%d, %d]\n", _lowRow, _lowCol);
                if (_lowCol < matrix.length && _lowRow > -1) {
                    if (isExist(matrix[_lowRow][_lowCol])) {
                        sl++;
                    } else {
                        sl = null;
                    }
                }
            }
            _lowRow = row - d_toUpper;
            _lowCol = column - d_toUpper;
            if (su != null) {
                _lowRow++;
                _lowCol++;
//                System.out.printf("Sprawdzam upper [%d, %d]\n", _lowRow, _lowCol);
                if (_lowRow < matrix.length & _lowCol < matrix.length) {
                    if (isExist(matrix[_lowRow][_lowCol])) {
                        su++;
                    } else {
                        su = null;
                    }
                }
            }
        }
        return (sl != null ? sl : 0) + (su != null ? su : 0);
    }

    public static int points(JButton[][] matrix, int row, int column) {
        return crossIsFull(matrix, row, column) + columnIsFull(matrix, column) + rowIsFull(matrix, row);
    }

    private static boolean isExist(JButton button) {
        return button != null && !button.isEnabled();
    }


}
