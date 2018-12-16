/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import utility.Utility;

/**
 *
 * @author claud
 */
public class Bird extends Item{
       
    public Bird(int x, int y) {
        super(x, y, "image/color/bird1.png");
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
        Board.running = false;
        Board.gameOver = true;
        super.TRex.setState(TRex.getDead()); 
        }
        
        
    }
    
    
            
}
