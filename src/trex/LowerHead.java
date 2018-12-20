/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author G8
 */
public class LowerHead implements TrexState {

    private final BufferedImage lowerHeadDinoLeft;
    private final BufferedImage lowerHeadDinoRight;
    private final BufferedImage biggerAuraImage;

    public LowerHead() {
        this.lowerHeadDinoLeft = Resources.instance().getDinoBelowLeftUp();
        this.lowerHeadDinoRight = Resources.instance().getDinoBelowRightUp();
        this.biggerAuraImage = Resources.instance().getBiggerAura();
    }

    @Override
    public void create(Graphics g) {
        if (Trex.instance().foot == Trex.instance().NO_FOOT) {
            Trex.instance().foot = Trex.instance().LEFT_FOOT_LOWER;
            g.drawImage(this.lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y, null);
            if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                g.drawImage(biggerAuraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
            }
            Trex.instance().collider = Utility.instance().createCollider(lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y);
        } else if (Trex.instance().foot == Trex.instance().LEFT_FOOT_LOWER) {

            if (Trex.instance().leftCounter < 5) {
                g.drawImage(this.lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y, null);
                if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                    g.drawImage(biggerAuraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
                }
                Trex.instance().collider = Utility.instance().createCollider(lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y);
                Trex.instance().leftCounter++;
            } else {
                Trex.instance().foot = Trex.instance().RIGHT_FOOT_LOWER;
                g.drawImage(this.lowerHeadDinoRight, Trex.instance().x, Trex.instance().y, null);
                if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                    g.drawImage(biggerAuraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
                }
                Trex.instance().collider = Utility.instance().createCollider(lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y);
                Trex.instance().leftCounter = 0;    //resetto il contatore e cambio stato
            }
        } else {
            if (Trex.instance().rightCounter < 5) {
                g.drawImage(this.lowerHeadDinoRight, Trex.instance().x, Trex.instance().y, null);
                if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                    g.drawImage(biggerAuraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
                }
                Trex.instance().collider = Utility.instance().createCollider(lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y);
                Trex.instance().rightCounter++;
            } else {
                Trex.instance().foot = Trex.instance().LEFT_FOOT_LOWER;
                g.drawImage(this.lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y, null);
                if (Trex.instance().getPower() == Trex.instance().pepperPower) {
                    g.drawImage(biggerAuraImage, Trex.instance().x - 10, Trex.instance().y - 35, null);
                }
                Trex.instance().collider = Utility.instance().createCollider(lowerHeadDinoLeft, Trex.instance().x, Trex.instance().y);
                Trex.instance().rightCounter = 0;   //resetto il contatore e cambio stato
            }
        }
    }
}
