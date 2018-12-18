/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.UserInterface;
import utility.ImageOutline;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import trex.Trex;
import utility.*;

/**
 *
 * @author claud
 */

public abstract class Item {

    protected Trex TRex;
    private BufferedImage image;
    private int x;
    private int y;
    private Area collider;
    private int birdCounter;
    final int BIRD1 = 6;
    final int BIRD2 = 7;
    
    int animation1;
    int animation2;
    private final BufferedImage bird1;
    private final BufferedImage bird2;
    //private ImageOutline outline;

    public Item(int x, int y, BufferedImage image) {
        this.birdCounter = BIRD1;
        TRex = Trex.instance();
        this.x = x;
        this.image = image;
        this.y = y - image.getHeight();
        ImageOutline outline = new ImageOutline(image);
        this.collider = new Area(outline.getOutline());
        this.bird1 = Resources.instance().getBird1Col();
        this.bird2 = Resources.instance().getBird2Col();
    }
    
    public abstract void collisionAction(Item collidedItem);

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
        
        if (image == Resources.instance().getBird1Col()) {
            //System.out.println("try to animate birds");
            if (birdCounter == BIRD1) {
                if (animation1 < 5) {
                    g.drawImage(bird1, x, y, null);
                    animation1++;
                } else {
                    birdCounter = BIRD2;
                    g.drawImage(bird2, x, y, null);
                    animation1 = 0;
                }
            } else {
                if (animation2 < 5) {
                    g.drawImage(bird2, x, y, null);
                    animation2++;
                } else {
                    birdCounter = BIRD1;
                    g.drawImage(bird1, x, y, null);
                    animation2 = 0;
                }
            }
            //animation bird
        } else {
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            g.drawImage(image, x, y, null);
        }
        Graphics2D g2d = (Graphics2D) g;
        
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
