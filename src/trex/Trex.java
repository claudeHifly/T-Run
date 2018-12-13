/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import components.Ground;
import static components.Ground.movementSpeed;
import general.UserInterface;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import utility.Utility;
import resources.Resources;

/**
 *
 * @author Angela
 */
public class Trex extends KeyAdapter implements TrexState{
    
    private TrexState running;
    private TrexState jumping;
    private TrexState dead;
    private TrexState lowerHead;
    //private TrexState pause;
    //private TrexState blink;
    
    private TrexState state;
    
    //private Resources resources;
    
    //private BufferedImage image;//immagine TRex stand colorato
    //BufferedImage deadTRex;//immagine TRex morto
    //BufferedImage leftFootDino;//immagine TRex leftFoot
    //BufferedImage rightFootDino;//immagine TRex rightFoot
    //BufferedImage lowerHeadDinoLeft;
    //BufferedImage lowerHeadDinoRight;
    //private BufferedImage gameOverImage;
    float deltaT;
    
    public final static int groundLevel = (int) (UserInterface.height * 0.75);
    static final int maxHeight = (int) (UserInterface.height - UserInterface.height * 0.50);
    private static int jumpFactor = (int) (movementSpeed * 1.3);
    static int TRexOnGround;
    public final static int x = 50;
    
    private float jumpStrenght, weight;
    int y;
    private int TRexPositionY;
    float gravity;
    float speedForJumping;
    static boolean topReached;
    private static int wTRex;
    private static int hTRex;
    private boolean jumpDisabled;
    private static int wTRexLower;
    private static int hTRexLower;
    
    //questi due contatori mi servono per rallentare l'animazione dei piedi
    //del TRex altrimenti cambierebbe sprite ogni 25ms
    int leftCounter;        //contatore per l'animazione del piede sinistro
    int rightCounter;       //contatore per l'animazione del piede destro
    
    private int blinkCounter;       //contatore per il numero di blink;

    Area collider;
    int foot;

    private int topTRex;
    private int bottomTRex;
    
    //AffineTransform at;
    final int LEFT_FOOT = 1;
    final int RIGHT_FOOT = 2;
    final int NO_FOOT = 3;
    
    final int LEFT_FOOT_LOWER = 4;
    final int RIGHT_FOOT_LOWER = 5;
    
    //ImageOutline outline;

    public Trex() {
        
        deltaT = (float) ((float) 1.25 + (Ground.movementSpeed * 0.12));
        System.out.println("deltaT " + deltaT);
        
        this.running = new Running(this);
        this.jumping = new Jumping(this);
        this.dead = new Dead(this);
        this.lowerHead = new LowerHead(this);
        //this.pause = new Pause(this);
        //this.blink = new Blink(this);

        this.state = running; 
        
        this.init();
    }
    
    private void init(){
        //at = new AffineTransform();
        
        gravity = (float) 0.75;
        jumpStrenght = 24;
        speedForJumping = (float) (6 * 2.2);//ho lasciato 6 perchè dobbiamo trovare una soluzione per il salto 
                                            //in base alla velocità del personaggio.
        
        //image = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand-colorato.png"));
        //imageColorato = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand-colorato.png"));
        //deadTRex = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes-colorato.png"));
        //leftFootDino = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-left-up-colorato.png"));
        //rightFootDino = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-right-up-colorato.png"));
        //lowerHeadDinoLeft = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-left-up-colorato.png"));
        //lowerHeadDinoRight = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-right-up-colorato.png"));
        //gameOverImage = new Utility().create(this.getClass().getClassLoader().getResource("image/altro/GameOver.png"));
        
        topReached = false;

        //wTRex = image.getWidth(null)
        //hTRex = image.getHeight(null);
        
        wTRex = Resources.instance().getJumpingImage().getWidth(null);
        hTRex = Resources.instance().getJumpingImage().getHeight(null);
       
        wTRexLower = Resources.instance().getLowerHeadLeftFootImage().getWidth(null);
        hTRexLower = Resources.instance().getLowerHeadLeftFootImage().getHeight(null);

        TRexOnGround = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;
        y = TRexOnGround;
        
        //y = (int) (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;
        foot = NO_FOOT;//inizializzo
        //collider = new Area(new Rectangle(X, y, image.getWidth(), image.getHeight()));
        collider = new Utility().createCollider(Resources.instance().getLeftFootImage(), this.x, this.y);
//        outline = new ImageOutline(leftFootDino);
//        collider = new Area(outline.getOutline(leftFootDino));
//        at.translate(x, y);
//        collider.transform(at);
    }
   
    @Override
    public void create(Graphics g) {
        state.create(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.red);
        //g2d.draw(collider);
        //g2d.setColor(Color.black);
    }

    public TrexState getState() {
        return state;
    }

    public void setState(TrexState state) {
        this.state = state;
    }

    public TrexState getRunning() {
        return running;
    }

    public TrexState getJumping() {
        return jumping;
    }

    public TrexState getDead() {
        return dead;
    }

    public TrexState getLowerHead() {
        return lowerHead;
    }

//    public TrexState getPause() {
//        return pause;
//    }
//
//    public TrexState getBlink() {
//        return blink;
//    }
    
    @Override
    public void keyPressed(KeyEvent e) {

        int keyPressed = e.getKeyCode();

        if ((keyPressed == KeyEvent.VK_SPACE || keyPressed == KeyEvent.VK_UP) && this.state != (lowerHead) && !(jumpDisabled)) {
            this.state = jumping ;
            jumpDisabled = true;
            //System.out.println("Space pressed");
        }

        if (keyPressed == KeyEvent.VK_DOWN && this.state != (jumping)) {
            this.state = lowerHead;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int keyTyped = e.getKeyCode();

        if (keyTyped == KeyEvent.VK_DOWN) {
            this.state = running;
        }

        if ((keyTyped == KeyEvent.VK_SPACE || keyTyped == KeyEvent.VK_UP)) {
            jumpDisabled = false;
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
