/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import trex.Trex;

/**
 *
 * @author Angela
 */
public class Dead implements TrexState {

    private final BufferedImage deadTRex;//immagine TRex morto

    public Dead() {
        this.deadTRex = Resources.instance().getDinoBigEyes();
    }

    @Override
    public void create(Graphics g) {
        Trex trex = Trex.getInstance();
       //trex.deltaT=0;
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
