/*
 * This class implements a way to simulate a canyon.
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

/**
 * This is the class constructor.
 * If the game is in demo mode, this method add an up arrow to indicate the action that must be performed by the character
 * in order to avoid the canyon.
 */
    public Empty(int x) {
        super(x, -1, Resources.instance().getEmpty());
        if (HomePage.demo) {
            Board.arrows.addArrowUp(x - distanceArrowCanyon, (int) (Ground.yPosition));
            
        }
    }

    @Override
    public void collisionAction() {
    }

}
