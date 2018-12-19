/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import static general.UserInterface.width;

/**
 *
 * @author G8
 */
public class Obstacles extends Items {

    private final int cactusOnScreen;
    private final double yPercentageCactusOnGround = 0.025;
    private final double yPercentageBirdOnGround = 0.1;
    private final int birdFrequency = 10;
    private final int canyonFrequency = 20;
    private final Ground ground;

    public Obstacles(Ground ground) {
        obArray = new ArrayList<>();
        this.ground = ground;
        Item ob;
        AffineTransform at;
        cactusOnScreen = (int) (ground.getGroundOnScreen() / 3 * 1.5);
        for (int i = 0; i < cactusOnScreen; i++) {
            ob = new Cactus(randomDistance(), (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
            at = new AffineTransform();
            at.translate(ob.getX(), ob.getY());
            ob.getCollider().transform(at);
            obArray.add(ob);
        }
    }

    @Override
    public void update() {
        AffineTransform at;
        for (Item ob : obArray) {
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
        if (obArray.isEmpty()) {
            return width;
        }
        return obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 300 + 300);
    }

    private double randomBirdHeight() {
        return (Math.random() * yPercentageBirdOnGround);
    }

    private Item randomObstacle() {
        int totalFrequency = 100;
        int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
        int rd = randomDistance();
        if (extract <= canyonFrequency) {
            int endCanyon = ground.addCanyon(rd);
            if (endCanyon == rd) {
                System.out.println("DOVRESTI METTERLO ALLA FINE");
                return new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
            }
            return new Empty(endCanyon);
        } else {
            if (extract <= canyonFrequency + birdFrequency) {
                return new Bird(rd, (int) (Ground.yPosition) - (int) (Ground.yPosition * randomBirdHeight()));
            } else {
                return new Cactus(rd, (int) (Ground.yPosition) + (int) (Ground.yPosition * yPercentageCactusOnGround));
            }
        }
    }

}
