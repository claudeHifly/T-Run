/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

//import static components.TRex.x;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utility.Utility;
import resources.Resources;

/**
 *
 * @author Angela
 */
public class Running implements TrexState{

    private final Trex trex;
    private final BufferedImage leftFoot;//immagine TRex leftFoot
    private final BufferedImage rightFoot;//immagine TRex rightFoot
    private Utility utility;
    
    public Running(Trex trex) {
        this.trex = trex;
        
        utility = new Utility();
        
        this.leftFoot = Resources.instance().getLeftFootImage();
        this.rightFoot = Resources.instance().getRightFootImage();
    }

    @Override
    public void create(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        if (trex.foot == trex.NO_FOOT) {
            trex.foot = trex.LEFT_FOOT;
            g.drawImage(leftFoot, trex.x, trex.y, null);
            trex.collider = utility.createCollider(leftFoot, trex.x, trex.y);
        } else if (trex.foot == trex.LEFT_FOOT) {
            if (trex.leftCounter < 5){
                g.drawImage(leftFoot, trex.x, trex.y, null);
                trex.collider = utility.createCollider(leftFoot, trex.x, trex.y);
                trex.leftCounter++;
            } else {
                trex.foot = trex.RIGHT_FOOT;
                g.drawImage(rightFoot, trex.x, trex.y, null);
                trex.collider=utility.createCollider(rightFoot, trex.x, trex.y);
                trex.leftCounter = 0;    //resetto il contatore e cambio stato
            }
        } else {
            if (trex.rightCounter < 5){
                g.drawImage(leftFoot, trex.x, trex.y, null);
                trex.collider=utility.createCollider(rightFoot, trex.x, trex.y);
                trex.rightCounter++;
            } else {
                trex.foot = trex.LEFT_FOOT;
                g.drawImage(leftFoot, trex.x, trex.y, null);
                trex.collider=utility.createCollider(leftFoot, trex.x, trex.y);
                trex.rightCounter = 0;   //resetto il contatore e cambio stato
            }
        }
    }
}