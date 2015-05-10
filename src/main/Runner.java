package main;

import javax.swing.*;
import Game.GamePanel;

import java.awt.*;

/**
 * Created by Connor on 5/10/15.
 */
public class Runner {

    public static void main(String[] args){

        System.out.println("Running");

        JFrame game = new JFrame("JumpingBob");
        game.setContentPane(new GamePanel());
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.pack();
        game.setVisible(true);

        System.out.println("Main method finished... moving to GamePanel...");



    }

}