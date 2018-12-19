/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import general.HomePage;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author G8
 */
public class Bird extends Item {

    private int birdCounter;
    final int BIRD1 = 6;
    final int BIRD2 = 7;

    int animation1;
    int animation2;
    private final BufferedImage bird1;
    private final BufferedImage bird2;

    private final int distanceArrowBird = 40;

    public Bird(int x, int y) {
        super(x, y, Resources.instance().getBird1Col());
        this.bird1 = Resources.instance().getBird1Col();
        this.bird2 = Resources.instance().getBird2Col();
        this.birdCounter = BIRD1;
        if (HomePage.demo) {
            System.out.println("Differenza" + (Ground.yPosition - y));
            double heightBelow = (Utility.instance().createCollider(Resources.instance().getDinoBelowLeftUp(), 0, 0)).getBounds2D().getHeight();
            System.out.println("Altezza Seduto" + heightBelow);
            System.out.println("Altezza In piedi" + Resources.instance().getDinoStand().getHeight());

            if (Ground.yPosition - y > Resources.instance().getDinoStand().getHeight()) {
                Board.arrows.addArrowRight(x - distanceArrowBird, Ground.yPosition);
            } else if (Ground.yPosition - y > heightBelow) {
                System.out.println("Sotto");
                Board.arrows.addArrowDown(x - distanceArrowBird, Ground.yPosition);
            } else {
                System.out.println("Sopra");
                Board.arrows.addArrowUp(x - distanceArrowBird, Ground.yPosition);
            }

        }
    }

    @Override
    public void collisionAction() {

        if (super.TRex.getPower() == TRex.pepperPower) {
            System.out.println("BRUCIA UCCELLO");
            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 10;
            } else {
                Board.coin += 10;
            }
        } else {
             if(!Board.gameOver)
                HealthBar.instance().decrease(1);
        /*Board.running = false;
        Board.gameOver = true;
        super.TRex.setState(TRex.getDead()); */
        }
    }

    public void create(Graphics g) {

        if (birdCounter == BIRD1) {
            if (animation1 < 5) {
                g.drawImage(bird1, getX(), getY(), null);
                animation1++;
            } else {
                birdCounter = BIRD2;
                g.drawImage(bird2, getX(), getY(), null);
                animation1 = 0;
            }
        } else {
            if (animation2 < 5) {
                g.drawImage(bird2, getX(), getY(), null);
                animation2++;
            } else {
                birdCounter = BIRD1;
                g.drawImage(bird1, getX(), getY(), null);
                animation2 = 0;
            }
        }
        //animation bird
        //Graphics2D g2d = (Graphics2D) g;

        //g2d.setColor(Color.red);
        //g2d.draw(collider);
        //g2d.setColor(Color.BLACK);
    }

}
