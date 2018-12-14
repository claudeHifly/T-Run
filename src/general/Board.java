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

/**
 *
 * @author Gennaro
 */
public class Board extends JPanel implements Runnable, ActionListener {

    public static boolean running = true;
    public static boolean gameOver = false;
    public static boolean blinking = false;
    private Trex TRex;
    private Blinker blinkerImage;
    private Ground grass_ground;
    private Obstacles obstacles;
    private Bones moneys;
    private Background background;

    public static int distance;
    public static float distanceForScore;
    public static int score;
    public static int coin;
    public static Thread animator;
    public static Thread blinker;
    
    
    
  

    //INIZIALIZZO BOARD
    public Board() {

        setFocusable(true);//keyListener
        addKeyListener(new TRexAdapter());
        startGame();
    }
    
    public void startGame(){
      
        blinkerImage = new Blinker();
        background = new Background();
        grass_ground = new Ground();
        
        //TREX
        TRex = Trex.instance();
        
        //OSTACOLI
        obstacles = new Obstacles();
        
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
        
        blinker = new Thread(this) {
            @Override
            public void run() {

                try {
                    System.out.println("Stampiamo solo la stringa per vedere se entra nel Thread del blinker");
                    Thread.sleep(60);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        };
        animator.start();

    }
    


    public void updateGame() {
        
        distance += 1;
        distanceForScore += 0.1;
        score += 1;
        background.update();
        grass_ground.update();
        moneys.update();
        obstacles.update();

        Item collidedObstacle = obstacles.hasCollided(TRex.getCollider());
        if (collidedObstacle != null) {
            
            collidedObstacle.collisionAction(collidedObstacle);
            /*
            running = false;
            gameOver = true;
            TRex.setState(TRex.getDead());*/
        }
        
        Item collidedMoney = moneys.hasCollided(TRex.getCollider());
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
        g.drawString("MT: " + Integer.toString((int)distanceForScore), getWidth() / 4 - 180, 100);
        //g.drawString("SCORE: " + Integer.toString(score), getWidth() - getWidth() / 4, 100);
        g.drawString("BONES: " + Integer.toString(coin), getWidth() / 4 + 50, 100);


        g.dispose();
  
    }
    


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
            
                reset();
            }
        }
        
        @Override
        public void keyTyped(KeyEvent e){
            TRex.keyTyped(e);
        }
        
        @Override
        public void keyReleased(KeyEvent e){
            TRex.keyReleased(e);
        }
        
        public void reset() {
        Ground.movementSpeed = 8;
        Ground.speedForCactus = 8;
        score = 0;
        distanceForScore = 0;
        coin = 0;
        System.out.println("reset");
        gameOver = false;
        startGame();
        }
    }

}
