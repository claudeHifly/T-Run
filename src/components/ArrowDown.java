/*
 * The purpose of this class is to build the down arrows used in the demo mode.
 * When the character collides with a down arrow, the 'collisionAction' method set the state of the character to 'LowerHead'.
 */
package components;

import utility.Resources;

/**
 *
 * @author G8
 */
public class ArrowDown extends Item {

    public ArrowDown(int x, int y) {
        super(x, y, Resources.instance().getArrowDown());
    }

    @Override
    public void collisionAction() {
        TRex.setState(TRex.getLowerHead());
    }
}
