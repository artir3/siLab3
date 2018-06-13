package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompMoves {
    public void getMove() {
        if (Singleton.singleton.moves <= Singleton.singleton.MAX * Singleton.singleton.MAX)
//            randomMove(matrix);
//        else
//            minmax(matrix);
            randomMoveFromRest();
    }

    private void randomMoveFromRest() {
        List<Point> rest = new ArrayList<>();
        for (int _col = 0; _col < Singleton.singleton.MAX; _col++) {
            for (int _row = 0; _row < Singleton.singleton.MAX; _row++) {
                if (Singleton.singleton.MATRIX[_row][_col].isEnabled())
                    rest.add(new Point(_row,_col));
            }
        }
        int i = (int) (Math.random() * rest.size());
        Point chosen = rest.get(i);

        Singleton.singleton.MATRIX[chosen.row][chosen.col].setEnabled(false);
        Singleton.singleton.MATRIX[chosen.row][chosen.col].setBackground(Color.BLUE);
        Singleton.singleton.kompScore += CheckPoints.points(chosen.row,chosen.col);

    }

    private void randomMove(JButton[][] matrix) {
        int _col, _row;
        do {
            _col = (int) (Math.random() * matrix.length);
            _row = (int) (Math.random() * matrix.length);
        } while (matrix[_row][_col].isEnabled());
        matrix[_row][_col].setBackground(Color.BLUE);
        matrix[_row][_col].setEnabled(false);
    }

    private void minmax(String[][] matrix) {
    }


    private class Point {
        public int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
