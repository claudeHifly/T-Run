/*
 * This class is used to represent the Falling state of the TRex, that is
 * the state in which the TRex is falling in a canyon.
 */
package trex;

import components.Ground;
import components.HealthBar;
import general.UserInterface;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.Resources;
import utility.Utility;

/**
 *
 * @author G8
 */
public class Falling implements TrexState {

    private final BufferedImage fallingImage;//immagine TRex fall
    private final BufferedImage auraImage;

    public Falling() {
        this.fallingImage = Resources.instance().getDinoBigEyes();
        this.auraImage = Resources.instance().getAura();
    }

    /**
     * This method is used to manage the Trex falling physics.
     */
    @Override
    public void create(Graphics g) {
        Trex trex = Trex.getInstance();
        if (trex.y < UserInterface.instance().height) {
            Ground.movementSpeed0 = 0;
            Ground.movementSpeed = 0;
            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.fallingImage, trex.x, trex.y, null);
            Trex.instance().collider = Utility.instance().createCollider(fallingImage, Trex.instance().x, Trex.instance().y);
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.speedForJumping += (trex.deltaT * trex.gravity);
        } else {
            trex.setMultiplier(false);
            HealthBar.instance().decrease(HealthBar.MAX);

            trex.create(g);
        }
    }

}
