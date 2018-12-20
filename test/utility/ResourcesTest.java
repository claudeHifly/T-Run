/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class ResourcesTest {
    Resources resource;
    
    public ResourcesTest() {
        resource = Resources.instance();
    }


    /**
     * Test of instance method, of class Resources.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(resource);
    }
    
}
