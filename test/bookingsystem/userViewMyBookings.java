/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class userViewMyBookings {

    public userViewMyBookings() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            Bdb.setup();
            User.currUser = new User("dwaine", "abc123", 1);
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
    public void testSomeMethod() {
        ArrayList<Booking> bookings
                = User.currUser.getSortedBookings(Business.currBusiness);
        System.out.println(bookings.size());
        String[] listData = new String[bookings.size()];

        for (int i = 0; i < bookings.size(); i++) {
            Booking tmpBooking = bookings.get(i);
            Employee em = Business.currBusiness.getEmployee(tmpBooking.getEmployeeID());
            listData[i] = tmpBooking.getTimeStart().format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))
                    + " - "
                    + tmpBooking.getTimeFinish().format(
                            DateTimeFormatter.ofPattern("hh:mm a"))
                    + " " + em.getName();
        }

        LocalDateTime timeStart1, timeStart2, timeFinish1, timeFinish2;
        timeStart1 = (new Timestamp((long) 1502514000 * 1000).toLocalDateTime());
        timeStart2 = (new Timestamp((long) 1506740400 * 1000).toLocalDateTime());
        timeFinish1 = (new Timestamp((long) 1502521200 * 1000).toLocalDateTime());
        timeFinish2 = (new Timestamp((long) 1506744000 * 1000).toLocalDateTime());

        assert (listData[0].equals(timeStart1.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))
                + " - "
                + timeFinish1.format(
                        DateTimeFormatter.ofPattern("hh:mm a"))
                + " Fred"));
        assert (listData[1].equals(timeStart2.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))
                + " - "
                + timeFinish2.format(
                        DateTimeFormatter.ofPattern("hh:mm a"))
                + " Fred"));
        assert (listData.length == 2);

    }
}
