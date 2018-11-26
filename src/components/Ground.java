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
import main.UserInterface;
import utility.Utility;
import main.Board;
import static main.Board.distance;

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    public final static int yPosition = (int)(UserInterface.height*0.75);
    public final static int movementSpeed = 6;
    
    
    
    private class GroundImage {
        BufferedImage image;
        int x;
    }
    
    private BufferedImage grassGround;//immagine suolo
    private ArrayList<GroundImage> grassGroundSet;
    
    /*
    private BufferedImage grassGroundColoured;//immagine suolo
    private ArrayList<GroundImage> grassGroundColouredSet;*/
    
    
    public Ground(){
        //GROUND
        //this.yPosition = (int)(UserInterface.height*0.75);
        
        
        //OLD
        this.grassGround = new Utility().create("src/image/old/Ground.png");
        
        //COLOURED
        //this.grassGround = new Utility().create("src/image/coloured/T-Run_ground_grass3.png"); 

        grassGroundSet = new ArrayList<GroundImage>();
        //grassGroundColouredSet = new ArrayList<GroundImage>();
        
        for(int i=0; i<2; i++){
            GroundImage tmp = new GroundImage();
            tmp.image = grassGround;
            tmp.x = 0;
            grassGroundSet.add(tmp);
        }
        
    }
    
    public void create(Graphics g) {
        
        /*
        if(distance >= 300){
            //this.grassGround = new Utility().create("src/image/coloured/T-Run_ground_grass3.png");
        }*/
        
        for(GroundImage img: grassGroundSet)
            g.drawImage(grassGround, (int) img.x, this.yPosition, null);
        
    }
    
    public void update() {
        //System.out.println("I'm in Ground update");
        Iterator<GroundImage> looper = grassGroundSet.iterator();
        GroundImage first = looper.next();

        first.x -= movementSpeed;

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
