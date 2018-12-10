/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import general.UserInterface;
import utility.Utility;
import static general.Board.distance;
import java.net.URL;

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    public final static int yPosition = (int)(UserInterface.height*0.75);
    public static int movementSpeed = 6;
    public static int speedForCactus = 6;
    private URL url;
    
    
    
    private class GroundImage {
        BufferedImage image;
        int x;
    }
    
    private BufferedImage grassGround;//immagine suolo
    //private BufferedImage backGround;//immagine suolo
    private ArrayList<GroundImage> grassGroundSet;
    //private ArrayList<GroundImage> backGroundSet;
    
    /*
    private BufferedImage grassGroundColoured;//immagine suolo
    private ArrayList<GroundImage> grassGroundColouredSet;*/
    
    
    public Ground(){
        //GROUND
        //this.yPosition = (int)(UserInterface.height*0.75);
        this.speedForCactus = movementSpeed;
        
        
        //OLD
        url = this.getClass().getClassLoader().getResource("image/color/Ground-colorato.png");
        this.grassGround = new Utility().create(url);
        //this.backGround = new Utility().create("src/image/bn/background.png");
        
        //COLOURED
        //this.grassGround = new Utility().create("src/image/altro/T-Run_ground_grass3.png"); 

        grassGroundSet = new ArrayList<GroundImage>();
        //backGroundSet = new ArrayList<GroundImage>();
        
        for(int i=0; i<3; i++){
            GroundImage tmp = new GroundImage();
            tmp.image = grassGround;
            tmp.x = 0;
            grassGroundSet.add(tmp);
        }
        
    }
    
    public void create(Graphics g) {
        
        /*
        if(distance >= 300){
            //this.grassGround = new Utility().create("src/image/altro/T-Run_ground_grass3.png");
        }*/
        
        for(GroundImage img: grassGroundSet)
            g.drawImage(grassGround, (int) img.x, this.yPosition, null);
        
    }
    
    public void update() {
        Iterator<GroundImage> looper = grassGroundSet.iterator();       //iterator di grassGroundSet
        GroundImage first = looper.next();
        
        movementSpeed = 6 + distance / 250;
        speedForCactus = movementSpeed;
        first.x -= movementSpeed;
        
        /*
        if(distance < 300){
        first.x -= movementSpeed;
        speedForCactus = movementSpeed;
        } else if (distance > 300 && distance < 700){
            first.x -= 2*movementSpeed;
            speedForCactus = 2*movementSpeed;
        } else if (distance > 700 && distance < 1500){
            first.x -= 4*movementSpeed;
            speedForCactus = 4*movementSpeed;
        } else if (distance > 1500 && distance < 3000){
            first.x -= 6*movementSpeed;
            speedForCactus = 6*movementSpeed;
        }*/

        int previousX = first.x;
        while (looper.hasNext()) {
            GroundImage next = looper.next();
            next.x = previousX + grassGround.getWidth();
            previousX = next.x;
        }

        if (first.x < -grassGround.getWidth()) {
            grassGroundSet.remove(first);
            first.x = previousX + grassGround.getWidth();
            grassGroundSet.add(first);
        }
        
    }
 
}
