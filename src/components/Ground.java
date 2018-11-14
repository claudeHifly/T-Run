/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.*;
import javax.swing.*;
import main.UserInterface;

/**
 *
 * @author Gennaro
 */
public class Ground {
    
    public Image grass_ground;//immagine suolo
    
    public Ground(){
        initGround();
    }
    
    private void initGround(){
        //GROUND
        loadImage();//ho lasciato load image perchè potremmo aver bisogno 
                    //di questo metodo per cambiare il ground
        
        //????????????????????????????????????????????????????????
        /*
        int w = grass_ground.getWidth((ImageObserver)this);
        int h = grass_ground.getHeight((ImageObserver)this);
        setPreferredSize(new Dimension(w, h));
        */
        //???????????????????????????????????????????????????????????
    }
    
    private void loadImage() {
        ImageIcon iconGround = new ImageIcon("src/image/old/Ground.png");
        grass_ground = iconGround.getImage();        
    }
    
    public void create(Graphics g) {
        g.drawImage(grass_ground, 0, (int)(UserInterface.height*0.75), null);
    }
}
