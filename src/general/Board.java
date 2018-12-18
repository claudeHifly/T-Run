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
import scoreboard.ScoreUserInterface;
import utility.Resources;

/**
 *
 * @author Gennaro
 */
public class Board extends JPanel implements Runnable, ActionListener {

    public static boolean running = true;
    public static boolean gameOver = false;
    public static boolean blinking = false;
    private Trex TRex;
    public static Ground grass_ground;
    private Obstacles obstacles;
    private Bones moneys;
    private Background background;
    private Item collidedObstacle;
    private Item collidedMoney;
    private final BufferedImage explosionImage;
    private String collidedObstacleString;
    private String collidedMoneyString;
    private final BufferedImage score5Col;
    private final BufferedImage score10Col;
    private final BufferedImage score20Col;
    
    

    public static int distance;
    public static float distanceForScore;

    public static int score;
    public static int coin;
    public static Thread animator;
    public static Thread blinker;
    
    
    public static boolean openScoreboard = true;

    //INIZIALIZZO BOARD
    public Board() {

        /*this.nameLabel = new JLabel("Name:");
        this.nameLabel.setVisible(true);
        this.add(this.nameLabel);*/
        
        this.explosionImage = Resources.instance().getExplosionCol();
        this.score5Col = Resources.instance().getScore5Col();
        this.score10Col = Resources.instance().getScore10Col();
        this.score20Col = Resources.instance().getScore20Col();
        
        
        setFocusable(true);//keyListener
        addKeyListener(new TRexAdapter());
        startGame();
    }

    public void startGame() {

        //TREX
        //TRex = new Trex();
        background = new Background();
        grass_ground = new Ground();
        this.collidedObstacleString = null;
        this.collidedMoneyString = null;

        //TREX
        TRex = Trex.instance();
        //OSTACOLI
        obstacles = new Obstacles(grass_ground);

        //MONETINE
        moneys = new Bones();

        //DISTANZA PERCORSA
        distance = 0;
        //SCORE
        score = 0;
        coin = 0;

        //ATTENZIONE: questo deve essere fatto nella classe Partita
        background.update();
        grass_ground.update();
        moneys.update();
        obstacles.update();

        animator = new Thread(this);

        animator.start();

    }

    public void updateGame() {

        distance += 1;
        distanceForScore += 0.1;
        
        score = coin + (int) distanceForScore;  //PUNTEGGIO FINALE
        
        background.update();
        grass_ground.update();
        moneys.update();
        obstacles.update();

        if ( (TRex.getState() != TRex.getFalling()) && (TRex.getState() != TRex.getJumping()) && !grass_ground.hasCollided(TRex.getCollider())) {
            
            //System.out.println("ho preso il canyon");
            //running = false;
            //gameOver = true;
            TRex.setState(TRex.getFalling());
        }

        this.collidedObstacle = obstacles.hasCollided(TRex.getCollider());
        this.collidedMoney = moneys.hasCollided(TRex.getCollider());
        
        if (this.collidedObstacle != null) {
            this.collidedObstacleString = collidedObstacle.getClass().getSimpleName();
            this.collidedMoneyString = collidedObstacle.getClass().getSimpleName();
            this.collidedObstacle.collisionAction(collidedObstacle);
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
            collidedMoney.collisionAction(collidedMoney);
            //coin += collidedMoney.getValue();
            //score += 1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        background.create(g);
        grass_ground.create(g);//creare sempre prima il ground
        moneys.create(g);
        obstacles.create(g);

        TRex.create(g);

        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("MT: " + Integer.toString((int) distanceForScore), getWidth() / 4 - 180, 100);
        //g.drawString("SCORE: " + Integer.toString(score), getWidth() - getWidth() / 4, 100);
        g.drawString("BONES: " + Integer.toString(coin), getWidth() / 4 + 50, 100);
        
        if (collidedObstacle!= null) {
            
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
                
                
                collidedObstacle.collisionAction(collidedObstacle);

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

                    if (this.TRex.multiplier == true) {

                        g.drawImage(score20Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);

                    } else {

                        g.drawImage(score10Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);

                    }
                }
            }
            
            //esplosione CACTUS
            if (collidedObstacleString.equals("Cactus")) {
                //collidedObstacle.getClass().getSimpleName()
                System.out.println(collidedObstacle.getClass().getSimpleName());
                collidedObstacle.collisionAction(collidedObstacle);

                if (TRex.getPower() == TRex.pepperPower) {
                    g.drawImage(explosionImage, collidedObstacle.getX() - 40, collidedObstacle.getY() - 24, null); //esplosione

                    if (this.TRex.multiplier == true) {
                        g.drawImage(score10Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);

                    } else {
                        g.drawImage(score5Col, collidedObstacle.getX() - 18, collidedObstacle.getY() - 20, null);

                    }
                }
            }

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
                String name = JOptionPane.showInputDialog("Enter your name:","");
                if(name != null){
                    openScoreboard = false;
                    if(name.length() > 3)
                        ScoreUserInterface.instance(name.substring(0,3));
                    else
                        ScoreUserInterface.instance(name);
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

        public void reset() {
            Ground.movementSpeed0 = 8;
            TRex.setPower(TRex.getNoPower());       //resetto il gioco, inizializzo a NoPower
            TRex.setMultiplier(false);
            score = 0;
            distanceForScore = 0;
            coin = 0;
            System.out.println("reset");
            gameOver = false;
            startGame();
        }
    }
}
