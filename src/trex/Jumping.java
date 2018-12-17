/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

//import static components.TRex.RUNNING;
//import static components.TRex.state;
//import static components.TRex.x;
import components.Ground;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author Angela
 */
public class Jumping implements TrexState {

    private final Trex trex;
    private final BufferedImage jumpingImage;//immagine TRex rightFoot
    private final BufferedImage auraImage;
    
    public Jumping(Trex trex) {
        this.trex = trex;
        
        this.jumpingImage = Resources.instance().getJumpingImage();
        this.auraImage = Resources.instance().getAuraImage();
    }

    @Override
    public void create(Graphics g) {

        if ( (/*(trex.y > trex.maxHeight) || */(trex.speedForJumping >= 0)) && trex.topReached == false) {

            trex.y -= trex.deltaT * trex.speedForJumping;
            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if(trex.getPower() == trex.pepperPower){
                     g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
            trex.collider=Utility.instance().createCollider(jumpingImage, trex.x, trex.y);
            trex.speedForJumping -= (trex.deltaT * trex.gravity);

            //System.out.println("bottomTRex height: " + bottomTRex);
            //break;
        }

        if ((/*trex.y <= trex.maxHeight || */trex.speedForJumping <= 0) && trex.topReached == false) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if(trex.getPower() == trex.pepperPower){
                     g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
            trex.topReached = true;
        }

        if (trex.topReached == true) {

            //Potrebbe verificarsi il caso in cui, a seguito dei numerosi decrementi effettuati sulla velocità 
            //del TRex in salita, quest'ultima diventi negativa.
            if (trex.speedForJumping < 0) {
                trex.speedForJumping = 0;
            }

            trex.y += trex.deltaT * trex.speedForJumping;
            g.drawImage(this.jumpingImage, trex.x, trex.y, null);
            if(trex.getPower() == trex.pepperPower){
                     g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
                }
            trex.collider=Utility.instance().createCollider(jumpingImage, trex.x, trex.y);
            trex.speedForJumping += (trex.deltaT * trex.gravity);

        }

        if (Ground.movementSpeed > 20 && trex.y >= trex.TRexOnGround - 75 && trex.topReached == true) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);   //deve sempre essere fatto prima g.drawImage
                                                                    //altrimenti abbiamo dei frame in cui scatta
                                                                    
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }
            trex.y = trex.TRexOnGround;
            trex.collider=Utility.instance().createCollider(jumpingImage, trex.x, trex.y);

            trex.topReached = false;
            trex.speedForJumping = (float) (6 * 2.2);
            trex.setState(trex.getRunning());
        }
        
        if (Ground.movementSpeed <= 20 && trex.y >= trex.TRexOnGround - 25 && trex.topReached == true) {

            g.drawImage(this.jumpingImage, trex.x, trex.y, null);   //deve sempre essere fatto prima g.drawImage
                                                                    //altrimenti abbiamo dei frame in cui scatta
                                                                    
            if (trex.getPower() == trex.pepperPower) {
                g.drawImage(auraImage, trex.x - 10, trex.y - 35, null);
            }                                                       
            trex.y = trex.TRexOnGround;
            trex.collider=Utility.instance().createCollider(jumpingImage, trex.x, trex.y);

            trex.topReached = false;
            trex.speedForJumping = (float) (6 * 2.2);
            trex.setState(trex.getRunning());
        }
    }
}
