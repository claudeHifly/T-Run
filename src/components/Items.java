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
 * @author G8
 */
public abstract class Items {

    protected ArrayList<Item> obArray;

    public void create(Graphics g) {
        for (Item ob : obArray) {
            ob.create(g);
        }
    }

    public Item hasCollided(Area TRexArea) {
        for (Item ob : obArray) {
            Area inter = (Area) ob.getCollider().clone();
            inter.intersect(TRexArea);
            if (!inter.isEmpty()) {
                return ob;
            }
        }

        return null;
    }

    public abstract void update();

    public ArrayList<Item> getObArray() {
        return obArray;
    }

}
