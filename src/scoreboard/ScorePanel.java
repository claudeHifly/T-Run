/*
 * This class implements the JPanel in which is shown the scoreboard of the game. 
 */
package scoreboard;

import general.Board;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import utility.Resources;

/**
 *
 * @author G8
 */
public class ScorePanel extends JPanel {

    private BufferedImage scoreboardImage = Resources.instance().getScoreboardBackgroundImage();

    public ScorePanel() {
        this.init();
    }

    private void init() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        this.create(g);
    }

    public void create(Graphics g) {
        Image scaledScoreboardImage = scoreboardImage.getScaledInstance(ScoreUserInterface.width, ScoreUserInterface.height, 100);
        g.drawImage(scaledScoreboardImage, 0, 0, null);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        Scoreboard.loadScores(ScoreUserInterface.playerName, Board.score, g);
    }
}
