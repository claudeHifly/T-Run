/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.*;
import java.awt.*;
import components.*;
import java.awt.image.BufferedImage;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class Board extends JPanel{
    
    private TRex TRex;
    private Ground grass_ground;
   
    //INIZIALIZZO BOARD
    public Board() {
       initBoard();
    }
    
    private void initBoard(){
        
        //GROUND
        grass_ground = new Ground();
        
        //TREX
        TRex = new TRex();
   
        
        
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        TRex.create(g);
        grass_ground.create(g);
        g.dispose();
    }
    
    
}

