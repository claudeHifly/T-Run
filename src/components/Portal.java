/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import general.UserInterface;
import java.awt.Font;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author sivoc
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
        //super.TRex.setState(TRex.getDead());
    }
    
}
