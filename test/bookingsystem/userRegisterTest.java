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
import java.sql.ResultSet;
import java.sql.SQLException;

public class userRegisterTest {

    public userRegisterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Bdb.setup();
            InputCheck.check = new InputCheck();
            User user = new User("NingNan", "pater9925", "a9925678910", "a9925678910", "Boxhillabcd",
                    "0405727777", "nanning@gmail.com");
            

        } catch (SQLException e) {
            fail(e.getMessage());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
         
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() throws SQLException {
        ResultSet rs = Bdb.selectQuery(
                "SELECT * from customers WHERE username='pater9925'");
        assert(rs.getString("name").equals("NingNan"));
        assert(rs.getString("phonenumber").equals("0405727777"));
        assert(rs.getString("password").equals("a9925678910"));
        assert(rs.getString("address").equals("Boxhillabcd"));
        assert(rs.getString("email").equals("nanning@gmail.com"));

        
    }

}
