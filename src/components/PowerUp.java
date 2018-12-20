/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static components.Ground.movementSpeed;
import static general.UserInterface.width;
import java.util.ArrayList;
import utility.Utility;

/**
 *
 * @author G8
 */
public class PowerUp extends Items {

    private final int powerUpSeriesOnScreen = 10;
    private final int maxPowerUpSeries = 6;
    private final int minPowerUpSeries = 2;
    private final int distancePowerUp = 50;
    private final double yPercentagePowerUpOnGround = 0.15;
    public static int frequencyBoneGold = 20;
    public static int frequencyPepper = 10;
    public static int frequencyHam = 10;
    public static int frequencyMultiplier = 10;
    public int numberSeries;

    public PowerUp() {
        obArray = new ArrayList<>();
        Item ob;

        for (int i = 0; i < powerUpSeriesOnScreen; i++) {
            int fd = randomDistanceSeries();
            numberSeries = randomPowerUpForSeries();
            for (int j = 0; j < numberSeries; j++) {
                ob = randomPowerUp(fd + j * distancePowerUp);
                Utility.instance().moveCollider(ob.getCollider(), ob.getX(), ob.getY());
                obArray.add(ob);
            }
        }

    }

    @Override
    public void update() {
        for (Item ob : obArray) {
            ob.setX(ob.getX() - movementSpeed);
            Utility.instance().moveCollider(ob.getCollider(), -movementSpeed, 0);
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
                Utility.instance().moveCollider(ob.getCollider(), ob.getX(), ob.getY());
                obArray.add(ob);
            }
        }
    }

    private int randomDistanceSeries() {
        if (obArray.isEmpty()) {
            return width;
        }
        return obArray.get(obArray.size() - 1).getX() + 300 + (int) (Math.random() * 500);
    }

    private int randomPowerUpForSeries() {
        return (int) (Math.random() * (maxPowerUpSeries - minPowerUpSeries) + minPowerUpSeries);
    }

    private Item randomPowerUp(int fd) {
        int totalFrequency = 100;
        int extract = (int) (Math.random() * (totalFrequency - 1) + 1);
        if (extract <= frequencyMultiplier) {
            return new Multiplier(fd, (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
        } else {
            if (extract <= frequencyMultiplier + frequencyBoneGold) {
                return new BoneSpecial(fd, (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
            } else {
                if (extract <= frequencyMultiplier + frequencyBoneGold + frequencyPepper) {
                    return new Pepper(fd, (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                } else {
                    if (extract <= frequencyMultiplier + frequencyBoneGold + frequencyPepper + frequencyHam) {
                        return new Ham(fd, (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                    } else {
                        return new Bone(fd, (Ground.yPosition) - (int) (Ground.yPosition * yPercentagePowerUpOnGround));
                    }
                }
            }
        }
    }
}
