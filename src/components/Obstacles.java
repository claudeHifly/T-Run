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
import main.UserInterface;
import static main.UserInterface.width;

/**
 *
 * @author claud
 */
public class Obstacles {

    private ArrayList<Obstacle> obArray;
    private final int cactusOnScreen = 6;
    
    

    public Obstacles() {
        obArray = new ArrayList<Obstacle>();
        Obstacle ob = new Cactus(width, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025));
        AffineTransform at = new AffineTransform();
        at.translate(width, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - ob.getImage().getHeight());
        ob.getCollider().transform(at);
        obArray.add(ob);
        for (int i = 1; i < cactusOnScreen; i++) {
            int rd = randomDistance();
            ob = new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025));
            at = new AffineTransform();
            at.translate(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - ob.getImage().getHeight());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }
        int rd = randomDistance();
        Obstacle ob1 = new Bird(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025));
        at = new AffineTransform();
        at.translate(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - ob1.getImage().getHeight());
        ob1.getCollider().transform(at);
        obArray.add(ob1);
        //System.out.println(obArray);
    }

    public void create(Graphics g) {
        for (Obstacle ob : obArray) {
            ob.create(g);
        }

    }

    public boolean hasCollided(Area TRexArea) {
        for (Obstacle ob : obArray) {
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
        Iterator<Obstacle> looper = obArray.iterator();
        Obstacle firstOb = looper.next();

        firstOb.setX(firstOb.getX() - movementSpeed);
        //at.translate(firstOb.getX() - movementSpeed, 0);
        AffineTransform at = new AffineTransform();
        at.translate(-movementSpeed, 0);
        firstOb.getCollider().transform(at);

        while (looper.hasNext()) {
            //System.out.println("I'm in looper while");
            Obstacle ob = looper.next();
            ob.setX(ob.getX() - movementSpeed);
            //at.translate(ob.getX() - movementSpeed, 0);
            at = new AffineTransform();
            at.translate(-movementSpeed, 0);
            ob.getCollider().transform(at);
        }

        if (firstOb.getX() < -firstOb.getImage().getWidth()) { //image is completely out of the screen: remove and move it to the end of the array
            //System.out.println("I'm in if");
            int rd = randomDistance();

            obArray.remove(firstOb);
            Obstacle ob = new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025));
            at = new AffineTransform();
            at.translate(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - ob.getImage().getHeight());
            ob.getCollider().transform(at);
            obArray.add(ob);
//            firstOb.setX(rd);
//            at = new AffineTransform();
//            at.translate(rd + firstOb.getImage().getWidth(), 0);
//            firstOb.getCollider().transform(at);
//            obArray.add(firstOb);
        }

    }

    private int randomDistance() {
        return obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 200);
    }

    public ArrayList<Obstacle> getObArray() {
        return obArray;
    }
}
