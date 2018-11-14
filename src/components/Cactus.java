/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.geom.Area;
import javax.swing.JFrame;
import utility.*;


/**
 *
 * @author claud
 */
public class Cactus extends Obstacle{
    
    private Area collider;
    private ImageOutline outline;
    
    public Cactus(int x, int y) {
        super(x,y);
        int random = (int) (Math.random() * 4 + 1);
        String path = "src/image/Cactus-"+random+".png";
        this.setImage(new Utility().create(path));
        System.out.println("height UI: " + y);
        this.setY(y-this.getImage().getHeight());
        
        outline = new ImageOutline(super.getImage());
        collider = new Area();
        //collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
        collider.add(new Area(outline.getOutline(super.getImage())));
    }

    public Area getCollider() {
        return collider;
    }
            
    
    public static void main(String[] args) throws Exception {
        Cactus cactus = new Cactus(0, 0);
        
        JFrame f = new JFrame("Image Outline");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(cactus.outline.getGui());
        f.pack();
        f.setResizable(false);
        f.setLocationByPlatform(true);
        f.setVisible(true);
    }
}
