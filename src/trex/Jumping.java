/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import components.Ground;
import general.Board;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author G8
 */
public class Jumping implements TrexState {

    private final BufferedImage jumpingImage;//immagine TRex rightFoot
    private final BufferedImage auraImage;

    public Jumping() {
        this.jumpingImage = Resources.instance().getDinoStand();
        this.auraImage = Resources.instance().getAura();
    }

    @Override
    public void create(Graphics g) {
        if (((Trex.instance().speedForJumping >= 0)) && Trex.instance().topReached == false) {
            Trex.instance().y -= Trex.instance().deltaT * Trex.instance().speedForJumping;
            g.drawImage(this.jumpingImage, Trex.instance().x, Trex.instance().y, null);
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(auraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().collider = Utility.instance().createCollider(jumpingImage, Trex.instance().x, Trex.instance().y);
            Trex.instance().speedForJumping -= (Trex.instance().deltaT * Trex.instance().gravity);
        }
        if ((Trex.instance().speedForJumping <= 0) && Trex.instance().topReached == false) {
            g.drawImage(this.jumpingImage, Trex.instance().x, Trex.instance().y, null);
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(auraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().topReached = true;
        }
        if (Trex.instance().topReached == true) {
            if (Trex.instance().speedForJumping < 0) {
                Trex.instance().speedForJumping = 0;
            }
            Trex.instance().y += Trex.instance().deltaT * Trex.instance().speedForJumping;
            g.drawImage(this.jumpingImage, Trex.instance().x, Trex.instance().y, null);
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(auraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().collider = Utility.instance().createCollider(jumpingImage, Trex.instance().x, Trex.instance().y);
            Trex.instance().speedForJumping += (Trex.instance().deltaT * Trex.instance().gravity);
        }
        if ((Ground.movementSpeed > 20 && Trex.instance().y >= Trex.instance().TRexOnGround - 75 && Trex.instance().topReached == true)
                || (Board.ground.hasCollided(Trex.instance().getCollider()))) {
            g.drawImage(this.jumpingImage, Trex.instance().x, Trex.instance().y, null); 
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(auraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().y = Trex.instance().TRexOnGround;
            Trex.instance().collider = Utility.instance().createCollider(jumpingImage, Trex.instance().x, Trex.instance().y);
            Trex.instance().topReached = false;
            Trex.instance().speedForJumping = (float) (6 * 2.2);
            Trex.instance().setState(Trex.instance().getRunning());
        }
        if ((Ground.movementSpeed <= 20 && Trex.instance().y >= Trex.instance().TRexOnGround - 25 && Trex.instance().topReached == true)
                || (Board.ground.hasCollided(Trex.instance().getCollider()))) {
            g.drawImage(this.jumpingImage, Trex.instance().x, Trex.instance().y, null);
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(auraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().y = Trex.instance().TRexOnGround;
            Trex.instance().collider = Utility.instance().createCollider(jumpingImage, Trex.instance().x, Trex.instance().y);
            Trex.instance().topReached = false;
            Trex.instance().speedForJumping = (float) (6 * 2.2);
            Trex.instance().setState(Trex.instance().getRunning());
        }
    }
}
