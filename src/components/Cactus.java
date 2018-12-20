/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import general.HomePage;
import java.awt.geom.Area;
import utility.*;

/**
 *
 * @author G8
 */
public class Cactus extends Item {
    private final int distanceArrowCactus = 40;

    public Cactus(int x, int y) {
        super(x, y, Resources.instance().getCactus());
        if (HomePage.demo) {
            Board.arrows.addArrowUp(x - distanceArrowCactus, (int) (Ground.yPosition));
        }
    }

    @Override
    public void collisionAction() {

        if (super.TRex.getPower() == TRex.pepperPower) {
            //System.out.println("BRUCIA CACTUS");

            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 5;
            } else {
                Board.coin += 5;
            }
        } else {
            if(!Board.gameOver)
                HealthBar.instance().decrease(1);
            
            //Board.running = false;
            //Board.gameOver = true;
            //super.TRex.setState(TRex.getDead());
        }
    }

}
