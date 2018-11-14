/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import main.UserInterface;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    public BufferedImage grass_ground;//immagine suolo
    private int x_position;
    private int y_position;
    
    public Ground(){
        this(0,0);
    }
    
    public Ground(int x, int y){
        //GROUND
        this.x_position = 0;
        this.y_position = (int)(UserInterface.height*0.75);
        
        //OLD
        this.grass_ground = new Utility().create("src/image/coloured/T-Run_ground_grass2.png");
        
        //COLOURED
        //this.grass_ground = new Utility().create("src/image/COLOURED/T-Run_ground_grass2.png");

    
    }
    
    /*
    private void loadImage() {
        ImageIcon iconGround = new ImageIcon("src/image/coloured/T-Run_ground_grass2.png");
        grass_ground = iconGround.getImage();        
    }*/
    
    public void create(Graphics g) {
        g.drawImage(grass_ground, this.x_position, this.y_position, null);
        
        //Move the x_position left for next time
        this.x_position -= 5;
        
        //Quando il ground è scomparso completamente andando a sinistra
        if (this.x_position <= -1 * grass_ground.getWidth()) {
 
            // If it has, line it back up so that its left edge is
            // lined up to the right side of the other background image
            this.x_position = this.x_position + grass_ground.getWidth() * 2;
        }
    }
    
    public void setX(int x) {
        this.x_position = x;
    }
    public int getX() {
        return this.x_position;
    }
    public int getY() {
        return this.y_position;
    }
    public int getImageWidth() {
        return grass_ground.getWidth();
    }
 
}
