/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Gennaro
 */

public class MovingBackground extends Canvas implements Runnable{
    
    private Ground backOne;
    private Ground backTwo;
    
    private BufferedImage back;
 
    public MovingBackground() {
        backOne = new Ground();
        backTwo = new Ground(backOne.getImageWidth(), 0);
 
        new Thread(this).start();
        setVisible(true);
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch (Exception e) {}
    }
 
    @Override
    public void update(Graphics window) {
        paint(window);
    }
 
    public void paint(Graphics window) {
        Graphics2D twoD = (Graphics2D)window;
 
        if (back == null)
            back = (BufferedImage)(createImage(getWidth(), getHeight()));
 
        // Create a buffer to draw to
        Graphics buffer = back.createGraphics();
 
        // Put the two copies of the background image onto the buffer
        backOne.create(buffer);
        backTwo.create(buffer);
 
        // Draw the image onto the window
        twoD.drawImage(back, null, 0, 0);
 
    }
    
}
