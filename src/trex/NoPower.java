/*
 * This class is used to represent the TRex without power-up.
 */
package trex;

import java.awt.Graphics;

/**
 *
 * @author G8
 */
public class NoPower implements TrexPower {

    private final Trex trex;

    public NoPower(Trex trex) {
        this.trex = trex;
    }

    @Override
    public void create(Graphics g) {
    }
}
