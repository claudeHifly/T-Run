/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreboard;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author G8
 */
public class ScoreUserInterfaceTest {

    ScoreUserInterface sUI;

    public ScoreUserInterfaceTest() {
        this.sUI = ScoreUserInterface.instance("playerName");
    }

    /**
     * Test of instance method, of class ScoreUserInterface.
     */
    @Test
    public void testInstance() {
        System.out.println("instance");
        assertNotNull(sUI);
    }

}
