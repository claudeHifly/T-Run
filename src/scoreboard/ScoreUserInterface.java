/*
 * This class represents the scoreboard window.
 * This class is implemented according with the Singleton design pattern.
 */
package scoreboard;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author G8
 */
public class ScoreUserInterface extends JFrame {

    public static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.295);
    public static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.65);
    static String playerName;
    private static ScoreUserInterface instance = null;

    private ScoreUserInterface() {

        this.add(new ScorePanel());
        this.setTitle("T-Run Scoreboard");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public static ScoreUserInterface instance(String playerName) {
        if (instance == null) {
            instance = new ScoreUserInterface();
        }

        ScoreUserInterface.playerName = playerName;
        instance.setVisible(true);
        return instance;
    }

}
