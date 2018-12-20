/*
 * This class implements a kind of collectable item, the bone.
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

    /**
     * This method manage the character collision with the item. After a
     * collision with the item, the character gains one bonus point. If the
     * character has the multiplier power-up, the bonus points made are two.
     */
    @Override
    public void collisionAction() {
        if (super.TRex.multiplier == true) {
            Board.coin += 2 * value;
        } else {
            Board.coin += value;
        }
    }

}
