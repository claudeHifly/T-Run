/*
 * The purpose of this class is to build a list of ground images and shift them in order to simulate the character movement.
 */
package components;

import static general.Board.distance;
import general.UserInterface;
import static general.UserInterface.width;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utility.*;

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

        GroundImage(int x) {
            this.x = x;
            this.image = Resources.instance().generateGround();
            this.y = yPosition;
            this.collider = Utility.instance().createCollider(image);
            this.image = Resources.instance().getGround();
        }

        public void create(Graphics g) {
            g.drawImage(image, x, y, null);

            //Uncommenting the lines below, you can see the the ground collider during the gameplay
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setColor(Color.red);
//            g2d.draw(collider);
//            g2d.setColor(Color.BLACK);
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
        movementSpeed = movementSpeed0;
        groundOnScreen = (width * 3 / Resources.instance().getGroundCanyon().getWidth());
        for (int i = 0; i < groundOnScreen; i++) {
            ob = new GroundImage(nextX);
            Utility.instance().moveCollider(ob.collider, ob.x, ob.y);
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
        return false;
    }

    public void update() {
        movementSpeed = movementSpeed0 + distance / 500;
        GroundImage ob1;
        for (GroundImage ob : grassGroundSet) {
            ob.x -= movementSpeed;
            Utility.instance().moveCollider(ob.collider, -movementSpeed, 0);
        }
        GroundImage firstGround = grassGroundSet.get(0);
        nextX = grassGroundSet.get(grassGroundSet.size() - 1).x + grassGroundSet.get(grassGroundSet.size() - 1).image.getWidth();
        if (firstGround.x < -firstGround.image.getWidth()) {
            grassGroundSet.remove(firstGround);
            ob1 = new GroundImage(nextX);
            Utility.instance().moveCollider(ob1.collider, ob1.x, ob1.y);
            grassGroundSet.add(ob1);
            nextX += ob1.image.getWidth();
        }
    }

    public int addCanyon(int x) {
        BufferedImage image = Resources.instance().getGroundCanyon();
        x -= image.getWidth() / 2;
        Area collider = Utility.instance().createCollider(Resources.instance().generateGroundCanyon());
        nextX = grassGroundSet.get(grassGroundSet.size() - 1).x + grassGroundSet.get(grassGroundSet.size() - 1).image.getWidth();
        for (int i = 0; i < grassGroundSet.size(); i++) {
            if (grassGroundSet.get(i).x >= x) {
                grassGroundSet.get(i).image = image;
                Utility.instance().moveCollider(collider, grassGroundSet.get(i).x, grassGroundSet.get(i).y);
                grassGroundSet.get(i).collider = collider;
                return grassGroundSet.get(i).x + grassGroundSet.get(i).image.getWidth() / 2;
            }
        }
        return x;
    }
}
