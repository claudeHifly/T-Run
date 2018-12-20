/*
 * This class is used to implement the main character of the game, the TRex, according to the State design pattern.
 * This class is used to manage the Trex state and therefore the TRex behaviour.
 * This class is also implemented using the Singleton design pattern.
 */
package trex;

import components.Ground;
import general.HomePage;
import general.UserInterface;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author G8
 */
public class Trex extends KeyAdapter implements TrexState, TrexPower {

    private static Trex instance = null;
    public final static int groundLevel = (int) (UserInterface.height * 0.75);
    static final int maxHeight = (int) (UserInterface.height - UserInterface.height * 0.50);
    public static int TRexOnGround;
    public final static int x = 50;
    static boolean topReached;
    private static int wTRex;
    private static int hTRex;
    private static int wTRexLower;
    private static int hTRexLower;
    private final TrexState running;
    private final TrexState jumping;
    private final TrexState dead;
    private final TrexState lowerHead;
    private final TrexState falling;
    public TrexPower pepperPower;
    public TrexPower noPower;
    public boolean multiplier;
    private TrexState state;
    private TrexPower power;
    private final BufferedImage mulBanner1;
    private final BufferedImage mulBanner2;
    float deltaT;
    int y;
    float gravity;
    float speedForJumping;
    private boolean jumpDisabled;

    int leftCounter;        //left foot animation counter
    int rightCounter;       //right foot animation counter

    int animation1;
    int animation2;
    Area collider;
    int foot;
    int bannerCounter;
    final int LEFT_FOOT = 1;
    final int RIGHT_FOOT = 2;
    final int NO_FOOT = 3;
    final int LEFT_FOOT_LOWER = 4;
    final int RIGHT_FOOT_LOWER = 5;
    final int BANNER1 = 6;
    final int BANNER2 = 7;

    public static Trex instance() {
        if (instance == null) {
            instance = new Trex();
        }
        if (instance.getState() == instance.getDead()) {
            instance.init();
            instance.setState(instance.getRunning());
        }
        return instance;

    }

    public static Trex getInstance() {
        return instance;
    }

    public static int getTRexOnGround() {
        return TRexOnGround;
    }

    public static void setTRexOnGround(int TRexOnGround) {
        Trex.TRexOnGround = TRexOnGround;
    }

    public static void setInstance(Trex instance) {
        Trex.instance = instance;
    }

    private Trex() {
        deltaT = (float) ((float) 1.25 + (Ground.movementSpeed * 0.12));
        this.animation1 = 0;
        this.animation2 = 0;
        this.mulBanner1 = Resources.instance().getMulBanner1();
        this.mulBanner2 = Resources.instance().getMulBanner2();
        this.running = new Running();
        this.jumping = new Jumping();
        this.dead = new Dead();
        this.lowerHead = new LowerHead();
        this.falling = new Falling();
        this.pepperPower = new PepperPower();
        this.noPower = new NoPower();
        this.multiplier = false;
        this.state = running;
        this.power = noPower;      //inizializzo, nessun powerUP
        this.bannerCounter = BANNER1;
        this.init();
    }

    private void init() {
        gravity = (float) 0.75;
        speedForJumping = (float) (6 * 2.2);
        topReached = false;
        wTRex = Resources.instance().getDinoStand().getWidth(null);
        hTRex = Resources.instance().getDinoStand().getHeight(null);
        wTRexLower = Resources.instance().getDinoBelowLeftUp().getWidth(null);
        hTRexLower = Resources.instance().getDinoBelowLeftUp().getHeight(null);
        TRexOnGround = (Ground.yPosition) + (int) (Ground.yPosition * 0.025) - hTRex;
        y = TRexOnGround;
        foot = NO_FOOT;
        collider = Utility.instance().createCollider(Resources.instance().getDinoBelowLeftUp(), Trex.x, this.y);
    }

    @Override
    public void create(Graphics g) {
        state.create(g);

        //Uncommenting the lines below, you can see the the TRex collider during the gameplay
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(collider);
//        g2d.setColor(Color.black);
        if (instance.getState() != dead) {
            if (multiplier == true) {
                if (bannerCounter == BANNER1) {
                    if (animation1 < 5) {
                        g.drawImage(mulBanner1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1++;
                    } else {
                        bannerCounter = BANNER2;
                        g.drawImage(mulBanner2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1 = 0;
                    }
                } else {
                    if (animation2 < 5) {
                        g.drawImage(mulBanner2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2++;
                    } else {
                        bannerCounter = BANNER1;
                        g.drawImage(mulBanner1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2 = 0;
                    }
                }
            }
        }
    }

    public TrexState getState() {
        return state;
    }

    public void setState(TrexState state) {
        this.state = state;
    }

    public TrexPower getPower() {
        return power;
    }

    public void setPower(TrexPower power) {
        this.power = power;
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

    public TrexState getFalling() {
        return falling;
    }

    public TrexPower getPepperPower() {
        return pepperPower;
    }

    public TrexPower getNoPower() {
        return noPower;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();
        if (!HomePage.demo) {
            if ((keyPressed == KeyEvent.VK_SPACE || keyPressed == KeyEvent.VK_UP) && this.state != (falling) && this.state != (lowerHead) && !(jumpDisabled) && this.state != dead) {
                this.state = jumping;
                jumpDisabled = true;
            }
            if (keyPressed == KeyEvent.VK_DOWN && this.state != (jumping)) {
                this.state = lowerHead;
            }
        } else {
            if (keyPressed == KeyEvent.VK_ESCAPE) {
                setState(getDead());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!HomePage.demo) {
            int keyTyped = e.getKeyCode();
            if (keyTyped == KeyEvent.VK_DOWN && (state != jumping)) {
                this.state = running;
            }
            if ((keyTyped == KeyEvent.VK_SPACE || keyTyped == KeyEvent.VK_UP)) {
                jumpDisabled = false;
            }
        }
    }

    public int getwTRex() {
        return wTRex;
    }

    public void setwTRex(int wTRex) {
        Trex.wTRex = wTRex;
    }

    public int gethTRex() {
        return hTRex;
    }

    public void sethTRex(int hTRex) {
        Trex.hTRex = hTRex;
    }

    public Area getCollider() {
        return collider;
    }

    public boolean isMultiplier() {
        return multiplier;
    }

    public void setMultiplier(boolean multiplier) {
        this.multiplier = multiplier;
    }

}
