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
public class HealthBarTest {
    HealthBar healthbar;
    
    public HealthBarTest() {
        healthbar = HealthBar.instance();
    }

    /**
     * Test of instance method, of class HealthBar.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(healthbar);
    }

    
}
