/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Angela
 */
public class Utility {
    
    public void createAndDraw(String path, int x, int y, Graphics g){
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(getClass().getResource(path));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        g.drawImage(bi, x, y - bi.getHeight(), null);
    }
}
