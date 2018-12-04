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
    private BufferedImage dust00, dust01, dust02, dust03;
    private float deltaT;

    public final static int groundLevel = (int) (UserInterface.height * 0.75);
    private final static int maxHeight = (int) (UserInterface.height - UserInterface.height * 0.52);
    private static int jumpFactor = (int) (movementSpeed * 1.3);
    private static int TRexOnGround;
    public final static int x = 50;
    private int y;
    private int TRexPositionY;
    private float gravity;
    private float speedForJumping;
    private static boolean topReached;
    private static int wTRex;
    private static int hTRex;
    private boolean jumpDisabled;

    private static int wTRexLower;
    private static int hTRexLower;

    private Area collider;
    private int foot;

    private int topTRex;
    private int bottomTRex;

    private final int LEFT_FOOT = 1,
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
        deltaT = (float) 1.25;
        gravity = (float) 0.981;
        speedForJumping = (float) (movementSpeed * 2.2);

        image = new Utility().create("src/image/old/Dino-stand-colorato.png");
        leftFootDino = new Utility().create("src/image/old/Dino-left-up-colorato.png");
        rightFootDino = new Utility().create("src/image/old/Dino-right-up-colorato.png");
        lowerHeadDinoLeft = new Utility().create("src/image/old/Dino-below-left-up-colorato.png");
        lowerHeadDinoRight = new Utility().create("src/image/old/Dino-below-right-up-colorato.png");
        dust00 = new Utility().create("src/image/old/dust00.png");
        dust01 = new Utility().create("src/image/old/dust01.png");
        dust02 = new Utility().create("src/image/old/dust02.png");
        dust03 = new Utility().create("src/image/old/dust03.png");

        state = RUNNING;
        topReached = false;

        wTRex = image.getWidth(null);
        hTRex = image.getHeight(null);
        wTRexLower = lowerHeadDinoLeft.getWidth(null);
        hTRexLower = lowerHeadDinoLeft.getHeight(null);
        topTRex = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025);
        bottomTRex = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;

        TRexPositionY = bottomTRex;

        TRexOnGround = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;

        y = TRexOnGround;
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

        if ((keyPressed == KeyEvent.VK_SPACE || keyPressed == KeyEvent.VK_UP) && state != (LOWER_HEAD) && !(jumpDisabled)) {
            state = JUMPING;
            jumpDisabled = true;
            //System.out.println("Space pressed");
        }

        if (keyPressed == KeyEvent.VK_DOWN && state != (JUMPING)) {
            state = LOWER_HEAD;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyTyped = e.getKeyCode();

        if (keyTyped == KeyEvent.VK_DOWN) {
            state = RUNNING;
        }

        if ((keyTyped == KeyEvent.VK_SPACE || keyTyped == KeyEvent.VK_UP)) {
            jumpDisabled = false;
            //System.out.println("Space pressed");
        }

    }

    //create viene invocato
    public void create(Graphics g) {
        ////////////////////g.drawImage(image, X, y, null); 
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(collider);
//        g2d.setColor(Color.BLACK);
        switch (state) {

            case RUNNING:

//                if (y <= groundLevel - hTRex) {
//                    y = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;
//                }
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

                if (speedForJumping > 0 /*TRexPositionY > maxHeight*/ && topReached == false) {

                    //jumping sprite
                    y -= deltaT * speedForJumping;
                    g.drawImage(image, x, y, null);
                    at.translate(0, -deltaT * speedForJumping);
                    collider.transform(at);
                    speedForJumping -= (deltaT * gravity);

                    //System.out.println("bottomTRex height: " + bottomTRex);
                    //break;
                } else if (speedForJumping <= 0 && topReached == false) {
                    topReached = true;
                    g.drawImage(image, x, y, null);

                    //System.out.println("SALTOOOOOOOOOOOOOOO " + bottomTRex);
                } else if (topReached == true) {

                    if (speedForJumping < 0) {
                        speedForJumping = 0;
                    }

                    //System.out.println("TRex position y iniziale = " + TRexPositionY);
                    //System.out.println("detlaT iniziale = " + deltaT);
                    //System.out.println("speedForJumping iniziale = " + speedForJumping);
                    y += deltaT * speedForJumping;
                    g.drawImage(image, x, y, null);
                    at.translate(0, deltaT * speedForJumping);
                    collider.transform(at);
                    speedForJumping += (deltaT * gravity);
                    //System.out.println("TRex position y finale = " + TRexPositionY);
                    //System.out.println("detlaT finale = " + deltaT);
                    //System.out.println("speedForJumping finale = " + speedForJumping);

                }

                if (y >= TRexOnGround - 20 && topReached == true) {
                    //System.out.println("ground " + bottomTRex);
                    y = TRexOnGround;
                    g.drawImage(image, x, y, null);
                    at = new AffineTransform();
                    at.translate(0, -collider.getBounds().getY() + y);
                    collider.transform(at);

                    topReached = false;
                    speedForJumping = (float) (movementSpeed * 2.2);
                    state = RUNNING;
                    break;
                }
                break;

            case LOWER_HEAD:
                int lowerCount = 0;

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
