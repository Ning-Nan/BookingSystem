/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.SQLException;
import java.util.ArrayList;
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
public class ShowWorkerAvailableTest {

    public ShowWorkerAvailableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SQLException {
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
    public void setUp() throws SQLException {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMethod() throws SQLException {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        ArrayList<Booking> bookings1 = new ArrayList<Booking>();
        bookings = Business.currBusiness.getEmployee(1).getEmployeeAvailability();
      bookings1 = Business.currBusiness.getEmployee(2).getEmployeeAvailability();
        assert(bookings.size()==0);
        assert(bookings1.size()==0);
    
    }
}
