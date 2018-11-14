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
        super(x,y);
        int random = (int) (Math.random() * 4 + 1);
        String path = "src/image/Cactus-"+random+".png";
        this.setImage(new Utility().create(path));
        System.out.println("height UI: " + y);
        this.setY(y-this.getImage().getHeight());
        collider = new Area();
        collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
    }

    public Area getCollider() {
        return collider;
    }
            
}
