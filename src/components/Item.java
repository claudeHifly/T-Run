/*
 * This abstract class is a superclass that implements, according to the Strategy design pattern, all the items of the game.
 * Each subclass have to implement the abstract 'collisionAction' method.
 */
package components;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import trex.Trex;
import utility.*;

/**
 *
 * @author G8
 */
public abstract class Item {

    protected Trex TRex;
    private BufferedImage image;
    private int x;
    private int y;
    private Area collider;

    public Item(int x, int y, BufferedImage image) {
        TRex = Trex.instance();
        this.x = x;
        this.image = image;
        this.y = y - image.getHeight();
        this.collider = Utility.instance().createCollider(image);

    }

    public abstract void collisionAction();

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void create(Graphics g) {
        g.drawImage(image, x, y, null);

        //Uncommenting the rows below, you can see the the items collider during the gameplay
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(collider);
//        g2d.setColor(Color.BLACK);
    }

    public Area getCollider() {
        return collider;
    }

    public void setCollider(Area collider) {
        this.collider = collider;
    }

}
