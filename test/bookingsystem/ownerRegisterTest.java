/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

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
 * @author User
 */
public class ownerRegisterTest {
    
    public ownerRegisterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            Bdb.setup();
            InputCheck.check = new InputCheck();
            Business.register("Pater Nan", "pater99252", "a99256789", "a99256789",
                    "Boxhillsssss", "0405727777", "nanning9925@gmail.com", 9, 17, "0,1,1,1,1,1,0");
            

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
    public void hello() throws SQLException {
     ResultSet rs = Bdb.selectQuery(
                "SELECT * from businesses WHERE username='pater99252'");
        assert(rs.getString("name").equals("Pater Nan"));
        assert(rs.getString("phonenumber").equals("0405727777"));
        assert(rs.getString("password").equals("a99256789"));
        assert(rs.getString("address").equals("Boxhillsssss"));
        assert(rs.getString("email").equals("nanning9925@gmail.com"));
    
    }
}
