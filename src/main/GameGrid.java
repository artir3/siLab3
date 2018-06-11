
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGrid extends JFrame {
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 10;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton applyButton = new JButton("Apply gaps");
    GridLayout experimentLayout;
    CompMoves cm = new CompMoves();
    CheckPoints checkPoints = new CheckPoints();

    public GameGrid(String name) {
        super(name);
        setResizable(false);
        experimentLayout = new GridLayout(S.s.MAX, S.s.MAX);
    }

    public void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
    }

    public void addComponentsToPane(final Container pane) {
        initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(S.s.MAX, S.s.MAX));


        //Set up components preferred size
//                Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((50 + maxGap) * S.s.MAX,
                (50 + maxGap) * S.s.MAX));
        //Add buttons to experiment with Grid Layout
        for (int i = 0; i < S.s.MAX * S.s.MAX; i++) {
            JButton button = newButton(i);
            S.s.MATRIX[i / S.s.MAX][i % S.s.MAX] = button;
            compsToExperiment.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playerMove(button);
                    komputerMove();
                }
            });

        }

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    void playerMove(JButton button) {
        S.s.moves++;
        button.setBackground(Color.RED);
        button.setEnabled(false);
        String[] poz = button.getName().split(" ");
        S.s.who = "Player";
        S.s.playerScore += checkPoints.points(Integer.parseInt(poz[0]), Integer.parseInt(poz[1]));
        System.out.printf("%d %s ruch na [%s], with points: %d\n", S.s.moves, S.s.who, button.getName(), S.s.playerScore);
    }

    void komputerMove() {
        S.s.moves++;
        S.s.who = "Komp";
        cm.getMove();
    }

    private JButton newButton(int i) {
        JButton button = new JButton();
        button.setEnabled(true);
        button.setName((i / S.s.MAX) + " " + (i % S.s.MAX));
        button.setBackground(Color.WHITE);
        return button;
    }

    private static void createAndShowGUI() {
        GameGrid frame = new GameGrid("Stratego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
