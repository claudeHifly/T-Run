/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static components.Ground.movementSpeed;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import static general.UserInterface.width;

/**
 *
 * @author claud
 */
public class Bones extends Items{

    //private ArrayList<Item> obArray;
    private final int moneyOnScreen = 10;
    public static double probabilityOfGoldBone;
    public static double probabilityOfPepper;
   
    public Bones() {
        super.obArray = new ArrayList<Item>();
        

        this.probabilityOfGoldBone = 0.15;
        this.probabilityOfPepper = 0.65;
        
        Item ob = new Bone(width, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15),"image/color/bone_small.png");
        
        AffineTransform at = new AffineTransform();
        at.translate(width, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
        ob.getCollider().transform(at);
        
        super.obArray.add(ob);
        
        int fd=0;
        int firstSerieNumber=(int)(Math.random()*4+2);
        int secondSerieNumber=(int)(Math.random()*4+2+firstSerieNumber);
        for (int i = 1; i < moneyOnScreen; i++) {
            fd = super.obArray.get(super.obArray.size() - 1).getX()+ 50;
            if (i==firstSerieNumber||i==secondSerieNumber)
                fd = super.obArray.get(super.obArray.size() - 1).getX()+ 200 + (int)(Math.random()*500);
            
            if (Math.random() < probabilityOfPepper) {
                if (Math.random() < 0.2) {
                    ob = new Pepper(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/pepper.png");
                } else if (Math.random() > 0.4){
                    ob = new Ham(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/ham.png");
                } else {
                    ob = new Multiplier(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/mulScore.png");
                }
            } else if (Math.random() < probabilityOfGoldBone) {
                ob = new Bone(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/bone_gold2.png");
            } else {
                ob = new Bone(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/bone_small.png");
            }
            
            at = new AffineTransform();
            at.translate(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
            ob.getCollider().transform(at);
            super.obArray.add(ob);
        }
        //System.out.println(obArray);
    }

    /*
    public void create(Graphics g) {
        for (Item ob : super.obArray) {
            ob.create(g);
        }

    }*/

    /*
    public Item hasCollided(Area TRexArea) {
        for (Item ob : super.obArray) {
            Area inter = (Area) ob.getCollider().clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                System.out.println("Collisione con " + ob.getClass().getSimpleName());
                return ob;
            }
        }
        
        return null;
    }*/

    @Override
    public void update() {
        //System.out.println("I'm in Obstacles update");
        Iterator<Item> looper = super.obArray.iterator();
        Item firstOb = null;
        firstOb = looper.next();//PERICOLO!!! Si rompe quando non ha un next

        firstOb.setX(firstOb.getX() - movementSpeed);
        //at.translate(firstOb.getX() - movementSpeed, 0);
        AffineTransform at = new AffineTransform();
        at.translate(-movementSpeed, 0);
        firstOb.getCollider().transform(at);

        while (looper.hasNext()) {
            //System.out.println("I'm in looper while");
            Item ob = looper.next();
            ob.setX(ob.getX() - movementSpeed);
            //at.translate(ob.getX() - movementSpeed, 0);
            at = new AffineTransform();
            at.translate(-movementSpeed, 0);
            ob.getCollider().transform(at);
        }
        
        if ((firstOb.getX() < -firstOb.getImage().getWidth())&&(super.obArray.size()>2)) { //image is completely out of the screen: remove and move it to the end of the array
            //System.out.println("I'm in if");
            //int rd = randomDistance();

            super.obArray.remove(firstOb);
//            Obstacle ob = new Bone(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
//            at = new AffineTransform();
//            at.translate(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
//            ob.getCollider().transform(at);
//            obArray.add(ob);
//            firstOb.setX(rd);
//            at = new AffineTransform();
//            at.translate(rd + firstOb.getImage().getWidth(), 0);
//            firstOb.getCollider().transform(at);
//            obArray.add(firstOb);
        }
        else{
            if (super.obArray.size()<=2){
                Item ob = new Bone(width+200, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15),"image/color/bone_small.png");
                at = new AffineTransform();
                at.translate(width+200, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
                ob.getCollider().transform(at);
                super.obArray.add(ob);
                int fd=0;
                int firstSerieNumber=(int)(Math.random()*3+2);
                int secondSerieNumber=(int)(Math.random()*3+2+firstSerieNumber);
                for (int i = 1; i < moneyOnScreen; i++) {
                    fd = super.obArray.get(super.obArray.size() - 1).getX()+ 50;
                    if (i==firstSerieNumber||i==secondSerieNumber)
                        fd = super.obArray.get(super.obArray.size() - 1).getX()+ 200 + (int)(Math.random()*500);
                    
                    
                    if (Math.random() < probabilityOfPepper) {
                        if (Math.random() < 0.2) {
                            ob = new Pepper(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/pepper.png");
                        } else if (Math.random() > 0.4) {
                            ob = new Ham(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/ham.png");
                        } else {
                            ob = new Multiplier(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/mulScore.png");
                        }
                    } else if (Math.random() < probabilityOfGoldBone) {
                        ob = new Bone(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/bone_gold2.png");
                    } else {
                        ob = new Bone(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15), "image/color/bone_small.png");
                    }

                    at = new AffineTransform();
                    at.translate(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
                    ob.getCollider().transform(at);
                    super.obArray.add(ob);
                }
            }
        }
    }

    private int randomDistance() {
        return super.obArray.get(super.obArray.size() - 1).getX() + (int) (Math.random() * 200 + 200);
    }

    /*
    public ArrayList<Item> getObArray() {
        return super.obArray;
    }*/
}
