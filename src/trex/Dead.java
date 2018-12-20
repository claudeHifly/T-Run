/*
 * This class is used to represent the Dead state of the TRex.
 */
package trex;

import components.HealthBar;
import general.Board;
import general.HomePage;
import general.UserInterface;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Dead implements TrexState {

    private final Trex trex;
    private final BufferedImage deadTRex;

    public Dead(Trex trex) {
        this.trex = trex;
        this.deadTRex = Resources.instance().getDinoBigEyes();
    }

/**
 * This method is used to enable the restart game functionality and the record saving
 * when the game over.
 * Its behaviour changes if the demo mode is enabled.
 */
    @Override
    public void create(Graphics g) {
        Board.running = false;
        Board.gameOver = true;
        HealthBar.instance().create(g);
        g.drawImage(this.deadTRex, trex.x, trex.y, null);
        g.setFont(new Font("Courier New", Font.BOLD, 25));

        if(!HomePage.demo){
            g.drawString("GAME OVER", (int) (UserInterface.width*0.46), (int) (UserInterface.height*0.37));
            g.drawString("SCORE: "+Board.score, (int) (UserInterface.width*0.46), (int) (UserInterface.height*0.42));
        
            g.drawString("Press ENTER to restart", (int) (UserInterface.width*0.39), (int) (UserInterface.height*0.51));
            g.drawString("Press SPACE to save score",(int) (UserInterface.width*0.39), (int) (UserInterface.height*0.56));
        }
        else
            g.drawString("Press ENTER to start the game!", (int) (UserInterface.width*0.33), (int) (UserInterface.height*0.51));
        
        HealthBar.instance().setInstance(null);
    }
}
