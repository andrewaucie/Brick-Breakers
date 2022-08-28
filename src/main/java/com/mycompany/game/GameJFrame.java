package com.mycompany.game;

import javax.swing.JFrame;

public class GameJFrame extends JFrame {
    public GameJFrame() {
        Gameplay gamePlay = new Gameplay();
        setBounds(10, 10, 900, 700);
        setTitle("Brick Breakers");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(gamePlay);
        setVisible(false);
        setAlwaysOnTop(true);
    }

}
