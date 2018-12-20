/*
 * This class implements a kind of collectable item, the special bone.
 */
package components;

import general.Board;
import utility.Resources;

/**
 *
 * @author G8
 */
public class BoneSpecial extends Item {

    private final int value;

    public BoneSpecial(int x, int y) {
        super(x, y, Resources.instance().getBoneSpecial());
        this.value = 10;
    }

    public int getValue() {
        return value;
    }
/**
 * This method manage the character collision with the item.
 * After a collision with the item, the character gains ten bonus points.
 * If the character has the multiplier power-up, the bonus points made are twenty.
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
