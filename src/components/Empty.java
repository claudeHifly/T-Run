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
public class Empty extends Item {

    public Empty(int x) {
        super(x, 200, Resources.instance().getEmpty());
    }

    @Override
    public void collisionAction(Item collidedItem) {
    }

}
