/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComponent;
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
                BufferedImage backImage = Resources.instance().getHomepageBackgroundImage();
                Image scaledBackImage = backImage.getScaledInstance(width, height, 100);
                
                /*BufferedImage footprintImage = Resources.instance().getHomepageFootprintImage();
                Image scaledFootprintImage = footprintImage.getScaledInstance((int) (width*0.1), (int) (height*0.1), 100);*/
                
                g.drawImage(scaledBackImage, 0, 0, this);
                
                g.setFont(new Font("Courier New", Font.BOLD, 25));
                g.drawString("Click on footprint to start", (int) (width*0.35), (int) (height*0.92));
                //g.drawImage(scaledFootprintImage, (int) (width*0.48), (int) (height*0.8), this);
            }
        };
        
        startButton = new JButton("START");
        startButton.setFont(new Font("Courier New", Font.BOLD, 30));
        startButton.setBounds((int) ((getWidth() - 150)/2), (int) (getHeight() * 0.8), 180, 50);
        //startButton.setVisible(false);
        panel.add(startButton); 
        
        //panel.setBackground(new Color(137,223,51));
        ImageShowingComponent footprint = new ImageShowingComponent(this);
        //footprint.setBounds((int) ((getWidth() - 150)/2), (int) (getHeight() * 0.8), 150, 50);
        footprint.setVisible(true);
        panel.setLayout(new BorderLayout());
        panel.add(footprint);
        
        //panel.setLayout(null);
        add(panel);
        //panel.add(footprint);
        
        startButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            setVisible(false);
            //UserInterface.instance().setVisible(true);
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
        Resources.instance();
        int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);
        int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
        HomePage frame = new HomePage();   
    }  
    
    private class ImageShowingComponent extends JComponent {

        // The image to display
        private final BufferedImage footprintImage;
        private final Image scaledFootprintImage;
        private MouseListener listenerStart;
        //private MouseListener listenerDemo;

        // Instantiate the panel and perform initialization
        ImageShowingComponent(HomePage frame) {
            this.listenerStart = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setVisible(false);
                    UserInterface.instance().setVisible(true);
                }
            };
            
            /*this.listenerDemo = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    frame.setVisible(false);
                    //apri demo
                }
            };*/
          addMouseListener(listenerStart);
          this.footprintImage = Resources.instance().getHomepageFootprintImage();
          this.scaledFootprintImage = footprintImage.getScaledInstance((int) (width*0.1), (int) (height*0.2), 100);
        }

        @Override
        public void paintComponent(Graphics g) {
          g.drawImage(this.scaledFootprintImage, (int) (width*0.44), (int) (height*0.7), null);
        }
        // This method override will tell the LayoutManager how large this component
        // should be. We'll want to make this component the same size as the `img`.
        /*public Dimension getPreferredSize() {
        return new Dimension(img.getWidth(), img.getHeight());
        }*/
        // The MouseListener that handles the click, etc.
    }
}
