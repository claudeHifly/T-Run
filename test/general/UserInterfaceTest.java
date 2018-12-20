/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class UserInterfaceTest {

    UserInterface UI;

    public UserInterfaceTest() {
        this.UI = UserInterface.instance();
    }

    /**
     * Test of instance method, of class UserInterface.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(UI);
    }

}
