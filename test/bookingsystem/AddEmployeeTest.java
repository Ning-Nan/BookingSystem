/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

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
public class AddEmployeeTest {
    
    public AddEmployeeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
          try {
            bdb.setup();
            business.currBusiness = new business(1, "rbusiness", "rbpass");

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
    
        business.currBusiness.addEmployee("Java");
        
        employee test = business.currBusiness.getEmployee(5);
        
        
        assert(test.getBusinessID() == 1);
        assert(test.getId() == 5);
        assert(test.getName().equals("Java"));
       
        
    }
}
