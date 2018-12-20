/*
 * This class implements the JPanel in which is shown, step by step, the game evolution. 
 */
package general;

import components.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import scoreboard.*;
import trex.*;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Board extends JPanel implements Runnable, ActionListener {

    public static boolean demo;
    public static boolean running = true;
    public static boolean gameOver = false;
    public static boolean blinking = false;
    public static boolean color = false;
    public static boolean colorGame = false;
    public static boolean changed = false;
    private Trex TRex;
    public static Ground ground;
    private Obstacles obstacles;
    public static Arrows arrows;
    private PowerUp moneys;
    private Background background;
    private Item collidedArrow;
    private Item collidedObstacle;
    private Item collidedMoney;
    private BufferedImage explosionImage;
    public static int distance = 0;
    public static float distanceForScore;
    public int lowestScore;
    private final int thresholdScore = 30;
    public static int score = 0;
    public static int coin = 0;
    public static Thread animator;
    public static Thread blinker;
    public static boolean openScoreboard = true;

    //INIZIALIZZO BOARD
    public Board() {
        this.explosionImage = Resources.instance().getExplosion();
        setFocusable(true);//keyListener
        addKeyListener(new TRexAdapter());
        startGame();
    }

    /**
     * This method initializes all the features to start gaming. If the game is
     * in the demo mode, arrows are needed. The thresholdScore attribute is used
     * to set the score the player must reach to switch the design of the game
     * from black and white to coloured.
     */
    public void startGame() {
        background = new Background();
        ground = new Ground();
        //TREX
        TRex = Trex.instance();
        // FRECCE DEMO
        if (HomePage.demo) {
            arrows = new Arrows();
        }
        //OSTACOLI
        obstacles = new Obstacles(ground);
        //MONETINE
        moneys = new PowerUp();
        background.update();
        ground.update();
        moneys.update();
        Scoreboard.readFromScoreFile();
        lowestScore = Scoreboard.getLowest();
        System.out.println(lowestScore);
        System.out.println(Scoreboard.getScoreboard().size());
        if (lowestScore < thresholdScore) {
            lowestScore = thresholdScore;
        }
        if (HomePage.demo) {
            arrows.update();
        }
        obstacles.update();
        animator = new Thread(this);
        animator.start();
    }

    /**
     * This method updates, step by step, the game by increasing the score,
     * decreasing the health bar level, moving the ground and the background
     * along the x-axis, etc.. In this method is also checked if the character
     * has collided with an item in order to perform the right action.
     */
    public void updateGame() {
        HealthBar.instance().decrease(0.05);
        distance += 1;
        distanceForScore += 0.1;
        if (score >= lowestScore && !colorGame) {
            color = true;
        }
        score = coin + (int) distanceForScore;  //PUNTEGGIO FINALE
        background.update();
        ground.update();
        moneys.update();
        if (HomePage.demo) {
            arrows.update();
        }
        obstacles.update();
        if ((TRex.getState() != TRex.getFalling()) && (TRex.getState() != TRex.getJumping()) && !ground.hasCollided(TRex.getCollider())) {
            TRex.setState(TRex.getFalling());
        }
        if (HomePage.demo) {
            collidedArrow = arrows.hasCollided(TRex.getCollider());
            if (this.collidedArrow != null) {
                collidedArrow.collisionAction();
            }
        }
        collidedObstacle = obstacles.hasCollided(TRex.getCollider());
        collidedMoney = moneys.hasCollided(TRex.getCollider());
        if (collidedObstacle != null) {
            collidedObstacle.collisionAction();
        }
        if (collidedMoney != null) {
            moneys.getObArray().remove(collidedMoney);
            collidedMoney.collisionAction();
        }
        if (colorGame && !changed) {
            changed = true;
            colorGame();
        }
    }

    /**
     * This method drows the 'state' of the game on the JPanel.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        background.create(g);
        ground.create(g);
        moneys.create(g);
        if (HomePage.demo) {
            arrows.create(g);
        }
        obstacles.create(g);
        TRex.create(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("MT: " + Integer.toString((int) distanceForScore), (int) (UserInterface.width * 0.4), (int) (UserInterface.height * 0.2));
        g.drawString("BONES: " + Integer.toString(coin), (int) (UserInterface.width * 0.5), (int) (UserInterface.height * 0.2));
        if (HomePage.demo && !gameOver) {
            g.drawString("Press ESC to stop demo", (int) (UserInterface.width * 0.39), (int) (UserInterface.height * 0.9));
        }
        if (collidedObstacle != null && TRex.getPower() == TRex.pepperPower) {
            g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione
        }
        if (!gameOver) {
            HealthBar.instance().create(g);
        }
        g.dispose();
    }

    /**
     * Game thread task.
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                this.updateGame();
                this.repaint();
                Thread.sleep(35);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * This method allows to reset all the features of the game in order to
     * restart the game.
     */
    public void reset() {
        Ground.movementSpeed0 = 8;
        color = false;
        colorGame = false;
        changed = false;
        HomePage.demo = false;
        this.explosionImage = Resources.instance().getExplosion();
        TRex.setPower(TRex.getNoPower());       //reset of the game, initialize to NoPower
        TRex.setMultiplier(false);
        score = 0;
        distanceForScore = 0;
        coin = 0;
        distance = 0;
        Trex.setInstance(null);
        HealthBar.setInstance(null);
        HealthBar.instance().increase(100);
        gameOver = false;
        startGame();
    }

    /**
     * This method initializes the coloured version of the game.
     */
    public void colorGame() {
        color = false;
        gameOver = false;
        //TREX
        Trex.setInstance(null);
        background = new Background();
        ground = new Ground();
        //TREX
        TRex = Trex.instance();
        //OSTACOLI
        if (HomePage.demo) {
            arrows = new Arrows();
        }
        obstacles = new Obstacles(ground);
        //MONETINE
        moneys = new PowerUp();
        background.update();
        ground.update();
        moneys.update();
        obstacles.update();
        explosionImage = Resources.instance().getExplosion();
        int count = HealthBar.instance().getCnt();
        HealthBar.setInstance(null);
        HealthBar.instance();
        HealthBar.instance().setCnt(count);
        running = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * This private nested class implements the keyboard functionality for the
     * game.
     */
    private class TRexAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            TRex.keyPressed(e);
            int keyPressed = e.getKeyCode();
            if (keyPressed == KeyEvent.VK_ENTER && gameOver) {
                openScoreboard = true;
                reset();
            }
            if (keyPressed == KeyEvent.VK_SPACE && gameOver && openScoreboard && !HomePage.demo) {
                String name = JOptionPane.showInputDialog("Enter your name:", "");
                if (name != null) {
                    openScoreboard = false;
                    if (name.length() > 3) {
                        ScoreUserInterface.instance(name.substring(0, 3));
                    } else {
                        ScoreUserInterface.instance(name);
                    }
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            TRex.keyTyped(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            TRex.keyReleased(e);
        }
    }
}
