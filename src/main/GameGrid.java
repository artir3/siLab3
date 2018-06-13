
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

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
        experimentLayout = new GridLayout(Singleton.singleton.MAX, Singleton.singleton.MAX);
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
        controls.setLayout(new GridLayout(Singleton.singleton.MAX, Singleton.singleton.MAX));
        compsToExperiment.setPreferredSize(new Dimension((50 + maxGap) * Singleton.singleton.MAX,
                (50 + maxGap) * Singleton.singleton.MAX));
        for (int i = 0; i < Singleton.singleton.MAX * Singleton.singleton.MAX; i++) {
            JButton button = newButton(i);
            Singleton.singleton.MATRIX[i / Singleton.singleton.MAX][i % Singleton.singleton.MAX] = button;
            compsToExperiment.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    playerMove(button);
                    komputerMove();
                    if (Singleton.singleton.moves >= Singleton.singleton.MAX * Singleton.singleton.MAX) {
                        System.out.println(Singleton.singleton.playerScore < Singleton.singleton.kompScore ? "KOMPUETER WYGRAL" : Singleton.singleton.playerScore == Singleton.singleton.kompScore ? "REMIS" : "GRACZ WYGRAL");
                        System.out.printf("Gracz zdobył: %d punktów\n", Singleton.singleton.playerScore);
                        System.out.printf("Komputer zdobył: %d punktów.", Singleton.singleton.kompScore);
                    }
                }
            });

        }

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    void playerMove(JButton button) {
        Singleton.singleton.moves++;
        button.setBackground(Color.RED);
        button.setEnabled(false);
        String[] poz = button.getName().split(" ");
        Singleton.singleton.who = "Player";
        Singleton.singleton.playerScore += checkPoints.points(Integer.parseInt(poz[0]), Integer.parseInt(poz[1]));
    }

    void komputerMove() {
        Singleton.singleton.moves++;
        Singleton.singleton.who = "Komp";
        cm.getMove();
    }

    private JButton newButton(int i) {
        JButton button = new JButton();
        button.setEnabled(true);
        button.setName((i / Singleton.singleton.MAX) + " " + (i % Singleton.singleton.MAX));
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
        System.out.println("Proszę wpisać wartość");
        Scanner scanner = new Scanner(System.in);
        Singleton.singleton.setMatixSize(scanner.nextInt());
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
