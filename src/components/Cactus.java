/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import java.awt.geom.Area;
import utility.*;

/**
 *
 * @author G8
 */
public class Cactus extends Item {

    public Cactus(int x, int y) {
        super(x, y, Resources.instance().getCactusCol());
    }

    @Override
    public void collisionAction(Item collidedItem) {

        if (super.TRex.getPower() == TRex.pepperPower) {
            System.out.println("BRUCIA CACTUS");

            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 5;
            } else {
                Board.coin += 5;
            }
        } else {
            Board.running = false;
            Board.gameOver = true;
            super.TRex.setState(TRex.getDead());
        }
    }

}
