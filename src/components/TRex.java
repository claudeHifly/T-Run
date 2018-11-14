/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.geom.Area;
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
    
    private BufferedImage image; //immagine TRex
    private int wTRex;
    private int hTRex;
    private Area collider;

    public TRex(){
        image = new Utility().create("src/image/old/Dino-stand.png");
        wTRex = image.getWidth(null);
        hTRex = image.getHeight(null);
        System.out.println("TRex width: " + wTRex);
        System.out.println("TRex height: " + hTRex);
        collider = new Area(new Rectangle());
    }
    
    public void create(Graphics g) {
        g.drawImage(image, 50, (int)(UserInterface.height*0.8-hTRex), null);
    }
    
}
    
    //SEVIVA PER IconImage, ho convertito a BufferedImage come stabilito
    /*
    private void loadImage(){
        ImageIcon iconTRex = new ImageIcon("src/image/old/Dino-stand.png");
        imageTRex = iconTRex.getImage();
    }*/
    

