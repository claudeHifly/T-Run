/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static components.Ground.movementSpeed;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import static general.UserInterface.width;

/**
 *
 * @author G8
 */
public class PowerUp extends Items {

    //private ArrayList<Item> obArray;
    private final int powerUpSeriesOnScreen = 10;
    private final int maxPowerUpSeries = 6;
    private final int minPowerUpSeries = 2;
    private final int distancePowerUp = 50;
    private final double yPercentagePowerUpOnGround = 0.15;
    public static int frequencyBoneGold = 15;
    public static int frequencyPepper = 5;
    public static int frequencyHam = 15;
    public static int frequencyMultiplier = 5;
    public int numberSeries;

    public PowerUp() {
        obArray = new ArrayList<>();
        Item ob;
        AffineTransform at;
        for (int i = 0; i < powerUpSeriesOnScreen; i++) {
            int fd = randomDistanceSeries();
            numberSeries = randomPowerUpForSeries();
            for (int j = 0; j < numberSeries; j++) {
                ob = randomPowerUp(fd + j * distancePowerUp);
                at = new AffineTransform();
                at.translate(ob.getX(), ob.getY());
                ob.getCollider().transform(at);
                obArray.add(ob);
            }
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
        AffineTransform at;
        for (Item ob : obArray) {
            //System.out.println("I'm in looper while");
            ob.setX(ob.getX() - movementSpeed);
            //at.translate(ob.getX() - movementSpeed, 0);
            at = new AffineTransform();
            at.translate(-movementSpeed, 0);
            ob.getCollider().transform(at);
        }
        Item firstOb = obArray.get(0);
        if ((firstOb.getX() < -firstOb.getImage().getWidth())) {
            obArray.remove(firstOb);
        }
        if (obArray.size() <= maxPowerUpSeries * (powerUpSeriesOnScreen - 1)) {
            int fd = randomDistanceSeries();
            numberSeries = randomPowerUpForSeries();
            for (int j = 0; j < numberSeries; j++) {
                Item ob = randomPowerUp(fd + j * distancePowerUp);
                at = new AffineTransform();
                at.translate(ob.getX(), ob.getY());
                ob.getCollider().transform(at);
                obArray.add(ob);
            }
        }

    }

    private int randomDistanceSeries() {
        if (obArray.isEmpty()) {
            return width;
        }
        return obArray.get(obArray.size() - 1).getX() + 200 + (int) (Math.random() * 500);

    }

    private int randomPowerUpForSeries() {
        return (int) (Math.random() * (maxPowerUpSeries - minPowerUpSeries) + minPowerUpSeries);

    }

    private Item randomPowerUp(int fd) {
        int totalFrequency = 100;
        int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
        if (extract <= frequencyMultiplier) {
            numberSeries = 1;
            return new Multiplier(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
        } else {
            if (extract <= frequencyMultiplier + frequencyBoneGold) {
                numberSeries = 1;
                return new BoneSpecial(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
            } else {
                if (extract <= frequencyMultiplier + frequencyBoneGold + frequencyPepper) {
                    numberSeries = 1;
                    return new Pepper(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                } else {
                    if (extract <= frequencyMultiplier + frequencyBoneGold + frequencyPepper + frequencyHam) {
                        numberSeries = 1;
                        return new Ham(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                    } else {
                        return new Bone(fd, (int) (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                    }
                }
            }
        }
    }

}