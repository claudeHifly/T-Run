/*
 * This class implements a kind of obstacle, the cactus.
 */
package components;

import general.Board;
import general.HomePage;
import utility.*;

/**
 *
 * @author G8
 */
public class Cactus extends Item {
    private final int distanceArrowCactus = 40;

/**
 * This is the class constructor.
 * If the game is in demo mode, this method combines up arrows with cactus to indicate the action that must be performed by the character
 * in order to avoid the obstacle.
 */
    public Cactus(int x, int y) {
        super(x, y, Resources.instance().getCactus());
        if (HomePage.demo) {
            Board.arrows.addArrowUp(x - distanceArrowCactus, (int) (Ground.yPosition));
        }
    }

/**
 * This method manage the character collision with the obstacle.
 * If the character does not have the pepper power-up, after the collision the health bar level is decreased.
 * Otherwise, the character gains some bonus points. If the character also has the multiplier power-up, the bonus points made are doubled.
 */
    @Override
    public void collisionAction() {

        if (super.TRex.getPower() == TRex.pepperPower) {

            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 5;
            } else {
                Board.coin += 5;
            }
        } else {
            if(!Board.gameOver)
                HealthBar.instance().decrease(1);
        }
    }

}
