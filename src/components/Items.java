/*
 * This class implements a collection of items.
 * The 'hasCollided' method returns the specific item with which the character has collided.
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
        obArray.forEach((ob) -> {
            ob.create(g);
        });
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
