package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompMoves {
    public void getMove(JButton[][] matrix) {
        if (Points.POINTS.moves < matrix.length *matrix.length)
//            randomMove(matrix);
//        else
//            minmax(matrix);
            randomMoveFromRest(matrix);
    }

    private void randomMoveFromRest(JButton[][] matrix) {
        List<Point> rest = new ArrayList<>();
        for (int _col = 0; _col < matrix.length; _col++) {
            for (int _row = 0; _row < matrix.length; _row++) {
                if (matrix[_row][_col].isEnabled())
                    rest.add(new Point(_row,_col));
            }
        }
        int i = (int) (Math.random() * rest.size());
        Point chosen = rest.get(i);
        matrix[chosen.row][chosen.col].setEnabled(false);
        matrix[chosen.row][chosen.col].setBackground(Color.BLUE);
        matrix[chosen.row][chosen.col].setText(Points.POINTS.moves+"");
        Points.POINTS.kompScore += Check.points(matrix,chosen.row,chosen.col);
        System.out.printf("%d %s na [%s], with points: %d\n",Points.POINTS.moves, Points.POINTS.who,matrix[chosen.row][chosen.col].getName(), Points.POINTS.kompScore);

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
