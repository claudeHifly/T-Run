/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JPanel;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class Blinker extends JPanel{
    
    private URL url;
    private BufferedImage imageBlinker;
    
    private void Blinker(){
        url = this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes-colorato.jpg");
        this.imageBlinker = new Utility().create(url);
    }

    public void create(Graphics g) {
        g.drawImage(imageBlinker, 0, 0, null);
    }
   


}
