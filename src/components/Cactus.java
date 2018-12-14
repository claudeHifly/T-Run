/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author claud
 */
public class Cactus extends Item{
    

    public Cactus(int x, int y) {
        super(x, y, "image/bn/Cactus-" + (int) (Math.random() * 4 + 1) + ".png");
    }
            
}
