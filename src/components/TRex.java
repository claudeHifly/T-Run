/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import javax.swing.*;
import main.UserInterface;

/**
 *
 * @author Gennaro
 */
public class TRex {
    
    private Image imageTRex;//immagine TRex
    private int wTRex;
    private int hTRex;

    public TRex(){
        initTRex();
    }
    
    private void initTRex(){
        loadImage();
        
        this.wTRex = imageTRex.getWidth(null);
        this.hTRex = imageTRex.getHeight(null);
        System.out.println("TRex width: " + wTRex);
        System.out.println("TRex height: " + hTRex);
         
    }
    
    private void loadImage(){
        ImageIcon iconTRex = new ImageIcon("src/image/old/Dino-stand.png");
        imageTRex = iconTRex.getImage();
    }
    
    
    public void create(Graphics g) {
        g.drawImage(imageTRex, 50, (int)(UserInterface.height*0.8-hTRex), null);
    }
    
}
