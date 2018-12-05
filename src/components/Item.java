/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import main.UserInterface;

/**
 *
 * @author Gennaro
 */
public abstract class Item {
    
    private BufferedImage image;
    private int x;
    private int y;
    private Area collider;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
//        g.drawRect((int) collider.getBounds().getX(), (int) collider.getBounds().getY(),
//                (int) collider.getBounds().getWidth(), (int) collider.getBounds().getHeight());
        Graphics2D g2d = (Graphics2D)g;
        //g2d.setColor(Color.red);
        //g2d.draw(collider);
        //g2d.setColor(Color.BLACK);
    }

    public Area getCollider() {
        return collider;
    }

    public void setCollider(Area collider) {
        this.collider = collider;
    }
    
}