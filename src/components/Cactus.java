/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * @author claud
 */
public class Cactus extends Obstacle{
    
    private Area collider;
    
    public Cactus(BufferedImage image, int x, int y) {
        super(image, x, y);
        collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
        
    }
    


        
}
