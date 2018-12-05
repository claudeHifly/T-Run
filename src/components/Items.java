/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Graphics;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 *
 * @author claud
 */
public interface Items {
    
    
    public void create(Graphics g);
    public Item hasCollided(Area TRexArea);
    public void update();
    public ArrayList<Item> getObArray();       
}
