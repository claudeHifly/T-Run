/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 *
 * @author Gennaro
 */

public class Pepper extends Item{
    
     public Pepper(int x, int y, String path) {
             super(x, y, path);  
     }
     

    @Override
    public void collisionAction(Item collidedItem) {

        System.out.println("HO PRESO IL PEPPER");
        Bones.probabilityOfPepper = 0;
        super.TRex.setPower(TRex.getPepperPower());
        pepperCountdown();

    }
    
    public void pepperCountdown(){
        
        Timer pepperTimer = new java.util.Timer();
        
        pepperTimer.schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    //System.out.println("Time Over -> No PepperPower");
                    TRex.setPower(TRex.getNoPower());       //resetto il powerUP
                    Bones.probabilityOfPepper = 0.65;       //ripristino la probabilità di spawn del peperoncino
                    pepperTimer.cancel();
                    
                }
            }, 
            15000   //10 secondi di powerUP Pepper
        );
        
        
    }//fine pepperCountdown;

    
}
