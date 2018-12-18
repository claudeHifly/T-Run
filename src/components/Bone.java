/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import static general.Board.coin;
import static general.Board.score;
import java.awt.image.BufferedImage;
import utility.Resources;

/**
 *
 * @author claud
 */
public class Bone extends Item{
    private final int value;
    
     public Bone(int x, int y, BufferedImage image) {
             super(x, y, image);
             if (image == Resources.instance().getBoneSpecialCol() || image == Resources.instance().getBoneSpecial()){
                 this.value=50;
             }
             else{
                 this.value=1;
             }
     }

    public int getValue() {
        return value;
    }

    @Override
    public void collisionAction(Item collidedItem) {
        if (super.TRex.multiplier == true){
            Board.coin += 2*((Bone) collidedItem).getValue();
            //Board.score += 1;
        } else {
            Board.coin += ((Bone) collidedItem).getValue();
        }
    }

}
