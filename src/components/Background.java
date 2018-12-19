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
import utility.Resources;

/**
 *
 * @author G8
 */
public class Background {

    private class GroundImage {

        BufferedImage image;
        int x;
    }

    private BufferedImage backGround;//immagine suolo
    private ArrayList<GroundImage> backGroundSet;

    public Background() {

        //OLD
        this.backGround = Resources.instance().getBackground();

        //COLOURED
        //this.grassGround = new Utility().create("src/image/altro/T-Run_ground_grass3.png"); 
        backGroundSet = new ArrayList<GroundImage>();

        for (int i = 0; i < 3; i++) {
            GroundImage tmp = new GroundImage();
            tmp.image = backGround;
            tmp.x = 0;
            backGroundSet.add(tmp);
        }

    }

    public void create(Graphics g) {

        for (GroundImage img : backGroundSet) {
            g.drawImage(backGround, (int) img.x, 0, null);
        }

    }

    public void update() {

        Iterator<GroundImage> looper2 = backGroundSet.iterator();
        GroundImage first2 = looper2.next();

        first2.x -= Ground.movementSpeed / 4;

        int previousX2 = first2.x;
        while (looper2.hasNext()) {
            GroundImage next = looper2.next();
            next.x = previousX2 + backGround.getWidth();
            previousX2 = next.x;
        }

        if (first2.x < -backGround.getWidth()) {
            backGroundSet.remove(first2);
            first2.x = previousX2 + backGround.getWidth();
            backGroundSet.add(first2);
        }

    }

}
