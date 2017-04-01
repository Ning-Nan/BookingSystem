/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import static bookingsystem.user.roleType.customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mac
 */
public class userLoginTest {

    public userLoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            bdb.setup();
            user.currUser = new user("dwaine", "abc123");

        } catch (SQLException e) {
            fail(e.getMessage());

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
        assert (user.currUser.getName().equals("Declan Paine"));
        assert (user.currUser.getPhoneNumber().equals("0240551105"));
        assert (user.currUser.getAddress().equals("26 Amiens Road CROSS ROADS NSW 2850"));

        assert (user.currUser.getEmail().equals("dwaine@gmail.com"));
        assert (user.currUser.getUsername().equals("dwaine"));
        assert (user.currUser.getRole().equals(customer));
    }
}
