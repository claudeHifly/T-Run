/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import main.UserInterface;
import utility.Utility;



/**
 *
 * @author Gennaro
 */
public class TRex{
    
    private BufferedImage image;//immagine TRex stand
    private BufferedImage leftFootDino;//immagine TRex leftFoot
    private BufferedImage rightFootDino;//immagine TRex rightFoot
    public final static int X = 50;
    private int y;
    public int wTRex;
    public int hTRex;
    private Area collider;
    private int foot;
    
    private final int   LEFT_FOOT = 1,
                        RIGHT_FOOT = 2,
                        NO_FOOT = 3;

    public TRex(){
        image = new Utility().create("src/image/old/Dino-stand.png");
        leftFootDino = new Utility().create("src/image/old/Dino-left-up.png");
        rightFootDino = new Utility().create("src/image/old/Dino-right-up.png");
        wTRex = image.getWidth(null);
        hTRex = image.getHeight(null);
        y = (int)(Ground.yPosition)+ (int)(Ground.yPosition *0.025) - hTRex;
        System.out.println("TRex width: " + wTRex);
        System.out.println("TRex height: " + hTRex);
        foot = NO_FOOT;//inizializzo
        collider = new Area(new Rectangle(X, y, image.getWidth(), image.getHeight()));
    }
    
    public void create(Graphics g) {
        //g.drawImage(image, X, y, null);
        
        if(foot == NO_FOOT) {
          foot = LEFT_FOOT;
          g.drawImage(leftFootDino, X, y, null);
        } else if(foot == LEFT_FOOT) {
          foot = RIGHT_FOOT;
          g.drawImage(rightFootDino, X, y, null);
        } else {
          foot = LEFT_FOOT;
          g.drawImage(leftFootDino, X, y, null);
        }
    }

    public int getwTRex() {
        return wTRex;
    }

    public void setwTRex(int wTRex) {
        this.wTRex = wTRex;
    }

    public int gethTRex() {
        return hTRex;
    }

    public void sethTRex(int hTRex) {
        this.hTRex = hTRex;
    }
    
    
    
    
    //SEVIVA PER IconImage, ho convertito a BufferedImage come stabilito
    /*
    private void loadImage(){
        ImageIcon iconTRex = new ImageIcon("src/image/old/Dino-stand.png");
        imageTRex = iconTRex.getImage();
    }*/
}

