/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Gennaro
 */
public class Board extends JPanel{
    
    public Image grass_ground;
     
    public Board() {
        initBoard();
    }
    
    private void initBoard(){
        
        loadImage();
        
        int w = grass_ground.getWidth(this);
        int h = grass_ground.getHeight(this);
        setPreferredSize(new Dimension(w, h)); 
    }
    
    private void loadImage() {
        ImageIcon ii = new ImageIcon("src/image/T-Run_ground_grass.png");
        grass_ground = ii.getImage();        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(grass_ground, 0, 0, null);
    }
}

