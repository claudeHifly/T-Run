/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.*;
import java.awt.*;
import components.*;

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
        
        //TREX
        TRex = new TRex();
        
        //GROUND
        grass_ground = new Ground();
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        TRex.create(g);
        grass_ground.create(g);
        
    }
    
    /*
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
        
        //posizione ground al 20% dell'altezza del JFrame
        g.drawImage(grass_ground, 0, (int)(UserInterface.height*0.8), null); 
    } 
    
 
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(TRex.getImage(), TRex.getX(), 
            TRex.getY(), this);
    }*/
}

