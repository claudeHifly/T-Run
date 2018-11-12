/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author gruppo 8
 */
public class UserInterface {

    JFrame mainWindow = new JFrame("T-Run");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int WIDTH = (int) screenSize.getWidth();
    public int HEIGHT = (int) (0.7*screenSize.getHeight());

    public void createAndShowGUI() {
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Container container = mainWindow.getContentPane();

        GamePanel gamePanel = new GamePanel();
        gamePanel.addKeyListener(gamePanel);
        gamePanel.setFocusable(true);

        container.setLayout(new BorderLayout());

        container.add(gamePanel, BorderLayout.CENTER);*/
        mainWindow.setSize(WIDTH, HEIGHT);
        mainWindow.setResizable(true);
        mainWindow.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().createAndShowGUI();
            }
        });
    }

}
