/*
 * The purpose of this class is to provide methods to add all kind of arrows and associate them with their own colliders. 
 * The 'update' method updates the position of the both images and colliders along x-axis.
 */
package components;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

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
        AffineTransform at;
        for (Item ob : obArray) {
            ob.setX(ob.getX() - Ground.movementSpeed);
            at = new AffineTransform();
            at.translate(-Ground.movementSpeed, 0);
            ob.getCollider().transform(at);
        }
        Item firstOb = obArray.get(0);
        if ((firstOb.getX() < -firstOb.getImage().getWidth()) && (!obArray.isEmpty())) { //image is completely out of the screen: remove and move it to the end of the array
            obArray.remove(firstOb);
        }
    }

    public void addArrowUp(int x, int y) {
        Item ob = new ArrowUp(x, y);
        AffineTransform at = new AffineTransform();
        at.translate(ob.getX(), ob.getY());
        ob.getCollider().transform(at);
        obArray.add(ob);
    }

    public void addArrowDown(int x, int y) {
        Item ob = new ArrowDown(x, y);
        AffineTransform at = new AffineTransform();
        at.translate(ob.getX(), ob.getY());
        ob.getCollider().transform(at);
        obArray.add(ob);
    }

    public void addArrowRight(int x, int y) {
        Item ob = new ArrowRight(x, y);
        AffineTransform at = new AffineTransform();
        at.translate(ob.getX(), ob.getY());
        ob.getCollider().transform(at);
        obArray.add(ob);
    }

}
