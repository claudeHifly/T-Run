/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import static main.UserInterface.width;

/**
 *
 * @author claud
 */
public class Obstacles {

    private ArrayList<Item> obArray;
    private final int cactusOnScreen = 6;
    private final double yPercentageCactusOnGround = 0.025;
    private final double yPercentageBirdOnGround = 0;
    

    public Obstacles() {
        obArray = new ArrayList<Item>();
        Item ob = new Cactus(width, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
        AffineTransform at = new AffineTransform();
        at.translate(width, ob.getY());
        ob.getCollider().transform(at);
        obArray.add(ob);
        for (int i = 1; i < cactusOnScreen; i++) {
            int rd = randomDistance();
            ob = new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
            at = new AffineTransform();
            at.translate(rd, ob.getY());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }
        int rd = randomDistance();
        Item ob1 = new Bird(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * randomBirdHeight()));
        at = new AffineTransform();
        at.translate(rd, ob1.getY());
        ob1.getCollider().transform(at);
        obArray.add(ob1);
    }

    public void create(Graphics g) {
        for (Item ob : obArray) {
            ob.create(g);
        }

    }

    public boolean hasCollided(Area TRexArea) {
        for (Item ob : obArray) {
            Area inter = (Area) ob.getCollider().clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                System.out.println("Collisione con " + ob.getClass().getSimpleName());
                return true;
            }
        }
        
        return false;
    }

    public void update() {
        //System.out.println("I'm in Obstacles update");
        Iterator<Item> looper = obArray.iterator();
        Item firstOb = looper.next();

        firstOb.setX(firstOb.getX() - Ground.speedForCactus);
        //at.translate(firstOb.getX() - movementSpeed, 0);
        AffineTransform at = new AffineTransform();
        at.translate(-Ground.speedForCactus, 0);
        firstOb.getCollider().transform(at);

        while (looper.hasNext()) {
            //System.out.println("I'm in looper while");
            Item ob = looper.next();
            ob.setX(ob.getX() - Ground.speedForCactus);
            //at.translate(ob.getX() - movementSpeed, 0);
            at = new AffineTransform();
            at.translate(-Ground.speedForCactus, 0);
            ob.getCollider().transform(at);
        }

        if (firstOb.getX() < -firstOb.getImage().getWidth()) { //image is completely out of the screen: remove and move it to the end of the array
            //System.out.println("I'm in if");
            int rd = randomDistance();
            String type = firstOb.getClass().getSimpleName();
            if (type.equals("Bird")){
                obArray.remove(firstOb);
                Item ob = new Bird(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * randomBirdHeight()));
                at = new AffineTransform();
                at.translate(rd, ob.getY());
                ob.getCollider().transform(at);
                obArray.add(ob);
            } else {
                obArray.remove(firstOb);
                Item ob = new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
                at = new AffineTransform();
                at.translate(rd, ob.getY());
                ob.getCollider().transform(at);
                obArray.add(ob);
            }
//            firstOb.setX(rd);
//            at = new AffineTransform();
//            at.translate(rd + firstOb.getImage().getWidth(), 0);
//            firstOb.getCollider().transform(at);
//            obArray.add(firstOb);
        }

    }

    private int randomDistance() {
        return obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 500);
    }
    
    private double randomBirdHeight() {
        return (Math.random() * 0.1);
    }

    public ArrayList<Item> getObArray() {
        return obArray;
    }
}
