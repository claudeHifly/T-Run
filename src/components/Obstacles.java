/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Graphics;
import java.util.ArrayList;
import main.UserInterface;

/**
 *
 * @author claud
 */
public class Obstacles {
    private ArrayList<Obstacle> obArray;

    public Obstacles() {
        obArray = new ArrayList<Obstacle>();
        Obstacle ob = new Cactus(TRex.X+100,Ground.yPosition);
        obArray.add(ob);
        for (int i=1; i<10; i++){
            ob = new Cactus(obArray.get(i-1).getX()+50,Ground.yPosition);
            obArray.add(ob);
        }
        System.out.println(obArray);
    }
    
    public void create(Graphics g){
        for (Obstacle ob:obArray){
            ob.create(g);
        }
        
    }
    
    
    
}
