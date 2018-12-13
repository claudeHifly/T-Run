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
    private final int cactusFrequency = 30;
    private final int birdFrequency = 10;
    private final int canyonFrequency = 60;
    private final Ground ground;
    private int nextX;

    public Obstacles(Ground ground) {
        obArray = new ArrayList<>();
        this.ground = ground;
        Item ob;
        AffineTransform at;
        int distance;
        nextX = 0;
        for (int i = 0; i < cactusOnScreen; i++) {
            nextX += randomDistance();
            ob = new Cactus(nextX, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
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
                //System.out.println("Collisione con " + ob.getClass().getSimpleName());
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
        if(!obArray.isEmpty()) {
            Item firstOb = obArray.get(0);
            if ((firstOb.getX() < -firstOb.getImage().getWidth())) { //image is completely out of the screen: remove and move it to the end of the array
                obArray.remove(firstOb);
                Item ob = randomObstacle();
                if(ob != null){
                    at = new AffineTransform();
                    at.translate(ob.getX(), ob.getY());
                    ob.getCollider().transform(at);
                    obArray.add(ob);
                }

            }
        }
    }

    private int randomDistance() {
        if (obArray.isEmpty()){
            return width;
        }
//        int distance;
//        do {
//            distance = obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 500);
//        } while (!ground.isCanyon(distance));
//        return distance;
        else
            return /*obArray.get(obArray.size() - 1).getX() +*/ (int) (Math.random() * 200 + 500);
    }
    
    private double randomBirdHeight() {
        return (Math.random() * yPercentageBirdOnGround);
    }
    
    private Item randomObstacle(){
        int totalFrequency = (cactusFrequency + birdFrequency + canyonFrequency);
        int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
        nextX += randomDistance();
        if (extract <= cactusFrequency){
            System.out.println("Cactus");
            return new Cactus(nextX, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
        } else if (extract <= cactusFrequency + birdFrequency) {
            System.out.println("Bird");
            return new Bird(nextX, (int) (Ground.yPosition) - (int) (Ground.yPosition * randomBirdHeight()));
        } else {
            System.out.println("Canyon");
            nextX = ground.addCanyon();
            return null;
        }
    }

    @Override
    public ArrayList<Item> getObArray() {
        return obArray;
    }
}
