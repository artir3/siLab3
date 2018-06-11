        /*
         * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
         *
         * Redistribution and use in source and binary forms, with or without
         * modification, are permitted provided that the following conditions
         * are met:
         *
         *   - Redistributions of source code must retain the above copyright
         *     notice, this list of conditions and the following disclaimer.
         *
         *   - Redistributions in binary form must reproduce the above copyright
         *     notice, this list of conditions and the following disclaimer in the
         *     documentation and/or other materials provided with the distribution.
         *
         *   - Neither the name of Oracle or the names of its
         *     contributors may be used to endorse or promote products derived
         *     from this software without specific prior written permission.
         *
         * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
         * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
         * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
         * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
         * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
         * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
         * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
         * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
         * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
         * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
         * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
         */

        package main;

        /*
         * GridLayoutDemo.java
         *
         */

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
            Check check = new Check();
            int max = 3, moves = 0;
            int playerScore = 0, kompScore = 0;

            public GameGrid(String name) {
                super(name);
                setResizable(false);
                experimentLayout = new GridLayout(max, max);
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
                controls.setLayout(new GridLayout(max, max));

                //Set up components preferred size
                JButton[][] bList = new JButton[max][max];
//                Dimension buttonSize = b.getPreferredSize();
                compsToExperiment.setPreferredSize(new Dimension((50 + maxGap) * max,
                        (50 + maxGap) * max));
                //Add buttons to experiment with Grid Layout
                for (int i = 0; i < max * max; i++) {
                    JButton bu = newButton();
                    bList[i / max][i % max] = bu;
                    compsToExperiment.add(bu);
                    bu.setEnabled(true);
                    bu.setName((i / max) + " " + (i % max));
                    bu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            moves++;
                            bu.setBackground(Color.RED);
                            bu.setEnabled(false);
//                            bu.setText(bu.getName() + "");
                            String[] poz = bu.getName().split(" ");
                            playerScore += check.points(bList,Integer.parseInt(poz[0]),Integer.parseInt(poz[0]));
                            System.out.printf("%d Player ruch na [%s], with points: %d\n",moves,bu.getName(),playerScore);
                            moves++;
                            cm.getMove(bList, moves,kompScore);
                        }
                    });

                }

                //Add controls to set up horizontal and vertical gaps
//                controls.add(new Label("Horizontal gap:"));
//                controls.add(new Label("Vertical gap:"));
//                controls.add(new Label(" "));
//                controls.add(horGapComboBox);
//                controls.add(verGapComboBox);
//                controls.add(applyButton);

                //Process the Apply gaps button press
                pane.add(compsToExperiment, BorderLayout.NORTH);
                pane.add(new JSeparator(), BorderLayout.CENTER);
                pane.add(controls, BorderLayout.SOUTH);
            }

            private JButton newButton() {
                JButton b = new JButton();
                b.setBackground(Color.WHITE);
                return b;
            }

            /**
             * Create the GUI and show it.  For thread safety,
             * this method is invoked from the
             * event dispatch thread.
             */
            private static void createAndShowGUI() {
                //Create and set up the window.
                GameGrid frame = new GameGrid("GridLayoutDemo");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //Set up the content pane.
                frame.addComponentsToPane(frame.getContentPane());
                //Display the window.
                frame.pack();
                frame.setVisible(true);
            }

            public static void main(String[] args) {
                /* Use an appropriate Look and Feel */
                try {
                    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
                /* Turn off metal's use of bold fonts */
                UIManager.put("swing.boldMetal", Boolean.FALSE);

                //Schedule a job for the event dispatch thread:
                //creating and showing this application's GUI.
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        createAndShowGUI();
                    }
                });
            }
        }
