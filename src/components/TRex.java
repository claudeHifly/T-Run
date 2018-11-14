/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import main.UserInterface;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class TRex {
    
    private BufferedImage imageTRex; //immagine TRex
    private int wTRex;
    private int hTRex;

    public TRex() {
        initTRex();
    }
    
    private void initTRex(){
        //loadImage();
        
        this.imageTRex = new Utility().create("src/image/old/Dino-stand.png");
  
        /*
        try {
            this.imageTRex = ImageIO.read(new File("src/image/old/Dino-stand.png"));
        } catch (IOException e) {
            System.out.println("Error! TRex not found!");
        }*/
        
        this.wTRex = imageTRex.getWidth(null);
        this.hTRex = imageTRex.getHeight(null);
        System.out.println("TRex width: " + wTRex);
        System.out.println("TRex height: " + hTRex);
         
    }
    
    
    //SEVIVA PER IconImage, ho convertito a BufferedImage come stabilito
    /*
    private void loadImage(){
        ImageIcon iconTRex = new ImageIcon("src/image/old/Dino-stand.png");
        imageTRex = iconTRex.getImage();
    }*/
    
    
    public void create(Graphics g) {
        g.drawImage(imageTRex, 50, (int)(UserInterface.height*0.75-hTRex+5), null);
    }
    
}
