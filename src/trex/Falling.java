/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import components.Ground;
import general.Board;
import general.UserInterface;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import resources.Resources;

/**
 *
 * @author Gennaro
 */
public class Falling implements TrexState {
    
    private final Trex trex;
    private final BufferedImage fallingImage;//immagine TRex fall
    
    public Falling(Trex trex) {
        this.trex = trex;
        this.fallingImage = Resources.instance().getJumpingImage();
    }
    
    @Override
    public void create(Graphics g) {
        if (trex.y < UserInterface.instance().getHeight()) {
            System.out.println("cadooooo");
            Ground.movementSpeed0 = 0;
            Ground.movementSpeed = 0;
            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.fallingImage, trex.x, trex.y, null);
            trex.speedForJumping += (trex.deltaT * trex.gravity);
        } else {
            
            System.out.println("morto");
            g.setFont(new Font("Courier New", Font.BOLD, 25));
            g.drawString("GAME OVER", UserInterface.width / 2 - 70, UserInterface.height / 2);
            g.drawString("Press ENTER to restart", UserInterface.width / 2 - 150, UserInterface.height / 2 + 35);
            trex.setState(trex.getDead());
            Board.running = false;
            Board.gameOver = true;
            

        }
    }
    
}
