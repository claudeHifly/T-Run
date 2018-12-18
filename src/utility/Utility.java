/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Angela
 */
public class Utility { //singleton
    
    private static Utility instance = null;

    private Utility(){}
    
    public static Utility instance(){
        if (instance == null)
            instance = new Utility();
        return instance;
    }
    
    public BufferedImage create(URL path){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bi;
    }
    
    public Area createCollider(BufferedImage img){
        ImageOutline outline = new ImageOutline(img);
        return new Area(outline.getOutline());
    }
    
    public Area moveCollider(Area collider, int x, int y){
        AffineTransform at = new AffineTransform();
        at.translate(x-collider.getBounds2D().getX(), y-collider.getBounds2D().getY());
        collider.transform(at);
        return collider;
    }
    
    public Area createCollider(BufferedImage img, int x, int y){
        ImageOutline outline = new ImageOutline(img);
        Area collider =  new Area(outline.getOutline());
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        collider.transform(at);
        return collider;
    }
}
