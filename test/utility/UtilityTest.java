/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class UtilityTest {
    Utility utility;
    
    public UtilityTest() {
        this.utility = Utility.instance();
    }

    /**
     * Test of instance method, of class Utility.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(utility);
    }

    /**
     * Test of create method, of class Utility.
     */
   @Test
    public void testCreate() {
        System.out.println("create");
        URL path = this.getClass().getClassLoader().getResource("image/color/Dino-stand.png");
        BufferedImage result = utility.create(path);
        assertNotNull(result);
    }

    /**
     * Test of createCollider method, of class Utility.
     */
    @Test
    public void testCreateCollider() {
        System.out.println("createCollider");
        URL path = this.getClass().getClassLoader().getResource("image/color/Dino-stand.png");
        BufferedImage img = utility.create(path);
        Area result = utility.createCollider(img, 0, 0);
        assertNotNull(result);
    }
    
}
