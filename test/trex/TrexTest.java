/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trex;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class TrexTest {
    Trex trex;
    
    public TrexTest() {
        this.trex = Trex.instance();
    }

    /**
     * Test of instance method, of class Trex.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(trex);
    }

    /**
     * Test of isMultiplier method, of class Trex.
     */
    @Test
    public void testIsMultiplier() {
        System.out.println("isMultiplier");
        boolean expResult = false;
        boolean result = trex.isMultiplier();
        assertEquals(expResult, result);
    }
    
}
