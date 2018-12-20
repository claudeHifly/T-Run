/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import trex.*;
import javax.swing.*;
import java.awt.*;
import components.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import scoreboard.*;
import utility.Resources;

/**
 *
 * @author G8
 */
public class Board extends JPanel implements Runnable, ActionListener {

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
    private String collidedArrowString;
    private String collidedObstacleString;
    private String collidedMoneyString;

    public static int distance = 0;
    public static float distanceForScore;
    
    public int lowestScore;
    private final int thresholdScore = 30;

    public static int score = 0;
    public static int coin = 0;
    public static Thread animator;
    public static Thread blinker;

    public static boolean openScoreboard = true;
    //public HealthBar bar = HealthBar.instance();

    //INIZIALIZZO BOARD
    public Board() {

        /*this.nameLabel = new JLabel("Name:");
        this.nameLabel.setVisible(true);
        this.add(this.nameLabel);*/
        this.explosionImage = Resources.instance().getExplosion();

        setFocusable(true);//keyListener
        addKeyListener(new TRexAdapter());
        startGame();
    }

    public void startGame() {

        //TREX
        //TRex = new Trex();
        background = new Background();
        ground = new Ground();
        if (HomePage.demo) {
            this.collidedArrowString = null;
        }

        this.collidedObstacleString = null;
        this.collidedMoneyString = null;

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

        //ATTENZIONE: questo deve essere fatto nella classe Partita
        background.update();
        ground.update();
        moneys.update();
        
        if((lowestScore = Scoreboard.getLowest()) < thresholdScore){
            lowestScore = thresholdScore;
        }
        
        
        if (HomePage.demo) {
            arrows.update();
        }

        obstacles.update();
        
        

        //bar = new HealthBar();
        animator = new Thread(this);

        animator.start();

    }

