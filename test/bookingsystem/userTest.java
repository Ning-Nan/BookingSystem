/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class userTest {
    
    public userTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            user userLogin = new user("dwaine", "abc123");
            assert(userLogin.getUsername().equals("dwaine"));
            assert(userLogin.getName().equals("Declan Paine"));
            assert(userLogin.getAddress()
                    .equals("26 Amiens Road CROSS ROADS NSW 2850"));
            assert(userLogin.getPhoneNumber().equals("0240551105"));
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

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
