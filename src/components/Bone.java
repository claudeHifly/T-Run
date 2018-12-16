/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import general.Board;
import static general.Board.coin;
import static general.Board.score;

/**
 *
 * @author claud
 */
public class Bone extends Item{
    private final int value;
    
     public Bone(int x, int y, String path) {
             super(x, y, path);
             if (path.equals("image/color/bone_gold2.png")){
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
