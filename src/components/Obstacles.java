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
import static general.UserInterface.width;

/**
 *
 * @author claud
 */
public class Obstacles implements Items{

    private final ArrayList<Item> obArray;
    private final int cactusOnScreen = 4;
    private final double yPercentageCactusOnGround = 0.025;
    private final double yPercentageBirdOnGround = 0.1;
    private final int cactusFrequency = 90;
    private final int birdFrequency = 10;
    //private final int canyonFrequency = 10;
    private final Ground ground;

    public Obstacles(Ground ground) {
        obArray = new ArrayList<>();
        this.ground = ground;
        Item ob;
        AffineTransform at;
        int distance;
        for (int i = 0; i < cactusOnScreen; i++) {
            ob = new Cactus(randomDistance(), (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
            at = new AffineTransform();
            at.translate(ob.getX(), ob.getY());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }
    }

    @Override
    public void create(Graphics g) {
        obArray.forEach((ob) -> {
            ob.create(g);
        });

    }

    @Override
    public Item hasCollided(Area TRexArea) {
        for (Item ob : obArray) {
            Area inter = (Area) ob.getCollider().clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                System.out.println("Collisione con " + ob.getClass().getSimpleName());
                return ob;
            }
        }
        
        return null;
    }

    @Override
    public void update() {
        AffineTransform at;
        int distance;
        for (Item ob : obArray){
              ob.setX(ob.getX() - Ground.movementSpeed);
              at = new AffineTransform();
              at.translate(-Ground.movementSpeed, 0);
              ob.getCollider().transform(at);
        }
        Item firstOb = obArray.get(0);
        if ((firstOb.getX() < -firstOb.getImage().getWidth()) && (!obArray.isEmpty())) { //image is completely out of the screen: remove and move it to the end of the array
            obArray.remove(firstOb);
            Item ob = randomObstacle();
            at = new AffineTransform();
            at.translate(ob.getX(), ob.getY());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }

    }

    private int randomDistance() {
        if (obArray.isEmpty()){
            return width;
        }
        int distance;
        do {
            distance = obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 500);
        } while (!ground.isCanyon(distance));
        return distance;
    }
    
    private double randomBirdHeight() {
        return (Math.random() * yPercentageBirdOnGround);
    }
    
    private Item randomObstacle(){
        int totalFrequency = (cactusFrequency + birdFrequency);
        int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
        if (extract <= cactusFrequency){
            return new Cactus(randomDistance(), (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
        } else 
            return new Bird(randomDistance(), (int) (Ground.yPosition) - (int) (Ground.yPosition * randomBirdHeight()));
        
    }

    @Override
    public ArrayList<Item> getObArray() {
        return obArray;
    }
}
