/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
public class AddBookingTest {
    
    public AddBookingTest() {
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
    public void testMethod() throws ParseException, SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate =(Date) dateFormat.parse("2017-07-30");
        LocalDateTime timeStart = LocalDateTime.ofInstant(
                    testDate.toInstant(),
                    ZoneId.systemDefault());
        timeStart = timeStart.plusHours(9);
        LocalDateTime timeFinish = timeStart.plusHours(1);
        boolean success = business.currBusiness.createOpenBooking(business.currBusiness.getEmployee(2), timeStart,
                timeFinish);
        
        assertTrue(success);
        
        long start = 1501369200;
        long end = 1501372800;
        
        ResultSet rs = bdb.selectQuery(
                "SELECT * from bookings WHERE id = '5' businessId = '5'"+
                        " employeeID = 2 ");
        
        assertTrue(!rs.isClosed());
        
        assert(start == rs.getLong("timeStart"));
        assert(end == rs.getLong("timeFinish"));
        
    
    }
}
