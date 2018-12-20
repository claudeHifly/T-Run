/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import general.UserInterface;
import utility.*;
import static general.Board.distance;
import static general.UserInterface.width;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import utility.ImageOutline;

/**
 *
 * @author G8
 */
public class Ground {

    private class GroundImage {

        private BufferedImage image;
        private int x;
        private int y;
        private Area collider;

        public GroundImage(int x) {
            this.x = x;
            this.image = Resources.instance().generateGround();
            this.y = yPosition;
            ImageOutline outline = new ImageOutline(image);
            this.collider = new Area(outline.getOutline());
            this.image = Resources.instance().getGround();
        }

        public void create(Graphics g) {
            g.drawImage(image, x, y, null);
            Graphics2D g2d = (Graphics2D) g;
            //g2d.setColor(Color.red);
            //g2d.draw(collider);
            //g2d.setColor(Color.BLACK);
        }

    }

    public final static int yPosition = (int) (UserInterface.height * 0.75);
    public static int movementSpeed0 = 8;
    public static int movementSpeed;
    private final ArrayList<GroundImage> grassGroundSet;
    private final int groundOnScreen;
    private int nextX;

    public Ground() {
        grassGroundSet = new ArrayList<>();
        GroundImage ob;
        nextX = 0;
        AffineTransform at;
        movementSpeed = movementSpeed0;
        groundOnScreen = (int) (width * 3 / Resources.instance().getGroundCanyon().getWidth());
        for (int i = 0; i < groundOnScreen; i++) {
            ob = new GroundImage(nextX);
            at = new AffineTransform();
            at.translate(ob.x, ob.y);
            ob.collider.transform(at);
            grassGroundSet.add(ob);
            nextX += ob.image.getWidth();
        }

    }

    public int getGroundOnScreen() {
        return groundOnScreen;
    }

    public void create(Graphics g) {
        grassGroundSet.forEach((ob) -> {
            ob.create(g);
        });
    }

    public boolean hasCollided(Area area) {

        for (GroundImage ob : grassGroundSet) {
            Area inter = (Area) ob.collider.clone();
            inter.intersect(area);
            if (!inter.isEmpty()) {

                return true;
            }
        }

        //System.out.println("ho preso il canyon");
        return false;
    }

    public void update() {
        AffineTransform at;
        movementSpeed = movementSpeed0 + distance / 500;
        GroundImage ob1;
        for (GroundImage ob : grassGroundSet) {
            ob.x -= movementSpeed;
            at = new AffineTransform();
            at.translate(-movementSpeed, 0);
            ob.collider.transform(at);
        }
        GroundImage firstGround = grassGroundSet.get(0);
        nextX = grassGroundSet.get(grassGroundSet.size() - 1).x + grassGroundSet.get(grassGroundSet.size() - 1).image.getWidth();
        if (firstGround.x < -firstGround.image.getWidth()) {
            grassGroundSet.remove(firstGround);
            ob1 = new GroundImage(nextX);
            at = new AffineTransform();
            at.translate(ob1.x, ob1.y);
            ob1.collider.transform(at);
            grassGroundSet.add(ob1);
            nextX += ob1.image.getWidth();
        }

    }

    public int addCanyon(int x) {
        AffineTransform at;
        BufferedImage image = Resources.instance().getGroundCanyon();
        x -= image.getWidth() / 2;
        Area collider = new Area((new ImageOutline(Resources.instance().generateGroundCanyon())).getOutline());
        nextX = grassGroundSet.get(grassGroundSet.size() - 1).x + grassGroundSet.get(grassGroundSet.size() - 1).image.getWidth();
        for (int i = 0; i < grassGroundSet.size(); i++) {
            if (grassGroundSet.get(i).x >= x) {
                grassGroundSet.get(i).image = image;
                at = new AffineTransform();
                at.translate(grassGroundSet.get(i).x, grassGroundSet.get(i).y);
                collider.transform(at);
                grassGroundSet.get(i).collider = collider;
                return grassGroundSet.get(i).x + grassGroundSet.get(i).image.getWidth() / 2;
            }
        }
        return x;
    }
}
