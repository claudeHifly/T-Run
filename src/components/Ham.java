/*
 * This class implements a kind of power-up, the ham, that increases the health bar level by 20%.
 */
package components;

import utility.Resources;

/**
 *
 * @author G8
 */
public class Ham extends Item {

    public Ham(int x, int y) {
        super(x, y, Resources.instance().getHam());

    }

    @Override
    public void collisionAction() {
            HealthBar.instance().increase(20);
    }
}
