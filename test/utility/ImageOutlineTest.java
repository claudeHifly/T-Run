/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class ImageOutlineTest {
    ImageOutline imageOutline;
    
    public ImageOutlineTest() {
        BufferedImage img = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand.png"));
        this.imageOutline = new ImageOutline(img);
    }
    
    /**
     * Test of getOutline method, of class ImageOutline.
     */
    @Test
    public void testGetOutline() {
        System.out.println("getOutline");
        assertNotNull(imageOutline);
    }
    
}
