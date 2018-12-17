/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Timer;

/**
 *
 * @author Gennaro
 */
public class Multiplier extends Item{
    
    public Multiplier(int x, int y, String path) {
             super(x, y, path);
             
     }


    @Override
    public void collisionAction(Item collidedItem) {
            super.TRex.setMultiplier(true);
            System.out.println("HO PRESO IL MOLTIPLICATIORE");
            mulCountdown();
    }
    
    public void mulCountdown(){
        
        Timer mulTimer = new java.util.Timer();
        
        mulTimer.schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    //System.out.println("Time Over -> No PepperPower");
                    TRex.setMultiplier(false);              //resetto il moltiplicatore
                    mulTimer.cancel();
                    
                }
            }, 
            10000   //10 secondi di powerUP Pepper
        );

    }
    
}