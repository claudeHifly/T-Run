/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.image.BufferedImage;

/**
 *
 * @author Gennaro
 */
public class Ham extends Item {
    
    public Ham(int x, int y, BufferedImage image) {
             super(x, y, image);
             
     }


    @Override
    public void collisionAction(Item collidedItem) {
            //Board.coin += ((Bone) collidedItem).getValue();
            //Board.score += 1;
            System.out.println("HO PRESO HAM");
            HealthBar.instance().increase(200);
    }
}
