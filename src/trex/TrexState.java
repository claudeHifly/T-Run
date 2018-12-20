/*
 * This class is used to implement the main character of the game, the TRex, according to the State design pattern.
 * This is the interface of the TRex and all the class that implement the TRex state must implement the own create method.
 */
package trex;

import java.awt.Graphics;

/**
 *
 * @author G8
 */
public interface TrexState {

    public void create(Graphics g);

}
