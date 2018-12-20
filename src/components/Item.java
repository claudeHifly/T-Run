/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import utility.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import trex.Trex;

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
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        g.drawImage(image, x, y, null);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.draw(collider);
        g2d.setColor(Color.BLACK);
    }

    public Area getCollider() {
        return collider;
    }

    public void setCollider(Area collider) {
        this.collider = collider;
    }

}
