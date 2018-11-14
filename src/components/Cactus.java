/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.geom.Area;
import java.awt.Rectangle;
import utility.*;


/**
 *
 * @author claud
 */
public class Cactus extends Obstacle{
    
    private Area collider;
    
    public Cactus(int x, int y) {
        super(new Utility().create("src/image/Cactus-1.png"), x, y);
        collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
    }
    


        
}
