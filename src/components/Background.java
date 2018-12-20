/*
 * The purpose of this class is to build a list of three background images and shift them in order to simulate the character movement.
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

    private final BufferedImage backGround;//immagine suolo
    private final ArrayList<GroundImage> backGroundSet;

    public Background() {
        //OLD
        this.backGround = Resources.instance().getBackground();
        //COLOURED
        backGroundSet = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GroundImage tmp = new GroundImage();
            tmp.image = backGround;
            tmp.x = 0;
            backGroundSet.add(tmp);
        }
    }

    public void create(Graphics g) {
        for (GroundImage img : backGroundSet) {
            g.drawImage(backGround, img.x, 0, null);
        }
    }

    /**
     * This method is used to shift the background images updating their
     * position along x-axis. The images are shifted along the x-axis of a
     * number of pixels that are a quarter of the number of pixels with which
     * the ground shifts. When one of the background images reaches the end of
     * the frame, it will be removed and then added at the end of the list.
     */
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
