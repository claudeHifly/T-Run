/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import utility.*;

/**
 *
 * @author claud
 */
public class Bird extends Item{
       
    public Bird(int x, int y) {
        super(x, y, Resources.instance().getBird1Col());
    }
    
    @Override
    public void collisionAction(Item collidedItem) {
        
        if (super.TRex.getPower() == TRex.pepperPower){
            System.out.println("BRUCIA UCCELLO");
            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 10;
            } else {
                Board.coin += 10;
            }
        } else {
             if(!Board.gameOver)
                HealthBar.instance().decrease(5);
        /*Board.running = false;
        Board.gameOver = true;
        super.TRex.setState(TRex.getDead()); */
        }
        
        
    }
    
    
            
}
