/*
 * This class is used to represent the TRex when it has reached the Pepper power-up.
 */
package trex;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author G8
 */
public class PepperPower implements TrexPower {

    private final BufferedImage auraTRex;//immagine TRex coun aura di fuoco

    public PepperPower() {
        this.auraTRex = Resources.instance().getAura();
    }

    @Override
    public void create(Graphics g) {
        Trex trex = Trex.getInstance();
        g.drawImage(this.auraTRex, trex.x, trex.y, null);
    }

}
