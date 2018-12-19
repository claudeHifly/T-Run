/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import general.HomePage;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Empty extends Item {

    private final int distanceArrowCanyon = 55;

    public Empty(int x, int y) {
        super(x, y, Resources.instance().getEmpty());
        if (HomePage.demo) {
            Board.arrows.addArrowUp(x - distanceArrowCanyon, (int) (Ground.yPosition));
            
        }
    }

    @Override
    public void collisionAction() {
    }

}
