/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboard;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author G8
 */
public class ScoreUserInterface extends JFrame {

    public static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.295);    //dovrebbero essere final
    public static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.65);
    static String playerName;
    private static ScoreUserInterface instance = null;

    private ScoreUserInterface() {

        this.add(new ScorePanel());//con questo metodo inserisco una Board al centro del contenitore JFrame
        this.setTitle("T-Run Scoreboard");//setto il titolo
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//setto l'operazione di default da eseguire quando chiudo la finestra

        this.setSize(width, height);
        this.setLocationRelativeTo(null);//in questo modo posiziono il JFrame al centro dello schermo
        this.setResizable(true);//dimensioni JFrame non modificabili
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
