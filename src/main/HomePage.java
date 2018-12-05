/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utility.Utility;

/**
 *
 * @author Angela
 */
public class HomePage{
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1371, 558);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage image = new Utility().create("src/image/Title small.png");
                BufferedImage backImage = new Utility().create("src/image/HomePage/sfondoHome.png");
                Image scaledImage = image.getScaledInstance(714, 256, 100);
                //Image scaledBackImage = backImage.getScaledInstance(1000, 625, 100);
                g.drawImage(backImage, 0, 0, this);
                g.drawImage(scaledImage, (frame.getWidth() - 714)/2, 0, this);
                
            }
        };
        
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Courier New", Font.BOLD, 30));
        startButton.setBounds((int) ((frame.getWidth() - 150)/2), (int) (frame.getHeight() * 0.8), 150, 50);
        panel.add(startButton);        
        
        //panel.setBackground(new Color(137,223,51));
        panel.setLayout(null);
        frame.add(panel);
        
        startButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new UserInterface().setVisible(true);
            }
        });


        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called
    }
}
