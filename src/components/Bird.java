/*
 * This class implements a kind of obstacle, the bird.
 * In this class is also managed the bird animation.
 */
package components;

import general.Board;
import general.HomePage;
import java.awt.Graphics;
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
    private final BufferedImage bird2;
    private final int distanceArrowBird = 40;

    /**
     * This is the class constructor. If the game is in demo mode, this method
     * combines arrows with birds to indicate the action that must be performed
     * by the character in order to avoid the obstacle.
     */
    public Bird(int x, int y) {
        super(x, y, Resources.instance().getBird1());
        bird2 = Resources.instance().getBird2();
        this.birdCounter = BIRD1;
        if (HomePage.demo) {
            double heightBelow = (Utility.instance().createCollider(Resources.instance().getDinoBelowLeftUp(), 0, 0)).getBounds2D().getHeight();
            if (Ground.yPosition - y > Resources.instance().getDinoStand().getHeight()) {
                Board.arrows.addArrowRight(x - distanceArrowBird, Ground.yPosition);
            } else if (Ground.yPosition - y > heightBelow) {
                Board.arrows.addArrowDown(x - distanceArrowBird, Ground.yPosition);
            } else {
                Board.arrows.addArrowUp(x - distanceArrowBird, Ground.yPosition);
            }
        }
    }

    /**
     * This method manage the character collision with the obstacle. If the
     * character does not have the pepper power-up, after the collision the
     * health bar level is decreased. Otherwise, the character gains some bonus
     * points. If the character also has the multiplier power-up, the bonus
     * points made are doubled.
     */
    @Override
    public void collisionAction() {
        if (super.TRex.getPower() == TRex.pepperPower) {
            if (super.TRex.multiplier == true) {
                Board.coin += 2 * 10;
            } else {
                Board.coin += 10;
            }
        } else {
            if (!Board.gameOver) {
                HealthBar.instance().decrease(1);
            }
        }
    }

    @Override
    public void create(Graphics g) {
        if (birdCounter == BIRD1) {
            if (animation1 < 5) {
                g.drawImage(getImage(), getX(), getY(), null);
                setCollider(Utility.instance().createCollider(getImage(), getX(), getY()));
                animation1++;
            } else {
                birdCounter = BIRD2;
                g.drawImage(bird2, getX(), getY(), null);
                setCollider(Utility.instance().createCollider(bird2, getX(), getY()));
                animation1 = 0;
            }
        } else {
            if (animation2 < 5) {
                g.drawImage(bird2, getX(), getY(), null);
                setCollider(Utility.instance().createCollider(bird2, getX(), getY()));
                animation2++;
            } else {
                birdCounter = BIRD1;
                g.drawImage(getImage(), getX(), getY(), null);
                setCollider(Utility.instance().createCollider(getImage(), getX(), getY()));
                animation2 = 0;
            }
        }
        //Uncommenting the rows below, you can see the the items collider during the gameplay

//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.red);
//        g2d.draw(collider);
//        g2d.setColor(Color.BLACK);
    }

}
