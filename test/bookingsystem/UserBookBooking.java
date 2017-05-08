/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
 * @author User
 */
public class UserBookBooking {

    public UserBookBooking() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Bdb.setup();
            Business.currBusiness = new Business(1, "rbusiness", "rbpass");
            User.currUser = new User("dwaine", "abc123");

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
    public void hello() throws ParseException, SQLException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = (Date) dateFormat.parse("2017-08-30");
        LocalDateTime timeStart = LocalDateTime.ofInstant(
                testDate.toInstant(),
                ZoneId.systemDefault());
        timeStart = timeStart.plusHours(13);
        LocalDateTime timeFinish = timeStart.plusHours(1);

        Booking a = Business.currBusiness.getABookingsFromDate(testDate).get(0);
        assertTrue(Business.currBusiness.book(a, timeStart, timeFinish, User.currUser.getID(),
                User.currUser.getName(), User.currUser.getAddress(), User.currUser.getPhoneNumber(),
                1));
        
        ResultSet rs = Bdb.selectQuery(
                "SELECT * from bookings WHERE timeStart = " + Timestamp.valueOf(timeStart).getTime() / 1000);
        
        if(rs.isClosed()){
        fail();
        }
        
        assert(rs.getInt("customerID") == 1);
        assert(rs.getString("name").equals("Declan Paine"));
        assert(rs.getInt("activity") == 1);
        
        
    }
}
