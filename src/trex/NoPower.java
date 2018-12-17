/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import java.awt.Graphics;

/**
 *
 * @author Gennaro
 */
public class NoPower implements TrexPower{
    
    private final Trex trex;
    
    public NoPower (Trex trex){
       this.trex = trex; 
    }

    @Override
    public void create(Graphics g) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
