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
 * @author G8
 */
public class HomePage extends JFrame {

    int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
    int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
    public static boolean demo;

    public static JButton demoButton;
    //public static JFrame frame;

    public HomePage() {
        setFocusable(true);//keyListener   
        setSize(width, height);
        demo = false;
        JPanel panel = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage backImage = Resources.instance().getHomepageBackgroundImage();
                Image scaledBackImage = backImage.getScaledInstance(width, height, 100);

                /*BufferedImage footprintImage = Resources.instance().getHomepageFootprintImage();
                Image scaledFootprintImage = footprintImage.getScaledInstance((int) (width*0.1), (int) (height*0.1), 100);*/
                g.drawImage(scaledBackImage, 0, 0, this);
                //g.drawImage(scaledFootprintImage, (int) (width*0.48), (int) (height*0.8), this);
            }
        };

        /*demoButton = new JButton("START DEMO");
        demoButton.setFont(new Font("Courier New", Font.BOLD, 30));
        demoButton.setBounds((int) ((getWidth() - 150) / 2), (int) (getHeight() * 0.5), 180, 50);
        //startButton.setVisible(false);
        panel.add(demoButton);*/
        //panel.setBackground(new Color(137,223,51));
        ImageShowingComponent footprint = new ImageShowingComponent(this);
        panel.setLayout(new BorderLayout());
        panel.add(footprint);

        //panel.setLayout(null);
        add(panel);
        //panel.add(footprint);

        /*demoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demo = true;
                setVisible(false);
                UserInterface.instance().setVisible(true);
            }
        });*/
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
        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
        int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
        HomePage frame = new HomePage();
    }

    private class ImageShowingComponent extends JComponent {

        // The image to display
        private final BufferedImage footprintImage;
        private final Image scaledFootprintImage;
        private final BufferedImage demoButtonImage;
        private final Image scaledDemoButtonImage;
        private MouseListener listener;

        // Instantiate the panel and perform initialization
        ImageShowingComponent(HomePage frame) {
            this.listener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int xOnScreen = e.getX();
                    int yOnScreen = e.getY();

                    //Controllo che il click sia stato fatto nella porzione di schermo dove è presente l'immagine
                    if ((xOnScreen >= (int) (width * 0.44) && xOnScreen <= (int) (width * 0.44) + (int) (width * 0.1)) && (yOnScreen >= (int) (height * 0.7) && yOnScreen <= (int) (height * 0.7) + (int) (height * 0.2))) {
                        frame.setVisible(false);
                        UserInterface.instance().setVisible(true);
                    }
                    else if((xOnScreen >= (int) (width * 0.6) && xOnScreen <= (int) (width * 0.6) + (int) (width * 0.05)) && (yOnScreen >= (int) (height * 0.75) && yOnScreen <= (int) (height * 0.75) + (int) (height * 0.1))) {
                        demo = true;
                        frame.setVisible(false);
                        UserInterface.instance().setVisible(true);
                    }
                }
            };

            this.addMouseListener(listener);
            this.footprintImage = Resources.instance().getHomepageFootprintImage();
            this.scaledFootprintImage = footprintImage.getScaledInstance((int) (width * 0.1), (int) (height * 0.2), 100);
            this.demoButtonImage = Resources.instance().getDemoButton();
            this.scaledDemoButtonImage = demoButtonImage.getScaledInstance((int) (width * 0.05), (int) (height * 0.1), 100);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(this.scaledFootprintImage, (int) (width * 0.44), (int) (height * 0.7), null);
            g.drawImage(this.scaledDemoButtonImage, (int) (width * 0.6), (int) (height * 0.75), null);
        }
    }
}
