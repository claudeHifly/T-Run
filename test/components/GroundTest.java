/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.geom.Area;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ArtSen
 */
public class GroundTest {
    Ground ground;
    
    public GroundTest() {
        ground = new Ground();
    }

    /**
     * Test of getGroundOnScreen method, of class Ground.
     */
    @Test
    public void testGetGroundOnScreen() {
        System.out.println("getGroundOnScreen");
        int result = ground.getGroundOnScreen();
        assertNotNull(result);
    }

    /**
     * Test of hasCollided method, of class Ground.
     */
    @Test
    public void testHasCollided() {
        System.out.println("hasCollided");
        Area area = new Area();
        boolean expResult = false;
        boolean result = ground.hasCollided(area);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCanyon method, of class Ground.
     */
    @Test
    public void testAddCanyon() {
        System.out.println("addCanyon");
        int result = ground.addCanyon(0);
        assertNotNull(result);
    }
    
}
