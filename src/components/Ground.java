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
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.net.URL;
import utility.ImageOutline;

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    private class GroundImage{
        private BufferedImage image;
        private int x;
        private int y;
        private Area collider;
        private final int canyonFrequency = 20;
        
        public GroundImage(int x) {
            this.x = x;
            URL url = this.getClass().getClassLoader().getResource(randomCanyon());
            this.image = new Utility().create(url);
            this.y = yPosition;
            ImageOutline outline = new ImageOutline(image);
            this.collider = new Area(outline.getOutline(image));
        }
        
        public void create(Graphics g) {
            g.drawImage(image, x, y, null);
            Graphics2D g2d = (Graphics2D)g;
            //g2d.setColor(Color.red);
            //g2d.draw(collider);
            //g2d.setColor(Color.BLACK);
        }
        private String randomCanyon(){
            int totalFrequency = 100;
            int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
            if (extract <= canyonFrequency){
                return "image/bn/GroundCanyon.png";
            } else{ 
                return "image/bn/Ground.png";    
            }
        }   
    }
    
        
    public final static int yPosition = (int)(UserInterface.height*0.75);
    private int movementSpeed0 = 8;
    public static int movementSpeed;
    private ArrayList<GroundImage> grassGroundSet;
    private final int groundOnScreen = 3;
    private int nextX;
    
    
    public Ground(){
        grassGroundSet = new ArrayList<>();
        GroundImage ob;
        nextX = 0;
        AffineTransform at;
        movementSpeed = movementSpeed0;
        for(int i=0; i<groundOnScreen; i++){
            ob = new GroundImage(nextX);
            at = new AffineTransform();
            at.translate(ob.x, ob.y);
            ob.collider.transform(at);
            grassGroundSet.add(ob);
            nextX += ob.image.getWidth();
        }
        
    }
    
    public void create(Graphics g) {
        grassGroundSet.forEach((ob) -> {
            ob.create(g);
        });
    }
    
    public boolean hasCollided(Area TRexArea) {
        for (GroundImage ob : grassGroundSet) {
            Area inter = (Area) ob.collider.clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                System.out.println("Collisione con " + ob.getClass().getSimpleName());
                return true;
            }
        }
        
        return false;
    }
    
    public void update() {
        AffineTransform at;
        movementSpeed = movementSpeed0 + distance / 500;
        GroundImage ob1;
        for (GroundImage ob: grassGroundSet){
            ob.x -= movementSpeed;
            at = new AffineTransform();
            at.translate(-movementSpeed, 0);
            ob.collider.transform(at);
        }
        GroundImage firstGround = grassGroundSet.get(0);
        nextX = grassGroundSet.get(grassGroundSet.size()-1).x + grassGroundSet.get(grassGroundSet.size()-1).image.getWidth();
        if (firstGround.x < -firstGround.image.getWidth()){
            grassGroundSet.remove(firstGround);
            ob1 = new GroundImage(nextX);
            at = new AffineTransform();
            at.translate(ob1.x, ob1.y);
            ob1.collider.transform(at);
            grassGroundSet.add(ob1);            
            nextX += ob1.image.getWidth();
        }
        
    }
 
}
