/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Bone extends Item {

    private final int value;

    public Bone(int x, int y) {
        super(x, y, Resources.instance().getBone());
        this.value = 1;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void collisionAction() {
        if (super.TRex.multiplier == true) {
            Board.coin += 2 * value;
            //Board.score += 1;
        } else {
            Board.coin += value;
        }
    }

}
