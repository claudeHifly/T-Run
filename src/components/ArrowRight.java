/*
 * The purpose of this class is to build the right arrows used in the demo mode.
 * When the character collides with a right arrow, the 'collisionAction' method set the state of the character to 'Running'.
 */
package components;

import utility.Resources;

/**
 *
 * @author G8
 */
public class ArrowRight extends Item {

    public ArrowRight(int x, int y) {
        super(x, y, Resources.instance().getArrowRight());
    }

    @Override
    public void collisionAction() {
        TRex.setState(TRex.getRunning());
    }

}
