/*
 * The purpose of this class is to implement the multiplier functionality that allows to double the bonus points gain by the character.
 * The multiplier is implemented as an item.
 */
package components;

import java.util.Timer;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Multiplier extends Item {

    public Multiplier(int x, int y) {
        super(x, y, Resources.instance().getMulScore());
    }

    /**
     * This method sets the Trex's boolean 'multiplier' attribute to True when
     * the character collides with the multiplier item. Then a countdown is
     * switched on.
     */
    @Override
    public void collisionAction() {
        super.TRex.setMultiplier(true);
        mulCountdown();
    }

    /**
     * This method implements, by using a timer, the contdown associated with
     * the multiplier item. After 10s the multiplier effects is cancelled.
     */
    public void mulCountdown() {
        Timer mulTimer = new java.util.Timer();
        mulTimer.schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                TRex.setMultiplier(false);              //resetto il moltiplicatore
                mulTimer.cancel();
            }
        },
                10000 //10 seconds of multiplier power up
        );
    }
}
