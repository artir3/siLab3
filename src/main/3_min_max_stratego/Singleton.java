package main;

import javax.swing.*;

public class Singleton {
    //rozmiar planszy
    public int MAX = 7;

    public int playerScore = 0;
    public int kompScore = 0;
    public int moves = 0;
    public String who = "";
    JButton[][] MATRIX = new JButton[MAX][MAX];
    public static Singleton singleton = new Singleton();

    public void setMatixSize(int max){
        this.MAX = max;
        MATRIX = new JButton[max][max];
    }


}
