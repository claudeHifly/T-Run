/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import utility.ImageOutline;
import static components.Ground.movementSpeed;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import main.Board;
import main.UserInterface;
import utility.Utility;

/**
 *
 * @author Gennaro
 */
public class TRex extends KeyAdapter {

    private BufferedImage image;//immagine TRex stand
    private BufferedImage imageColorato;//immagine TRex stand colorato
    private BufferedImage deadTRex;//immagine TRex morto
    private BufferedImage leftFootDino;//immagine TRex leftFoot
    private BufferedImage rightFootDino;//immagine TRex rightFoot
    private BufferedImage lowerHeadDinoLeft;
    private BufferedImage lowerHeadDinoRight;
    private BufferedImage gameOverImage;
    private float deltaT;

    public final static int groundLevel = (int) (UserInterface.height * 0.75);
    private final static int maxHeight = (int) (UserInterface.height - UserInterface.height * 0.50);
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
    
    //questi due contatori mi servono per rallentare l'animazione dei piedi
    //del TRex altrimenti cambierebbe sprite ogni 25ms
    private int leftCounter;        //contatore per l'animazione del piede sinistro
    private int rightCounter;       //contatore per l'animazione del piede destro
    
    private int blinkCounter;       //contatore per il numero di blink;

    private Area collider;
    private int foot;

    private int topTRex;
    private int bottomTRex;
    
    private AffineTransform at;

    private final int LEFT_FOOT = 1,
            RIGHT_FOOT = 2,
            NO_FOOT = 3,
            LEFT_FOOT_LOWER = 4,
            RIGHT_FOOT_LOWER = 5;


    //stato T-Rex
    public static int state;
    public static final int STAND_STILL = 1,
                            RUNNING = 2,
                            JUMPING = 3,
                            DIE = 4,
                            LOWER_HEAD = 5,
                            DEAD = 6,
                            BLINK = 7;

    private ImageOutline outline;

