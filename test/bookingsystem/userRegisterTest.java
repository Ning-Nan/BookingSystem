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
            bdb.setup();
            user user = new user("Ning", "pater", "9925", "9925", "Boxhill",
                    "0405727777", "nanning@gmail.com");
            

        } catch (SQLException e) {
            fail(e.getMessage());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        bdb.iuQuery("DELETE FROM customers WHERE name = 'Ning'");
         
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() throws SQLException {
        ResultSet rs = bdb.selectQuery(
                "SELECT * from customers WHERE username='pater'");
        assert(rs.getString("name").equals("Ning"));
        assert(rs.getString("phonenumber").equals("0405727777"));
        assert(rs.getString("password").equals("9925"));
        assert(rs.getString("address").equals("Boxhill"));
        assert(rs.getString("email").equals("nanning@gmail.com"));

        
    }

}
