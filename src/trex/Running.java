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
public class Running implements TrexState, TrexPower {

    private final BufferedImage leftFoot;//immagine TRex leftFoot
    private final BufferedImage rightFoot;//immagine TRex rightFoot
    private final BufferedImage auraImage;

    public Running() {
        this.leftFoot = Resources.instance().getDinoLeftUp();
        this.rightFoot = Resources.instance().getDinoRightUp();
        this.auraImage = Resources.instance().getAura();
    }

    @Override
    public void create(Graphics g) {
        Trex trex = Trex.getInstance();
        if (trex.foot == trex.NO_FOOT) {
            trex.foot = trex.LEFT_FOOT;
            g.drawImage(leftFoot, trex.x, trex.y, null);
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.collider = Utility.instance().createCollider(leftFoot, trex.x, trex.y);
        } else if (trex.foot == trex.LEFT_FOOT) {
            if (trex.leftCounter < 5) {
                g.drawImage(leftFoot, trex.x, trex.y, null);
                if (trex.getPower() == trex.pepperPower) {
                    g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
                trex.collider = Utility.instance().createCollider(leftFoot, trex.x, trex.y);
                trex.leftCounter++;
            } else {
                trex.foot = trex.RIGHT_FOOT;
                g.drawImage(rightFoot, trex.x, trex.y, null);
                if (trex.getPower() == trex.pepperPower) {
                    g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
                trex.collider = Utility.instance().createCollider(rightFoot, trex.x, trex.y);
                trex.leftCounter = 0;    //resetto il contatore e cambio stato
            }
        } else {
            if (trex.rightCounter < 5) {
                g.drawImage(rightFoot, trex.x, trex.y, null);
                if (trex.getPower() == trex.pepperPower) {
                    g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
                trex.collider = Utility.instance().createCollider(rightFoot, trex.x, trex.y);
                trex.rightCounter++;

            } else {
                trex.foot = trex.LEFT_FOOT;
                g.drawImage(leftFoot, trex.x, trex.y, null);
                if (trex.getPower() == trex.pepperPower) {
                    g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
                trex.collider = Utility.instance().createCollider(leftFoot, trex.x, trex.y);
                trex.rightCounter = 0;   //resetto il contatore e cambio stato
            }
        }
    }
}
