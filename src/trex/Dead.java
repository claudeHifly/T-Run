/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import components.HealthBar;
import general.Board;
import general.UserInterface;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author Angela
 */
public class Dead implements TrexState {

    private final Trex trex;
    private final BufferedImage deadTRex;//immagine TRex morto

    public Dead(Trex trex) {
        this.trex = trex;
        this.deadTRex = Resources.instance().getDinoBigEyesCol();
    }

    @Override
    public void create(Graphics g) {
       //trex.deltaT=0;
        Board.running = false;
        Board.gameOver = true;
        HealthBar.instance().create(g);
        g.drawImage(this.deadTRex, trex.x, trex.y, null);
        g.setFont(new Font("Courier New", Font.BOLD, 25));

        g.drawString("GAME OVER", (int) (UserInterface.width*0.46), (int) (UserInterface.height*0.37));
        g.drawString("SCORE: "+Board.score, (int) (UserInterface.width*0.46), (int) (UserInterface.height*0.42));
        
        g.drawString("Press ENTER to restart", (int) (UserInterface.width*0.39), (int) (UserInterface.height*0.51));
        g.drawString("Press SPACE to save score",(int) (UserInterface.width*0.39), (int) (UserInterface.height*0.56)); 
        
        HealthBar.instance().setInstance(null);
    }
}
