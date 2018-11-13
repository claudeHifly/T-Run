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
public class TRex {
    
    private Image imageTRex;//immagine TRex

    public TRex(){
        initTRex();
    }
    
    private void initTRex(){
        loadImage();
         
    }
    
    private void loadImage(){
        ImageIcon iconTRex = new ImageIcon("src/image/TRex-stand.png");
        imageTRex = iconTRex.getImage();
    }
    
    
    public void create(Graphics g) {
        g.drawImage(imageTRex, 50, (int)(UserInterface.height*0.8-55), null);
        //ATTENZIONE! IL -55 VA ASSOLUTAMENTE SOSTITUITO CON LA HEIGHT DELLO
        //DEL TREX
    }
    
}
