/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import static general.UserInterface.height;
import static general.UserInterface.width;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import trex.Trex;
import utility.Resources;

/**
 *
 * @author Angela
 */
public class HealthBar{ //Singleton
    private static HealthBar instance = null;
    
    private final BufferedImage emptyBar;
    private final BufferedImage rectangle;
    private final Image scaledImage;
    private final Image scaledRectImage;
    
    public static final int MAX = 2260;
    private int cnt;
   
    private HealthBar() {
        this.cnt = MAX;
        this.emptyBar = Resources.instance().getHealthBar();
        this.rectangle = Resources.instance().getHealthBarRectangle();
        this.scaledImage = emptyBar.getScaledInstance((int) (width*0.8),(int) (height*0.09), 100);
        this.scaledRectImage = rectangle.getScaledInstance(rectangle.getWidth(),(int) (height*0.073), 100);
    }
    
    public static HealthBar instance(){
        if(instance == null)
            instance = new HealthBar();
        return instance;
    }
    
    public void create(Graphics g){
        
        for(int i = 0; i<this.cnt; i++)
            g.drawImage(this.scaledRectImage, (int) (scaledImage.getWidth(null)*0.069+i*rectangle.getWidth(null)*0.5), (int) (scaledImage.getHeight(null)*0.15), null);
    
        g.drawImage(this.scaledImage, 5, 5, null);
    }
    
    public void increase(int n){
        this.cnt += n;
        if(this.cnt > MAX)
            this.cnt = MAX;
    }
    
    public void decrease(int n){
        this.cnt -= n;
        if(this.cnt <= 0){
            //this.cnt = MAX;
            //Board.running = false;
            Board.gameOver = true;
            Trex.instance().setState(Trex.instance().getDead());
        }
    }

    public static void setInstance(HealthBar instance) {
        HealthBar.instance = instance;
    }
    
}
