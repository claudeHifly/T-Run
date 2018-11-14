/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
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
        super(new Utility().create("src/image/Cactus-1.png"), x, y);
        outline = new ImageOutline(super.getImage());
        collider = new Area();
        //collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
        collider.add(new Area(outline.getOutline(super.getImage())));
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
