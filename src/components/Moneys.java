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
public class Moneys {

    private ArrayList<Obstacle> obArray;
    private final int moneyOnScreen = 15;
    AffineTransform at = new AffineTransform();
    

    public Moneys() {
        obArray = new ArrayList<Obstacle>();
        Obstacle ob = new Money(width, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
        at.translate(width, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
        ob.getCollider().transform(at);
        obArray.add(ob);
        int fd=0;
        for (int i = 1; i < moneyOnScreen; i++) {
            fd = obArray.get(obArray.size() - 1).getX()+ 50;
            if (i==5||i==10)
                fd = obArray.get(obArray.size() - 1).getX()+ 200 + (int)(Math.random()*500);
            ob = new Money(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
            at = new AffineTransform();
            at.translate(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }
        //System.out.println(obArray);
    }

    public void create(Graphics g) {
        for (Obstacle ob : obArray) {
            ob.create(g);
        }

    }

    public Obstacle hasCollided(Area TRexArea) {
        for (Obstacle ob : obArray) {
            Area inter = (Area) ob.getCollider().clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                System.out.println("Collisione con " + ob.getClass().getSimpleName());
                return ob;
            }
        }
        
        return null;
    }

    public void update() {
        //System.out.println("I'm in Obstacles update");
        Iterator<Obstacle> looper = obArray.iterator();
        Obstacle firstOb = looper.next();

        firstOb.setX(firstOb.getX() - movementSpeed);
        //at.translate(firstOb.getX() - movementSpeed, 0);
        at = new AffineTransform();
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
        
        if ((firstOb.getX() < -firstOb.getImage().getWidth())&&(obArray.size()>1)) { //image is completely out of the screen: remove and move it to the end of the array
            //System.out.println("I'm in if");
            //int rd = randomDistance();

            obArray.remove(firstOb);
//            Obstacle ob = new Money(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
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
            if (obArray.size()<=1){
                Obstacle ob = new Money(width+200, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
                at = new AffineTransform();
                at.translate(width+200, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
                ob.getCollider().transform(at);
                obArray.add(ob);
                int fd=0;
                for (int i = 1; i < moneyOnScreen; i++) {
                    fd = obArray.get(obArray.size() - 1).getX()+ 50;
                    if (i==5||i==10)
                        fd = obArray.get(obArray.size() - 1).getX()+ 200 + (int)(Math.random()*500);
                    ob = new Money(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15));
                    at = new AffineTransform();
                    at.translate(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * 0.15) - ob.getImage().getHeight());
                    ob.getCollider().transform(at);
                    obArray.add(ob);
                }
            }
        }
    }

    private int randomDistance() {
        return obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 200);
    }

    public ArrayList<Obstacle> getObArray() {
        return obArray;
    }
}
