package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompMoves {
    public void getMove() {
        if (S.s.moves < S.s.MAX * S.s.MAX)
//            randomMove(matrix);
//        else
//            minmax(matrix);
            randomMoveFromRest();
    }

    private void randomMoveFromRest() {
        List<Point> rest = new ArrayList<>();
        for (int _col = 0; _col < S.s.MAX; _col++) {
            for (int _row = 0; _row < S.s.MAX; _row++) {
                if (S.s.MATRIX[_row][_col].isEnabled())
                    rest.add(new Point(_row,_col));
            }
        }
        int i = (int) (Math.random() * rest.size());
        Point chosen = rest.get(i);

        S.s.MATRIX[chosen.row][chosen.col].setEnabled(false);
        S.s.MATRIX[chosen.row][chosen.col].setBackground(Color.BLUE);
        S.s.kompScore += CheckPoints.points(chosen.row,chosen.col);
        System.out.printf("%d %s na [%s], with points: %d\n",S.s.moves, S.s.who,S.s.MATRIX[chosen.row][chosen.col].getName(), S.s.kompScore);

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
