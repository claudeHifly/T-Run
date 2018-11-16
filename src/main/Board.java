/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.*;
import java.awt.*;
import components.*;
import java.awt.event.*;
import static java.lang.System.gc;

/**
 *
 * @author Gennaro
 */
public class Board extends JPanel implements Runnable, ActionListener{
    
    private TRex TRex;
    private Ground grass_ground;
    private Obstacles obstacles;
    

    private int distance;
    private int score;
    private Thread animator;

    //INIZIALIZZO BOARD
    public Board() {
        
        setFocusable(true);//il metodo che mi ha salvato la vita con il keyListener
        
        addKeyListener(new TRexAdapter());
        
        //TREX
        TRex = new TRex();;
        
        //GROUND
        grass_ground = new Ground();

        //OSTACOLI
        obstacles = new Obstacles();

        //DISTANZA PERCORSA
        distance = 0;
        //SCORE
        score = 0;

        //ATTENZIONE: questo deve essere fatto nella classe Partita
        grass_ground.update();
        animator = new Thread(this);
        animator.start();
    }

    public void updateGame() {
        distance += 1;
        grass_ground.update();
        obstacles.update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        grass_ground.create(g);//creare sempre prima il ground
        
        TRex.create(g);
        obstacles.create(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("MT: " + Integer.toString(distance), getWidth() / 4 - 100, 100);
        g.drawString("SCORE: " + Integer.toString(score), getWidth() - getWidth() / 4, 100);

        g.dispose();
    }

    @Override
    public void run() {
        
        while(true){
            this.updateGame();
            this.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
    
    
    private class TRexAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            TRex.keyPressed(e);
        }
    }
    
}
