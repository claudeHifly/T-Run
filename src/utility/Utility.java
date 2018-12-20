/*
 * The Utility class provides two functionalities:
 * - creating a BufferedImage, given the path of the image file
 * - creating the collider, given the image and the coordinates
 * This class 
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
 * @author G8
 */
public class Utility { //Singleton

    private static Utility instance = null;

    public static Utility instance() {
        if (instance == null) {
            instance = new Utility();
        }
        return instance;
    }
    private Utility() {
    }

    public BufferedImage create(URL path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return bi;
    }

    public Area createCollider(BufferedImage img, int x, int y) {
        ImageOutline outline = new ImageOutline(img);
        Area collider = new Area(outline.getOutline());
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        collider.transform(at);
        return collider;
    }

    public Area createCollider(BufferedImage img) {
        ImageOutline outline = new ImageOutline(img);
        Area collider = new Area(outline.getOutline());
        return collider;
    }

    public void moveCollider(Area collider, int x, int y) {
        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        collider.transform(at);
    }
}
