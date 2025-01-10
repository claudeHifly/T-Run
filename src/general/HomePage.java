/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import scoreboard.ScoreUserInterface;
import scoreboard.Scoreboard;
import utility.Resources;
import utility.Sound;

/**
 *
 * @author G8
 */
public class HomePage extends JFrame {

    private static String levelMusic = "src/music/JurassicParkTheme.wav";
    int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8);
    int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
    public static boolean demo;
    private final Sound levelMusicSound = Sound.getSoundInstance(levelMusic);
    private static HomePage instance = null;
    public HomePage() {
        setFocusable(true);//keyListener
        demo = false;
        JPanel panel = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                BufferedImage backImage = Resources.instance().getHomepageBackgroundImage();
                Image scaledBackImage = backImage.getScaledInstance(width, height, 100);
                g.drawImage(scaledBackImage, 0, 0, this);
            }
        };

        ImageShowingComponent footprint = new ImageShowingComponent(this);
        panel.setLayout(new BorderLayout());
        panel.add(footprint);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.levelMusicSound.playSound();//MUSICA
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        validate();
        repaint();
    }
    public static HomePage getHomePageInstance(){
       if (instance == null){
           instance = new HomePage();
       }
       return instance;
    }
    public static void main(String[] args) {
        Resources.instance();
        HomePage frame = getHomePageInstance();
    }
    private class ImageShowingComponent extends JComponent {

        // The image to display
        private final BufferedImage footprintImage;
        private final Image scaledFootprintImage;
        private final BufferedImage demoButtonImage;
        private final Image scaledDemoButtonImage;
        private BufferedImage soundButtonImage;
        private Image scaledSoundButtonImage;
        private BufferedImage rankButtonImage;
        private Image scaledRankButtonImage;
        private final MouseListener listener;

        // Instantiate the panel and perform initialization
        ImageShowingComponent(HomePage frame) {
            this.listener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int xOnScreen = e.getX();
                    int yOnScreen = e.getY();

                    //Check if the click is in the area of the start button image
                    if ((xOnScreen >= (int) (width * 0.44) && xOnScreen <= (int) (width * 0.44) + (int) (width * 0.1)) && (yOnScreen >= (int) (height * 0.7) && yOnScreen <= (int) (height * 0.7) + (int) (height * 0.2))) {
                        frame.setVisible(false);
                        //
                        // frame.removeAll();
                        UserInterface.instance().setVisible(true);
                        Board.demo = false;
                        //levelMusicSound.stopSound();
                    } //Check if the click is in the area of the demo button image
                    else if ((xOnScreen >= (int) (width * 0.6) && xOnScreen <= (int) (width * 0.6) + (int) (width * 0.05)) && (yOnScreen >= (int) (height * 0.75) && yOnScreen <= (int) (height * 0.75) + (int) (height * 0.1))) {
                        demo = true;
                        //levelMusicSound.stopSound();
                        frame.setVisible(false);
                        UserInterface.instance().setVisible(true);
                    }
                    else if ((xOnScreen >= (int) (width * 0.95) && xOnScreen <= (int) (width * 0.95) + (int) (width * 0.035)) && (yOnScreen >= (int) (height * 0.01) && yOnScreen <= (int) (height * 0.01) + (int) (height * 0.07))) {
                        switchMusicIcon(getGraphics(), levelMusicSound.switchSound());
                    }
                    else if ((xOnScreen >= (int) (width * 0.91) && xOnScreen <= (int) (width * 0.91) + (int) (width * 0.035)) && (yOnScreen >= (int) (height * 0.01) && yOnScreen <= (int) (height * 0.01) + (int) (height * 0.07))) {
                        ScoreUserInterface.instance("AAA");
                    }
                }
            };
            this.addMouseListener(listener);
            this.footprintImage = Resources.instance().getHomepageFootprintImage();
            this.scaledFootprintImage = footprintImage.getScaledInstance((int) (width * 0.1), (int) (height * 0.2), 100);
            this.demoButtonImage = Resources.instance().getDemoButton();
            this.scaledDemoButtonImage = demoButtonImage.getScaledInstance((int) (width * 0.05), (int) (height * 0.1), 100);
            if (levelMusicSound.isSoundOn()) {
                this.soundButtonImage = Resources.instance().getSoundButton();
            }else {
                this.soundButtonImage = Resources.instance().getOffSoundButton();
            }
            this.scaledSoundButtonImage = soundButtonImage.getScaledInstance((int) (width * 0.035), (int) (height * 0.07), 100);
            this.rankButtonImage = Resources.instance().getRankButton();
            this.scaledRankButtonImage = rankButtonImage.getScaledInstance((int) (width * 0.035), (int) (height * 0.07), 100);
        }

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(this.scaledFootprintImage, (int) (width * 0.44), (int) (height * 0.7), null);
            g.drawImage(this.scaledDemoButtonImage, (int) (width * 0.6), (int) (height * 0.75), null);
            g.drawImage(this.scaledSoundButtonImage, (int) (width * 0.95), (int) (height * 0.01), null);
            g.drawImage(this.scaledRankButtonImage, (int) (width * 0.91), (int) (height * 0.01), null);
        }

        public void switchMusicIcon(Graphics g, boolean soundOn) {
            if (soundOn) {
                this.soundButtonImage = Resources.instance().getSoundButton();
            }else{
                this.soundButtonImage = Resources.instance().getOffSoundButton();
            }
            this.scaledSoundButtonImage = soundButtonImage.getScaledInstance((int) (width * 0.035), (int) (height * 0.07), 100);
            g.drawImage(this.scaledSoundButtonImage, (int) (width * 0.95), (int) (height * 0.01), null);
        }
    }
}

