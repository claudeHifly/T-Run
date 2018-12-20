/*
 * This class is used to represent the Jumping state of the TRex, that is the state
 * in which the TRex jump in order to avoid an obstacle.
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

    private final Trex trex;
    private final BufferedImage jumpingImage;//immagine TRex rightFoot
    private final BufferedImage auraImage;

    public Jumping(Trex trex) {
        this.trex = trex;

        this.jumpingImage = Resources.instance().getDinoStand();
        this.auraImage = Resources.instance().getAura();
    }

/**
 * This method is used to manage the TRex's jump physics. 
 */
    @Override
    public void create(Graphics g) {

        if (((trex.speedForJumping >= 0)) && trex.topReached == false) {

            trex.y -= trex.deltaT * trex.speedForJumping;
            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.collider = Utility.instance().createCollider(jumpingImage, trex.x, trex.y);
            trex.speedForJumping -= (trex.deltaT * trex.gravity);

        }

        if ((trex.speedForJumping <= 0) && trex.topReached == false) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.topReached = true;
        }

        if (trex.topReached == true) {

            if (trex.speedForJumping < 0) {
                trex.speedForJumping = 0;
            }

            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.collider = Utility.instance().createCollider(jumpingImage, trex.x, trex.y);
            trex.speedForJumping += (trex.deltaT * trex.gravity);

        }

        if ((Ground.movementSpeed > 20 && trex.y >= trex.TRexOnGround - 75 && trex.topReached == true)
                || (Board.ground.hasCollided(trex.getCollider()))) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);

            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.y = trex.TRexOnGround;
            trex.collider = Utility.instance().createCollider(jumpingImage, trex.x, trex.y);

            trex.topReached = false;
            trex.speedForJumping = (float) (6 * 2.2);
            trex.setState(trex.getRunning());
        }

        if ((Ground.movementSpeed <= 20 && trex.y >= trex.TRexOnGround - 25 && trex.topReached == true)
                || (Board.ground.hasCollided(trex.getCollider()))) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);

            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.y = trex.TRexOnGround;
            trex.collider = Utility.instance().createCollider(jumpingImage, trex.x, trex.y);

            trex.topReached = false;
            trex.speedForJumping = (float) (6 * 2.2);
            trex.setState(trex.getRunning());
        }
    }
}
