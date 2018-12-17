/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

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
public class Dead implements TrexState{

    private final Trex trex;
    private final BufferedImage deadTRex;//immagine TRex morto
    
    public Dead(Trex trex) {
        this.trex = trex;
        this.deadTRex = Resources.instance().getDeadImage();
    }

    @Override
    public void create(Graphics g) {
        Board.running = false;
        Board.gameOver = true;
        g.drawImage(this.deadTRex, trex.x, trex.y, null);
        g.setFont(new Font("Courier New", Font.BOLD, 25));

        g.drawString("GAME OVER", UserInterface.width / 2 - 90, UserInterface.height-490);
        g.drawString("SCORE: "+Board.score, UserInterface.width / 2 - 90, UserInterface.height-450);
        
        g.drawString("Press ENTER to restart", UserInterface.width / 2 - 200, UserInterface.height-380);
        g.drawString("Press SPACE to save score", UserInterface.width / 2 - 200, UserInterface.height-340);        
    }
}
