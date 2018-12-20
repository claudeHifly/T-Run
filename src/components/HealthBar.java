/*
 * The purpose of this class is to implement the health bar for the stamina functionality.
 * This class is implemented according to the Singleton design pattern.
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
 * @author G8
 */
public class HealthBar{
    private static HealthBar instance = null;
    
    private final BufferedImage emptyBar;
    private final BufferedImage rectangle;
    private final Image scaledImage;
    private final Image scaledRectImage;
    
    public static int MAX = 0;
    private int cnt;
   
/** 
 * Constructor method.
 * MAX defines the maximum health level.
 * cnt is the current health level.
 */
    private HealthBar() {
        this.emptyBar = Resources.instance().getHealthBar();
        this.rectangle = Resources.instance().getHealthBarRectangle();
        this.scaledImage = emptyBar.getScaledInstance((int) (width*0.975),(int) (height*0.098), 100);
        this.scaledRectImage = rectangle.getScaledInstance(rectangle.getWidth(),(int) (height*0.0735), 100);
        
        MAX = scaledImage.getWidth(null)- (int) (scaledImage.getWidth(null)*0.069);
        this.cnt = MAX;
    }
    
    public static HealthBar instance(){
        if(instance == null)
            instance = new HealthBar();
        return instance;
    }
    
    public void create(Graphics g){
        for(int i = 0; i<this.cnt; i++)
            g.drawImage(this.scaledRectImage, (int) (scaledImage.getWidth(null)*0.07+i*rectangle.getWidth(null)), (int) (scaledImage.getHeight(null)*0.16), null);
    
        g.drawImage(this.scaledImage, (int) (width*0.01), (int) (height*0.01), null);
    }
    
/**
 * This method increases the health level by n%.
 */    
    public void increase(double n){
        this.cnt += n*MAX/100;
        if(this.cnt > MAX)
            this.cnt = MAX;
    }

/**
 * This method decreases the health level by n%.
 */    
    public void decrease(double n){
        this.cnt -= n*MAX/100;
        if(this.cnt <= 0){
            Board.gameOver = true;
            Trex.instance().setState(Trex.instance().getDead());
        }
    }

    public static void setInstance(HealthBar instance) {
        HealthBar.instance = instance;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    
}