    public void updateGame() {

        HealthBar.instance().decrease(0.05);
        distance += 1;
        distanceForScore += 0.1;

        if (score == lowestScore) {
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

            //System.out.println("ho preso il canyon");
            //running = false;
            //gameOver = true;
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

        if (this.collidedObstacle != null) {
            this.collidedObstacleString = collidedObstacle.getClass().getSimpleName();
            this.collidedMoneyString = collidedObstacle.getClass().getSimpleName();
            this.collidedObstacle.collisionAction();
            /*
            running = false;
            gameOver = true;
            TRex.setState(TRex.getDead());
            TRex.setState(TRex.getDead());*/
        }

        if (collidedMoney != null) {
            //System.out.println("Ho preso una monetina shobalola");
            System.out.println(collidedMoney.getClass().getSimpleName());
            moneys.getObArray().remove(collidedMoney);
            collidedMoney.collisionAction();
            //coin += collidedMoney.getValue();
            //score += 1;
        }

        if (colorGame && !changed) {
            changed = true;
            colorGame();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        background.create(g);
        ground.create(g);//creare sempre prima il ground
        moneys.create(g);
        if (HomePage.demo) {
            arrows.create(g);
        }

        obstacles.create(g);

        TRex.create(g);

        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("MT: " + Integer.toString((int) distanceForScore), (int) (UserInterface.width*0.4), (int) (UserInterface.height*0.2));
        //g.drawString("SCORE: " + Integer.toString(score), getWidth() - getWidth() / 4, 100);
        g.drawString("BONES: " + Integer.toString(coin), (int) (UserInterface.width*0.5), (int) (UserInterface.height*0.2));

        if (collidedObstacle != null) {

            //esplosione BIRD
            if (collidedObstacleString.equals("Bird")) {

                /*
                if (birdCounter == BIRD1) {
                    if (animation1 < 5) {
                        g.drawImage(bird1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1++;
                    } else {
                        birdCounter = BIRD2;
                        g.drawImage(bird2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1 = 0;
                    }
                } else {
                    if (animation2 < 5) {
                        g.drawImage(bird2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2++;
                    } else {
                        birdCounter = BIRD1;
                        g.drawImage(bird1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2 = 0;
                    }
                }*/
                collidedObstacle.collisionAction();

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

//                    if (this.TRex.multiplier == true) {
//
//                        g.drawImage(score20Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);
//
//                    } else {
//
//                        g.drawImage(score10Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);
//
//                    }
                }
            }

            //esplosione CACTUS
            if (collidedObstacleString.equals("Cactus")) {
                //collidedObstacle.getClass().getSimpleName()
                System.out.println(collidedObstacle.getClass().getSimpleName());
                collidedObstacle.collisionAction();

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

//                    if (this.TRex.multiplier == true) {
//                        g.drawImage(score10Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);
//
//                    } else {
//                        g.drawImage(score5Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);
//
//                    }
                }
            }

        }

        if (collidedObstacle != null) {

            //esplosione BIRD
            if (collidedObstacleString.equals("Bird")) {

                /*
                if (birdCounter == BIRD1) {
                    if (animation1 < 5) {
                        g.drawImage(bird1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1++;
                    } else {
                        birdCounter = BIRD2;
                        g.drawImage(bird2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation1 = 0;
                    }
                } else {
                    if (animation2 < 5) {
                        g.drawImage(bird2, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2++;
                    } else {
                        birdCounter = BIRD1;
                        g.drawImage(bird1, (int) (UserInterface.width * 0.42), (int) (UserInterface.height * 0.18), null);
                        animation2 = 0;
                    }
                }*/
                collidedObstacle.collisionAction();

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

                }
            }

            //esplosione CACTUS
            if (collidedObstacleString.equals("Cactus")) {
                //collidedObstacle.getClass().getSimpleName()
                System.out.println(collidedObstacle.getClass().getSimpleName());
                collidedObstacle.collisionAction();

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

                }
            }

        }
        if (!gameOver) {
            HealthBar.instance().create(g);
        }
        g.dispose();

    }

    @Override
    public void run() {
        running = true;

        while (running) {

            try {
                //System.out.println("running");
                this.updateGame();
                this.repaint();
                Thread.sleep(35);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }//while running

    }

    public void reset() {
        HomePage.demo = false;
        HealthBar.instance().increase(100);
        Ground.movementSpeed0 = 8;
        color = false;
        colorGame = false;
        changed = false;
        HomePage.demo = false;
        HealthBar.instance().increase(100);
        this.explosionImage = Resources.instance().getExplosion();
        TRex.setPower(TRex.getNoPower());       //resetto il gioco, inizializzo a NoPower
        TRex.setMultiplier(false);
        score = 0;
        distanceForScore = 0;
        coin = 0;
        distance = 0;
        Trex.setInstance(null);
        System.out.println("reset");
        gameOver = false;
        
        startGame();
    }

    public void colorGame() {
        color = false;
        gameOver = false;
        //TRex.setPower(TRex.getNoPower());
        //TRex.setMultiplier(false);
        System.out.println("colorGame");

        //TREX
        Trex.setInstance(null);

        background = new Background();
        ground = new Ground();
//        this.collidedObstacleString = null;
//        this.collidedMoneyString = null;

        //TREX
        TRex = Trex.instance();
        //OSTACOLI

        if (HomePage.demo) {
            arrows = new Arrows();
        }
        obstacles = new Obstacles(ground);
        //MONETINE
        moneys = new PowerUp();

        //ATTENZIONE: questo deve essere fatto nella classe Partita
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
        //
    }

    private class TRexAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            TRex.keyPressed(e);

            int keyPressed = e.getKeyCode();

            if (keyPressed == KeyEvent.VK_ENTER && gameOver) {
                openScoreboard = true;
                reset();
            }
            if (keyPressed == KeyEvent.VK_SPACE && gameOver && openScoreboard) {
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

//        public void reset() {
//            color = false;
//            colorGame = false;
//            changed = false;
//            HomePage.demo = false;
//            HealthBar.instance().increase(100);
//            Ground.movementSpeed0 = 8;
//            TRex.setPower(TRex.getNoPower());       //resetto il gioco, inizializzo a NoPower
//            TRex.setMultiplier(false);
//            score = 0;
//            distanceForScore = 0;
//            distance = 0;
//            coin = 0;
//            System.out.println("reset");
//            gameOver = false;
//
//            startGame();
//        }
    }
}
