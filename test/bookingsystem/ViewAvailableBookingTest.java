/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author User
 */
public class ViewAvailableBookingTest {
    
    public ViewAvailableBookingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         try {
            bdb.setup();
            user.currUser = new user("dwaine", "abc123");
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
    public void testMethod() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate =(Date) dateFormat.parse("2017-08-30");
        System.out.println(testDate);
        ArrayList<booking> bookings =business.currBusiness.getABookingsFromDate(testDate);
        
        assert(bookings.size() == 1);
        assert(bookings.get(0).getId() == 1);
        
        assert(bookings.get(0).getAddress() == null);
        assert(bookings.get(0).getBusinessID()==1);
        
        assert(bookings.get(0).getName() == null);
        assert(bookings.get(0).getPhoneNumber() == null);
        assert(bookings.get(0).getCustomerID()!=1);
    
    }
}