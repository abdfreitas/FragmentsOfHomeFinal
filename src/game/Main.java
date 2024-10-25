package game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        String collisionCheckerType = args[0];
        //System.out.println(args[0]);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Fragments of Home");

        GamePanel gamePanel = new GamePanel(collisionCheckerType);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
