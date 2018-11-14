/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import main.UserInterface;

/**
 *
 * @author claud
 */
public abstract class Obstacle {
   
    private BufferedImage image;
    private int x;
    private int y;

    public Obstacle(int x, int y) {
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
    }    
    
    
}
