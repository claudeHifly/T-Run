/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utility.Utility;

/**
 *
 * @author User
 */
public class HomePage{
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(680, 680);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = new Utility().create("src/image/Title small.png");
                Image image2 = image.getScaledInstance(650, 300, Image.SCALE_SMOOTH);
                BufferedImage dimg = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);
                g.drawImage(image2, 0, 0, this);
            }
        };
        
        JButton startButton = new JButton("Start");
        //startButton.setSize(5, 20);
        frame.add(panel);
        frame.add(startButton);
        
        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called
    }
}
