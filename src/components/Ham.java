/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import utility.Resources;

/**
 *
 * @author G8
 */
public class Ham extends Item {

    public Ham(int x, int y) {
        super(x, y, Resources.instance().getHamCol());

    }

    @Override
    public void collisionAction(Item collidedItem) {
        //Board.coin += ((Bone) collidedItem).getValue();
        //Board.score += 1;
        System.out.println("HO PRESO HAM");
    }
}
