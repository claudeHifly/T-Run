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
import utility.Resources;

/**
 *
 * @author Gennaro
 */
public class Falling implements TrexState {
    
    private final Trex trex;
    private final BufferedImage fallingImage;//immagine TRex fall
    
    public Falling(Trex trex) {
        this.trex = trex;
        this.fallingImage = Resources.instance().getDinoStandCol();
    }
    
    @Override
    public void create(Graphics g) {
        if (trex.y < UserInterface.instance().height) {
            System.out.println("cadooooo");
            Ground.movementSpeed0 = 0;
            Ground.movementSpeed = 0;
            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.fallingImage, trex.x, trex.y, null);
            trex.speedForJumping += (trex.deltaT * trex.gravity);
        } else {
            trex.setState(trex.getDead());
            System.out.println("morto");
            trex.create(g);
        }
    }
    
}
