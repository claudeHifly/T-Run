/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    @Override
    public void collisionAction() {

        System.out.println("HO PRESO IL PEPPER");
        
        super.TRex.setPower(TRex.getPepperPower());
        pepperCountdown();

    }

    public void pepperCountdown() {
        Timer pepperTimer = new java.util.Timer();

        pepperTimer.schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                TRex.setPower(TRex.getNoPower());       //resetto il powerUP      //ripristino la probabilità di spawn del peperoncino
                pepperTimer.cancel();

            }
        },
                15000 //10 secondi di powerUP Pepper
        );

    }//fine pepperCountdown;

}
