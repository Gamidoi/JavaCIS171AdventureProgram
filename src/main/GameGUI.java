package main;
/*
 * Sam Hapke sphapke@dmacc.edu
 * CIS171 11027
 * 12/6/2025
 */


import display.MenuPanel;

import javax.swing.*;

public class GameGUI {
    public static void main(String[] args){
        JFrame game = new JFrame();
        game.setTitle("Little Adventure Game!");
        game.setSize(1015, 670);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.add(new MenuPanel());
        game.setVisible(true);
    }
}
