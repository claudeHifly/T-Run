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
public class BoneTest {

    Bone bone;

    public BoneTest() {
        bone = new Bone(0, 0);
    }

    /**
     * Test of getValue method, of class Bone.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        int expResult = 1;
        int result = bone.getValue();
        assertEquals(expResult, result);
    }

}
