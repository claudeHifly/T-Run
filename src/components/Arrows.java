/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.ArrayList;
import utility.Utility;

/**
 *
 * @author G8
 */
public class Arrows extends Items {

    private final double yPercentageArrowOnGround = 0.025;

    public Arrows() {
        obArray = new ArrayList<>();
    }

    @Override
    public void update() {
        for (Item ob : obArray) {
            ob.setX(ob.getX() - Ground.movementSpeed);
            Utility.instance().moveCollider(ob.getCollider(), -Ground.movementSpeed, 0);
        }
        Item firstOb = obArray.get(0);
        if ((firstOb.getX() < -firstOb.getImage().getWidth()) && (!obArray.isEmpty())) { //image is completely out of the screen: remove and move it to the end of the array
            obArray.remove(firstOb);
        }
    }

    public void addArrowUp(int x, int y) {
        Item ob = new ArrowUp(x, y);
        Utility.instance().moveCollider(ob.getCollider(), ob.getX(), ob.getY());
        obArray.add(ob);
    }

    public void addArrowDown(int x, int y) {
        Item ob = new ArrowDown(x, y);
        Utility.instance().moveCollider(ob.getCollider(), ob.getX(), ob.getY());
        obArray.add(ob);
    }

    public void addArrowRight(int x, int y) {
        Item ob = new ArrowRight(x, y);
        Utility.instance().moveCollider(ob.getCollider(), ob.getX(), ob.getY());
        obArray.add(ob);
    }

}
