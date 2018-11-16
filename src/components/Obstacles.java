/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static components.Ground.movementSpeed;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import main.UserInterface;

/**
 *
 * @author claud
 */
public class Obstacles {
    private ArrayList<Obstacle> obArray;

    public Obstacles() {
        obArray = new ArrayList<Obstacle>();
        Obstacle ob = new Cactus(TRex.x+200, (int)(Ground.yPosition)+ (int)(Ground.yPosition *0.025));
        obArray.add(ob);
        for (int i=1; i<10; i++){
            ob = new Cactus(randomDistance(),(int)(Ground.yPosition)+ (int)(Ground.yPosition *0.025));
            obArray.add(ob);
        }
        //System.out.println(obArray);
    }
    
    public void create(Graphics g){
        for (Obstacle ob:obArray){
            ob.create(g);
        }
        
    }
    
    private int randomDistance() {
        return obArray.get(obArray.size() - 1).getX() + (int) (Math.random() * 200 + 200);
    }
    
    public void update() {
        Iterator<Obstacle> looper = obArray.iterator();
        Obstacle firstOb = looper.next();
        

        firstOb.setX(firstOb.getX() - movementSpeed);

        while (looper.hasNext()) {
            Obstacle ob = looper.next();
            ob.setX(ob.getX() - movementSpeed);
        }

        if (firstOb.getX() < -firstOb.getImage().getWidth()) { //image is completely out of the screen: remove and move it to the end of the array
            obArray.remove(firstOb);
            firstOb.setX(randomDistance());
            obArray.add(firstOb);
        }

    }
    
    
    
}
