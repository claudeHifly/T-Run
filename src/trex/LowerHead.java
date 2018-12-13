/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import utility.Utility;
import resources.Resources;

/**
 *
 * @author Angela
 */
public class LowerHead implements TrexState {

    private final Trex trex;
    private final BufferedImage lowerHeadDinoLeft;
    private final BufferedImage lowerHeadDinoRight;
    private Utility utility;
    
    public LowerHead(Trex trex) {
        this.trex = trex;
        this.utility = new Utility();
        lowerHeadDinoLeft = Resources.instance().getLowerHeadLeftFootImage();
        lowerHeadDinoRight = Resources.instance().getLowerHeadRightFootImage();
    }

    @Override
    public void create(Graphics g) {
        
    if (trex.foot == trex.NO_FOOT) {
            trex.foot = trex.LEFT_FOOT_LOWER;
            g.drawImage(this.lowerHeadDinoLeft, trex.x, trex.y, null);
            trex.collider=utility.createCollider(lowerHeadDinoLeft, trex.x, trex.y);
//            trex.collider = new Area(trex.outline.getOutline(trex.lowerHeadDinoLeft));
//            trex.at = new AffineTransform();
//            trex.at.translate(trex.x, trex.y);
//            trex.collider.transform(trex.at);
        } else if (trex.foot == trex.LEFT_FOOT_LOWER) {

            if (trex.leftCounter < 5){
                g.drawImage(this.lowerHeadDinoLeft, trex.x, trex.y, null);
                trex.collider=utility.createCollider(lowerHeadDinoLeft, trex.x, trex.y);
//                trex.collider = new Area(trex.outline.getOutline(trex.lowerHeadDinoLeft));
//                trex.at = new AffineTransform();
//                trex.at.translate(trex.x, trex.y);
//                trex.collider.transform(trex.at);
                trex.leftCounter++;
            } else {
                trex.foot = trex.RIGHT_FOOT_LOWER;
                g.drawImage(this.lowerHeadDinoRight, trex.x, trex.y, null);
                trex.collider=utility.createCollider(lowerHeadDinoLeft, trex.x, trex.y);
//                trex.collider = new Area(trex.outline.getOutline(trex.lowerHeadDinoRight));
//                trex.at = new AffineTransform();
//                trex.at.translate(trex.x, trex.y);
//                trex.collider.transform(trex.at);
                trex.leftCounter = 0;    //resetto il contatore e cambio stato
            }

        } else {

            if (trex.rightCounter < 5){
                g.drawImage(this.lowerHeadDinoRight, trex.x, trex.y, null);
                trex.collider=utility.createCollider(lowerHeadDinoLeft, trex.x, trex.y);
//                trex.collider = new Area(trex.outline.getOutline(trex.lowerHeadDinoRight));
//                trex.at = new AffineTransform();
//                trex.at.translate(trex.x, trex.y);
//                trex.collider.transform(trex.at);
                trex.rightCounter++;
            } else {
                trex.foot = trex.LEFT_FOOT_LOWER;
                g.drawImage(this.lowerHeadDinoLeft, trex.x, trex.y, null);
                trex.collider=utility.createCollider(lowerHeadDinoLeft, trex.x, trex.y);
//                trex.collider = new Area(trex.outline.getOutline(trex.lowerHeadDinoLeft));
//                trex.at = new AffineTransform();
//                trex.at.translate(trex.x, trex.y);
//                trex.collider.transform(trex.at);
                trex.rightCounter = 0;   //resetto il contatore e cambio stato
            }
        }
    }
}