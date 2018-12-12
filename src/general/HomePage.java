/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utility.Utility;
import java.net.URL;

/**
 *
 * @author Angela
 */
public class HomePage{
    
    public static void main(String[] args) {
        int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);
        int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL url = this.getClass().getClassLoader().getResource("image/HomePage/Title small.png");
                BufferedImage image = new Utility().create(url);
                url = this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png");
                BufferedImage backImage = new Utility().create(url);
                Image scaledImage = image.getScaledInstance((int) (width * 0.5), (int) (image.getHeight() * width / image.getWidth() * 0.5), 100);
                Image scaledBackImage = backImage.getScaledInstance((int) (backImage.getWidth() * height / backImage.getHeight()), height, 100);
                
                g.drawImage(scaledBackImage, 0, 0, this);
                g.drawImage(scaledImage, (int) (width - (width * 0.5))/2, 0, this);
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
            UserInterface.instance().setVisible(true);
            }
        });


        frame.validate(); // because you added panel after setVisible was called
        frame.repaint(); // because you added panel after setVisible was called
    }
}
