/*
 * The purpose of this class is to implement a character super mode that allows to gain bonus points by colliding with the obstacles.
 */
package components;

import java.util.Timer;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Pepper extends Item {


    public Pepper(int x, int y) {
        super(x, y, Resources.instance().getPepper());
    }
    
/**
 * This method sets the Trex's state to 'PepperPower' when the character collides with the pepper item.
 * Then a countdown is switched on.
 */
    @Override
    public void collisionAction() {        
        super.TRex.setPower(TRex.getPepperPower());
        pepperCountdown();

    }
    
/**
 * This method implements, by using a timer, the contdown associated with the pepper item.
 * After 15s the pepper effects is cancelled.
 */  
    public void pepperCountdown() {
        int normalFrequency = PowerUp.frequencyPepper;
        PowerUp.frequencyPepper = 0;
        Timer pepperTimer = new java.util.Timer();

        pepperTimer.schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                TRex.setPower(TRex.getNoPower());                //reset of the power up
                PowerUp.frequencyPepper = normalFrequency;       //reset of the pepper's spawn probability
                pepperTimer.cancel();

            }
        },
                15000                                            //15 seconds of pepper power up
        );

    }                                                            //end of pepperCountdown;

}
