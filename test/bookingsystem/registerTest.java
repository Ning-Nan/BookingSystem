/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.FileNotFoundException;
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
public class registerTest {
    
    public registerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        try {
            user userRegister = new user("Ning Nan","pater9925","99256789","99256789","Docklands","0405727777");
        } catch (FileNotFoundException e) {
            fail("Customer info file not found");
        } catch (Exception e) {
            fail(e.getMessage());
        }
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
     * Test of getUsername method, of class user.
     */
    @Test
    public void testGetUsername() {
      
    }

    /**
     * Test of getName method, of class user.
     */

    
}
