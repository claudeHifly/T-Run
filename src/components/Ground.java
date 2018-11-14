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

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    public BufferedImage grass_ground;//immagine suolo
    
    public Ground(){
        initGround();
    }
    
    private void initGround(){
        //GROUND
        try {
            this.grass_ground = ImageIO.read(new File("src/image/coloured/T-Run_ground_grass2.png"));
        } catch (IOException e) {
            System.out.println("Error! Ground not found!");
        }
        //loadImage();//ho lasciato load image perchè potremmo aver bisogno 
                    //di questo metodo per cambiare il ground
        
        //????????????????????????????????????????????????????????
        /*
        int w = grass_ground.getWidth((ImageObserver)this);
        int h = grass_ground.getHeight((ImageObserver)this);
        setPreferredSize(new Dimension(w, h));
        */
        //???????????????????????????????????????????????????????????
    }
    
    /*
    private void loadImage() {
        ImageIcon iconGround = new ImageIcon("src/image/coloured/T-Run_ground_grass2.png");
        grass_ground = iconGround.getImage();        
    }*/
    
    public void create(Graphics g) {
        g.drawImage(grass_ground, 0, (int)(UserInterface.height*0.75), null);
    }
}
