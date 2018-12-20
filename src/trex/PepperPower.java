/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    private final Trex trex;
    private final BufferedImage auraTRex;

    public PepperPower(Trex trex) {
        this.trex = trex;
        this.auraTRex = Resources.instance().getAura();
    }

    @Override
    public void create(Graphics g) {
        g.drawImage(this.auraTRex, trex.x, trex.y, null);
    }

}
