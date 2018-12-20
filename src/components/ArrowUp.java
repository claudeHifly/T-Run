/*
 * The purpose of this class is to build the up arrows used in the demo mode.
 * When the character collides with an up arrow, the 'collisionAction' method set the state of the character to 'Jumping'.
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