    public TRex() {
        at = new AffineTransform();
        deltaT = (float) 1.25;
        gravity = (float) 0.981;
        speedForJumping = (float) (6 * 2.2);//ho lasciato 6 perchè dobbiamo trovare una soluzione per il salto 
                                            //in base alla velocità del personaggio.
        
        image = new Utility().create("src/image/color/Dino-stand-colorato.png");
        imageColorato = new Utility().create("src/image/color/Dino-stand-colorato.png");
        deadTRex = new Utility().create("src/image/color/Dino-big-eyes-colorato.png");
        leftFootDino = new Utility().create("src/image/color/Dino-left-up-colorato.png");
        rightFootDino = new Utility().create("src/image/color/Dino-right-up-colorato.png");
        lowerHeadDinoLeft = new Utility().create("src/image/color/Dino-below-left-up-colorato.png");
        lowerHeadDinoRight = new Utility().create("src/image/color/Dino-below-right-up-colorato.png");
        gameOverImage = new Utility().create("src/image/altro/GameOver.png");

        state = RUNNING;
        topReached = false;

        wTRex = image.getWidth(null);
        hTRex = image.getHeight(null);
        wTRexLower = lowerHeadDinoLeft.getWidth(null);
        hTRexLower = lowerHeadDinoLeft.getHeight(null);

        TRexOnGround = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;

        y = TRexOnGround;
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
        }

    }

  

    //create viene invocato
    public void create(Graphics g) {
        
         
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(collider);
//        g2d.setColor(Color.BLACK);
        switch (state) {

            case RUNNING:
                Board.blinking = false;

                if (foot == NO_FOOT) {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootDino, x, y, null);
                    collider = new Area(outline.getOutline(leftFootDino));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                } else if (foot == LEFT_FOOT) {
                    
                    if (leftCounter < 5){
                        g.drawImage(leftFootDino, x, y, null);
                        collider = new Area(outline.getOutline(leftFootDino));
                        at = new AffineTransform();
                        at.translate(x, y);
                        collider.transform(at);
                        leftCounter++;
                    } else {
                    foot = RIGHT_FOOT;
                    g.drawImage(rightFootDino, x, y, null);
                    collider = new Area(outline.getOutline(rightFootDino));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                    leftCounter = 0;    //resetto il contatore e cambio stato
                    }
                } else {
                    if (rightCounter < 5){
                        g.drawImage(rightFootDino, x, y, null);
                        collider = new Area(outline.getOutline(rightFootDino));
                        at = new AffineTransform();
                        at.translate(x, y);
                        collider.transform(at);
                        rightCounter++;
                    } else {
                    foot = LEFT_FOOT;
                    g.drawImage(leftFootDino, x, y, null);
                    collider = new Area(outline.getOutline(leftFootDino));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                    rightCounter = 0;   //resetto il contatore e cambio stato
                    }
                }
                break;
               
            case JUMPING:
                
                AffineTransform at = new AffineTransform();

                if ( ((y > maxHeight) || (speedForJumping <= 0)) && topReached == false) {

                    y -= deltaT * speedForJumping;
                    g.drawImage(imageColorato, x, y, null);
                    collider = new Area(outline.getOutline(rightFootDino));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                    speedForJumping -= (deltaT * gravity);

                    //System.out.println("bottomTRex height: " + bottomTRex);
                    //break;
                }
                
                if ((y <= maxHeight || speedForJumping <= 0) && topReached == false) {
                    
                    topReached = true;
                    g.drawImage(imageColorato, x, y, null);
                    
                    
                }
                
                if (topReached == true) {
                    
                    //Potrebbe verificarsi il caso in cui, a seguito dei numerosi decrementi effettuati sulla velocità 
                    //del TRex in salita, quest'ultima diventi negativa.
                    if (speedForJumping < 0) {
                        speedForJumping = 0;
                    }

                    y += deltaT * speedForJumping;
                    g.drawImage(imageColorato, x, y, null);
                    collider = new Area(outline.getOutline(imageColorato));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                    speedForJumping += (deltaT * gravity);
                   
                }
                
                if (y >= TRexOnGround - 20 && topReached == true) {
                    
                    g.drawImage(imageColorato, x, y, null); //deve sempre essere fatto prima g.drawImage
                                                    //altrimenti abbiamo dei frame in cui scatta
                    y = TRexOnGround;
                    collider = new Area(outline.getOutline(imageColorato));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);

                    topReached = false;
                    speedForJumping = (float) (6 * 2.2);
                    state = RUNNING;
                }
                break;
                

            case LOWER_HEAD:

                if (foot == NO_FOOT) {
                    foot = LEFT_FOOT_LOWER;
                    g.drawImage(lowerHeadDinoLeft, x, y, null);
                    collider = new Area(outline.getOutline(lowerHeadDinoLeft));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                } else if (foot == LEFT_FOOT_LOWER) {
                    
                    if (leftCounter < 5){
                        g.drawImage(lowerHeadDinoLeft, x, y, null);
                        collider = new Area(outline.getOutline(lowerHeadDinoLeft));
                        at = new AffineTransform();
                        at.translate(x, y);
                        collider.transform(at);
                        leftCounter++;
                    } else {
                    foot = RIGHT_FOOT_LOWER;
                    g.drawImage(lowerHeadDinoRight, x, y, null);
                    collider = new Area(outline.getOutline(lowerHeadDinoRight));
                    at = new AffineTransform();
                    at.translate(x, y);
                    collider.transform(at);
                    leftCounter = 0;    //resetto il contatore e cambio stato
                    }
                    
                } else {
                    
                    if (rightCounter < 5){
                        g.drawImage(lowerHeadDinoRight, x, y, null);
                        collider = new Area(outline.getOutline(lowerHeadDinoRight));
                        at = new AffineTransform();
                        at.translate(x, y);
                        collider.transform(at);
                        rightCounter++;
                    } else {
                        foot = LEFT_FOOT_LOWER;
                        g.drawImage(lowerHeadDinoLeft, x, y, null);
                        collider = new Area(outline.getOutline(lowerHeadDinoLeft));
                        at = new AffineTransform();
                        at.translate(x, y);
                        collider.transform(at);
                        rightCounter = 0;   //resetto il contatore e cambio stato
                    }
                }
                break;

            case DEAD:
                g.drawImage(deadTRex, x, y, null);
                //g.drawImage(gameOverImage, 0, 0, null);
                g.setFont(new Font("Courier New", Font.BOLD, 25));
                g.drawString("GAME OVER", UserInterface.width / 2 - 70, UserInterface.height / 2);
                g.drawString("Press ENTER to restart", UserInterface.width / 2 - 150, UserInterface.height / 2 + 35);
                break;
            
            /*
            case BLINK:
                System.out.println("blinkCounter: " + blinkCounter);
                state = RUNNING;
                Board.blinking = false;
                
                
                /*if(blinkCounter == 0 || blinkCounter == 2 || blinkCounter == 4 || blinkCounter == 8){
                    g.drawImage(imageColorato, x, y, null);
                    blinkCounter++;
                } else {
                    g.drawImage(image, x, y, null);
                    blinkCounter++;
                }
                //System.out.println("NEXTblinkCounter");
                if(blinkCounter >= 10){
                    System.out.println("FINE");
                    Board.blinking = false;
                    state = RUNNING;
                    blinkCounter = 0;
                }
                break;*/
                
            
       }
    }
    
    /*
    public void updateTRexSprite(Graphics g){
        
        System.out.println("updateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        /*Board.running = false;
        for (int i = 0; i < 10; i++) {
            
            g.drawImage(image, x, y, null);
            g.drawImage(image, x, y, null);
            g.drawImage(image, x, y, null);
            g.drawImage(imageColorato, x, y, null);
            g.drawImage(imageColorato, x, y, null);
            g.drawImage(imageColorato, x, y, null);
        }
        g.dispose();
        g.drawImage(gameOverImage, x, y, null);
        
        //Board.running = true;
        
    }*/

    public void die(){
        state = DEAD;
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
