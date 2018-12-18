/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import components.Ground;
import general.UserInterface;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author Gennaro
 */
public class Falling implements TrexState {
    
    private Trex trex;
    private final BufferedImage fallingImage;//immagine TRex fall
    private final BufferedImage auraImage;
    
    public Falling(Trex trex) {
        this.trex = trex;
        this.fallingImage = Resources.instance().getDinoBigEyesCol();
        this.auraImage = Resources.instance().getAuraCol();
    }
    
    @Override
    public void create(Graphics g) {
        if (trex.y < UserInterface.instance().height) {
            
            Ground.movementSpeed0 = 0;
            Ground.movementSpeed = 0;
            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.fallingImage, trex.x, trex.y, null);
            if(trex.getPower() == trex.pepperPower){
                     g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
            trex.speedForJumping += (trex.deltaT * trex.gravity);
        } else {
            trex.setMultiplier(false);
            trex.setState(trex.getDead());
            
            trex.create(g);
        }
    }
    
}
