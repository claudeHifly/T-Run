/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ArtSen
 */
public class BoneSpecialTest {
    BoneSpecial bonesp;
    
    public BoneSpecialTest() {
        bonesp = new BoneSpecial(0,0);
    }
    

    /**
     * Test of getValue method, of class BoneSpecial.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int expResult = 10;
        int result = bonesp.getValue();
        assertEquals(expResult, result);
    }
    
}
