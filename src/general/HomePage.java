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
import utility.Resources;

/**
 *
 * @author Angela
 */
public class HomePage extends JFrame{
    int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);
    int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
    
    public static JButton startButton;
    //public static JFrame frame;
    
    public HomePage() {
        setFocusable(true);//keyListener   
        setSize(width, height);
        JPanel panel = new JPanel() {
            
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                //URL url = this.getClass().getClassLoader().getResource("image/HomePage/Title small.png");
                BufferedImage image = Resources.instance().getHomepageTitleImage();
                //url = this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png");
                BufferedImage backImage = Resources.instance().getHomepageBackgroundImage();
                Image scaledImage = image.getScaledInstance((int) (width * 0.5), (int) (image.getHeight() * width / image.getWidth() * 0.5), 100);
                Image scaledBackImage = backImage.getScaledInstance(width, height, 100);
                
                g.drawImage(scaledBackImage, 0, 0, this);
                //g.drawImage(scaledImage, (int) (width - (width * 0.5))/2, 0, this);
            }
        };
        
        startButton = new JButton("START");
        startButton.setFont(new Font("Courier New", Font.BOLD, 30));
        startButton.setBounds((int) ((getWidth() - 150)/2), (int) (getHeight() * 0.8), 150, 50);
        panel.add(startButton);        
        
        //panel.setBackground(new Color(137,223,51));
        panel.setLayout(null);
        add(panel);
        
        startButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            setVisible(false);
            UserInterface.instance().setVisible(true);
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        validate(); // because you added panel after setVisible was called
        repaint(); // because you added panel after setVisible was called
    }


    public static void main(String[] args) {
        int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);
        int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
        HomePage frame = new HomePage();   
    }            
}
