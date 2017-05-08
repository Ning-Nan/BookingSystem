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
public class AddActivityTest {
    
    public AddActivityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
          try {
            Bdb.setup();
            Business.currBusiness = new Business(1, "rbusiness", "rbpass");

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
    public void testMethod() {
    
     try {
            String temp;
            int temp1;
            assertTrue(Business.currBusiness.addActivity("Wash Dog", 120));
            ResultSet rs = Bdb.selectQuery("SELECT * FROM activity WHERE id = 7");
            if (rs.isClosed()) {
                fail();
            }
            temp = rs.getString("name");
            temp1 = rs.getInt("duration");
            assert (temp.equals("Wash Dog"));
            assert (temp1 == 120);

        } catch (SQLException e) {
            fail(e.getMessage());

        } catch (Exception e) {
            fail(e.getMessage());
        }
       
        
    }
}
