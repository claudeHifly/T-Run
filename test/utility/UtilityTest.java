/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class UtilityTest {
    Utility utility;
    
    public UtilityTest() {
        this.utility = Utility.instance();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of instance method, of class Utility.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        Utility result = utility;
        assertTrue(result != null);
    }

    /**
     * Test of create method, of class Utility.
     */
   /*@Test
    public void testCreate() {
        System.out.println("create");
        URL path = null;
        Utility instance = null;
        BufferedImage expResult = null;
        BufferedImage result = instance.create(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCollider method, of class Utility.
     */
   /* @Test
    public void testCreateCollider() {
        System.out.println("createCollider");
        BufferedImage img = null;
        int x = 0;
        int y = 0;
        Utility instance = null;
        Area expResult = null;
        Area result = instance.createCollider(img, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
