/*
 * The purpose of this class is to implement an item that allows the design switch of the game.
 * When the character collides with this item, the 'coloGame' attribute is setted to True and so, in the game, are shown the coloured images instead of the black and white ones.
 */
package components;

import general.Board;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Portal extends Item {

    public Portal(int x, int y) {
        super(x, y, Resources.instance().getPortal());
    }

    @Override
    public void collisionAction() {
        Board.running = false;
        Board.colorGame = true;
        Board.gameOver = true;
    }

}
