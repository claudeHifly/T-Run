/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;

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
            //Board.coin += ((Bone) collidedItem).getValue();
            //Board.score += 1;
            System.out.println("HO PRESO IL PEPPER");
    }

    
}
