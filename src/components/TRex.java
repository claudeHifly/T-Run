/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static components.Ground.movementSpeed;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import main.Board;
import main.UserInterface;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class TRex extends KeyAdapter {

    private BufferedImage image;//immagine TRex stand
    private BufferedImage leftFootDino;//immagine TRex leftFoot
    private BufferedImage rightFootDino;//immagine TRex rightFoot
    private BufferedImage lowerHeadDinoLeft;
    private BufferedImage lowerHeadDinoRight;

    public final static int groundLevel = (int) (UserInterface.height * 0.75);
    private final static int maxHeight = (int) (UserInterface.height - UserInterface.height * 0.52);
    private static int jumpFactor = (int) (movementSpeed * 1.3);
    public final static int x = 50;
    private int y;
    private static boolean topReached;
    private static int wTRex;
    private static int hTRex;
    
    private static int wTRexLower;
    private static int hTRexLower;
    
    private Area collider;
    private int foot;

    private int topTRex;
    private int bottomTRex;

    private final int   LEFT_FOOT = 1,
                        RIGHT_FOOT = 2,
                        NO_FOOT = 3,
                        LEFT_FOOT_LOWER = 4,
                        RIGHT_FOOT_RIGHT = 5;
                                

    //stato T-Rex
    private static int state;
    public static final int STAND_STILL = 1,
                            RUNNING = 2,
                            JUMPING = 3,
                            DIE = 4,
                            LOWER_HEAD = 5;

    private ImageOutline outline;

    public TRex() {
        AffineTransform at = new AffineTransform();
        image = new Utility().create("src/image/old/Dino-stand.png");
        leftFootDino = new Utility().create("src/image/old/Dino-left-up.png");
        rightFootDino = new Utility().create("src/image/old/Dino-right-up.png");
        lowerHeadDinoLeft = new Utility().create("src/image/old/Dino-below-left-up.png");
        lowerHeadDinoRight = new Utility().create("src/image/old/Dino-below-right-up.png");

        state = RUNNING;
        topReached = false;

        wTRex = image.getWidth(null);
        hTRex = image.getHeight(null);
        wTRexLower = lowerHeadDinoLeft.getWidth(null);
        hTRexLower = lowerHeadDinoLeft.getHeight(null);
        topTRex = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025);
        bottomTRex = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;

        y = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;
        System.out.println("TRex width: " + wTRex);
        System.out.println("TRex height: " + hTRex);
        System.out.println("Ground height: " + y);
        System.out.println("topTRex height: " + topTRex);
        System.out.println("bottomTRex height: " + bottomTRex);
        System.out.println("maxHeight: " + maxHeight);
        foot = NO_FOOT;//inizializzo
        //collider = new Area(new Rectangle(X, y, image.getWidth(), image.getHeight()));
        outline = new ImageOutline(leftFootDino);
        collider = new Area(outline.getOutline(leftFootDino));
        at.translate(x, y);
        collider.transform(at);
    }

    @Override
    public void keyPressed(KeyEvent e) {
                
        int keyPressed = e.getKeyCode();
     
        if ( (keyPressed == KeyEvent.VK_SPACE || keyPressed == KeyEvent.VK_UP) && state != (LOWER_HEAD) ) {
            state = JUMPING;
            //System.out.println("Space pressed");
        }
             
        if (keyPressed == KeyEvent.VK_DOWN && state!=(JUMPING)) {
            state = LOWER_HEAD;
        }

    }
    
    @Override
    public void keyTyped(KeyEvent e){
    }
    
    @Override
    public void keyReleased(KeyEvent e){
           
        int keyTyped = e.getKeyCode();
             
        if (keyTyped == KeyEvent.VK_DOWN) {
            state = RUNNING;
        }
                
    }
    
  

    //create viene invocato
    public void create(Graphics g) {
        //g.drawImage(image, X, y, null); 
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.red);
        //g2d.draw(collider);
        //g2d.setColor(Color.BLACK);
        switch (state) {

            case RUNNING:

                if (y <= groundLevel - hTRex) {
                    y += 2;
                }

                if (foot == NO_FOOT) {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootDino, x, y, null);
                } else if (foot == LEFT_FOOT) {
                    foot = RIGHT_FOOT;
                    g.drawImage(rightFootDino, x, y, null);
                } else {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootDino, x, y, null);
                }
                break;

            case JUMPING:
                AffineTransform at = new AffineTransform();

                if (bottomTRex > maxHeight && topReached == false) {
                    at.translate(0, -jumpFactor);
                    collider.transform(at);
                    g.drawImage(image, x, bottomTRex -= jumpFactor, null);
                    
                    //System.out.println("bottomTRex height: " + bottomTRex);
                    break;
                } else if (bottomTRex <= maxHeight && topReached == false) {
                    topReached = true;
                    g.drawImage(image, x, bottomTRex, null);

                    //System.out.println("SALTOOOOOOOOOOOOOOO " + bottomTRex);
                    break;
                } else if (bottomTRex < y && topReached == true) {
                    at.translate(0, jumpFactor);
                    collider.transform(at);
                    g.drawImage(image, x, bottomTRex += jumpFactor, null);
                    
                    break;
                } else if (topTRex >= y && topReached == true) {
                    g.drawImage(image, x, bottomTRex, null);
                    topReached = false;
                    state = RUNNING;
                    break;
                }
                break;
                
            case LOWER_HEAD:
                
                if (foot == NO_FOOT) {
                    foot = LEFT_FOOT_LOWER;
                    g.drawImage(lowerHeadDinoLeft, x, y, null);
                } else if (foot == LEFT_FOOT_LOWER) {
                    foot = RIGHT_FOOT_RIGHT;
                    g.drawImage(lowerHeadDinoRight, x, y, null);
                } else {
                    foot = LEFT_FOOT_LOWER;
                    g.drawImage(lowerHeadDinoLeft, x, y, null);
                }
                break;
                

        }
    }

    public int getwTRex() {
        return wTRex;
    }

    public void setwTRex(int wTRex) {
        this.wTRex = wTRex;
    }

    public int gethTRex() {
        return hTRex;
    }

    public void sethTRex(int hTRex) {
        this.hTRex = hTRex;
    }

    public Area getCollider() {
        return collider;
    }
}
