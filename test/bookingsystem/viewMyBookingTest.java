/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author User
 */
public class viewMyBookingTest {

    public viewMyBookingTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        try {
            user userLogin = new user("dwaine", "abc123");
            booking ViewTest = new booking(userLogin);

            booking booking = (booking) ViewTest.bookingList.get(0);
            assert (booking.getUsername().equals(userLogin.getUsername()));
            assert (booking.getWorker().equals("Lily"));
            assert (booking.getFormateDate().equals("2017/08/12 15:00"));
            assert (booking.getPhoneNumber().equals("045465464"));
            assert (booking.getAddress().equals("25 main st"));

            booking booking1 = (booking) ViewTest.bookingList.get(1);
            assert (booking1.getUsername().equals(userLogin.getUsername()));
            assert (booking1.getWorker().equals("Asva"));
            assert (booking1.getFormateDate().equals("2017/09/30 13:00"));
            assert (booking1.getPhoneNumber().equals("6565465465"));
            assert (booking1.getAddress().equals("25 main st"));

            /*
            assert(userLogin.getUsername().equals("dwaine"));
            assert(userLogin.getName().equals("Declan Paine"));
            assert(userLogin.getAddress()
                    .equals("26 Amiens Road CROSS ROADS NSW 2850"));
            assert(userLogin.getPhoneNumber().equals("0240551105"));*/
        } catch (FileNotFoundException e) {
            fail("Customer info file not found");
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
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
