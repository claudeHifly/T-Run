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
public class ArrowUp extends Item {

    public ArrowUp(int x, int y) {
        super(x, y, Resources.instance().getArrowUp());
    }

    @Override
    public void collisionAction() {
        TRex.setState(TRex.getJumping());
    }

}
