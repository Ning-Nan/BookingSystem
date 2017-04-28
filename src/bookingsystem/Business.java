package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.ZoneId;
import java.util.List;

/**
 * Java representation of a Business database item and the current logged in
 * business. Caution should be used to make sure a Business object always
 * represents an actual database item.
 */
public class Business {

    public static Business currBusiness;

    private int id;
    private String name;
    private String address;
    private String username;
    private String password;
    private String phoneNumber;

    /**
     * Construct a Business object by querying the database for it's existence.
     *
     * @param id id from the business table
     * @param username owner username
     * @param password owner password
     * @throws SQLException
     * @throws Exception
     */
    public Business(int id, String username, String password)
            throws SQLException, Exception {
        ResultSet rs = Bdb.selectQuery("SELECT * from businesses WHERE id="
                + id);

        if (rs.isClosed()) {
            throw new Exception("Business login ID error");
        }

        if (!rs.getString("username").equals(username)) {
            throw new Exception("Business username doesn't match");
        }

        if (!rs.getString("password").equals(password)) {
            throw new Exception("Business password doesn't match");
        }

        this.name = rs.getString("name");
        this.address = rs.getString("address");
        this.phoneNumber = rs.getString("phonenumber");
        this.username = username;
        this.password = password;
        this.id = id;

    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Add an employee for the a business
     *
     * @param name Employee name
     * @return Success of adding employee
     */
    public boolean addEmployee(String name) {
        try {
            Bdb.iuQuery("INSERT INTO employees (businessID, name) "
                    + "VALUES ("
                    + this.id + ", "
                    + "'" + name + "')");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Given an employeeID, return an Employee object using the database.
     *
     * @param employeeID employeeID from the database table
     * @return Employee object that has the employeeID
     */
    public Employee getEmployee(int employeeID) {
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from employees WHERE "
                    + "businessID=" + this.id + " AND id=" + employeeID);
            if (rs.isClosed()) {
                return null;
            }
            Employee em = new Employee(rs.getInt("id"),
                    rs.getInt("businessID"), rs.getString("name"));
            return em;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Get all the employees for a business
     *
     * @return An ArrayList containing Employee objects of all the employees.
     */
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList();

        ResultSet rs;

        try {
            rs = Bdb.selectQuery("SELECT * from employees WHERE businessID="
                    + this.id + " ORDER BY id");

            if (rs.isClosed()) {
                return employees;
            }

            while (rs.next()) {
                Employee em = new Employee(rs.getInt("id"),
                        rs.getInt("businessID"), rs.getString("name"));
                employees.add(em);
            }

        } catch (SQLException e) {
            return employees;
        }

        return employees;

    }

    /**
     * Get available bookings for a certain date
     *
     * @param d Date
     * @return ArrayList of Bookings
     */
    public ArrayList<Booking> getABookingsFromDate(Date d) {

        ArrayList<Booking> bookings = new ArrayList<Booking>();

        // Convert date object to a LocalDateTime object
        LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(),
                ZoneId.systemDefault());

        // Get the Timestamps of thate day at midnight and the next day at
        // midnight.
        Timestamp tldt = Timestamp.valueOf(ldt);
        Timestamp tldtPlusOneDay = Timestamp.valueOf(ldt.plusDays(1));

        /*
            Timestamps are stored as unix timestamps (seconds),
            Java uses milliseconds. (Divide/Multiply by 1000).
         */
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE "
                    + "businessID=" + this.id + " AND timeStart > "
                    + tldt.getTime() / 1000 + " AND timeStart < "
                    + tldtPlusOneDay.getTime() / 1000 + " AND customerID IS NULL");

            if (rs.isClosed()) {
                // No results, no bookings for the date.
                return bookings;
            }

            while (rs.next()) {
                Booking tmpBooking = new Booking(rs.getInt("id"),
                        rs.getInt("businessID"),
                        rs.getInt("employeeID"),
                        rs.getInt("customerID"),
                        rs.getLong("timeStart"),
                        rs.getLong("timeFinish"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phonenumber"));
                bookings.add(tmpBooking);
            }

            return bookings;
        } catch (SQLException e) {
            return bookings;
        }
    }

    /**
     * Create a new booking for a business, assigned to an employee
     *
     * @param em Employee for the booking
     * @param timeStart Time booking starts
     * @param timeFinish Time booking ends
     * @return Success of booking creation
     */
    public boolean createOpenBooking(Employee em, LocalDateTime timeStart,
            LocalDateTime timeFinish) {

        Timestamp startTimestamp = Timestamp.valueOf(timeStart);
        Timestamp finishTimestamp = Timestamp.valueOf(timeFinish);

        /*
            Timestamps are stored as unix timestamps (seconds),
            Java uses milliseconds. (Divide/Multiply by 1000).
         */
        try {
            /* Make sure an employee doesn't already have a booking
             * that overlaps the booking being created.
             */
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE "
                    + "employeeID=" + em.getId() + " AND timeStart <= "
                    + (startTimestamp.getTime() / 1000)
                    + " AND timeFinish >= " + (finishTimestamp.getTime() / 1000));

            if (!rs.isClosed()) {
                // If a result is returned then a booking overlaps.
                return false;
            }

            /* Make sure an employee doesn't have a booking that will
             * overlap by being mid-way through the booking being created.
             */
            rs = Bdb.selectQuery("SELECT * from bookings WHERE "
                    + "employeeID=" + em.getId() + " AND timeStart < "
                    + (finishTimestamp.getTime() / 1000)
                    + " AND timeFinish > " + (finishTimestamp.getTime() / 1000));

            if (!rs.isClosed()) {
                // If a result is returned then a booking overlaps.
                return false;
            }

            // No bookings overlap, add it to the database.
            boolean success = Bdb.iuQuery("INSERT INTO bookings (employeeID,"
                    + " businessID, timeStart, timeFinish) VALUES ("
                    + em.getId() + ", " + this.id + ", "
                    + startTimestamp.getTime() / 1000 + ", "
                    + finishTimestamp.getTime() / 1000 + ")");

            return success;

        } catch (SQLException e) {
            return false;
        }

    }

    /**
     * Get all bookings available or booked for a business
     *
     * @return List of bookings
     */
    public ArrayList<Booking> getAllBooking() {
        ArrayList<Booking> bookings = new ArrayList<Booking>();

        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE "
                    + "businessID=" + this.id + " AND employeeID" + " AND timeStart" + " AND timeFinish" + " ORDER BY timeStart ASC");

            if (rs.isClosed()) {
                // No bookings
                return bookings;
            }

            while (rs.next()) {
                Booking tmpBooking = new Booking(rs.getInt("id"),
                        rs.getInt("businessID"),
                        rs.getInt("employeeID"),
                        rs.getInt("customerID"),
                        rs.getLong("timeStart"),
                        rs.getLong("timeFinish"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phonenumber"));
                bookings.add(tmpBooking);
            }

            return bookings;
        } catch (SQLException e) {
            return bookings;
        }
    }

    public ArrayList<Activity> getActivity() {

        ArrayList<Activity> intList = new ArrayList<Activity>();

        try {
            ResultSet rs = Bdb.selectQuery("SELECT * FROM activity WHERE businessID = " + this.id);

            if (rs.isClosed()) {
                // no activity
                return intList;
            }

            while (rs.next()) {
                
                Activity tempActivity = new Activity(rs.getInt("id"),
                                                     rs.getInt("businessID"),
                                                     rs.getString("name"),
                                                     rs.getInt("duration")
                                                      );
                intList.add(tempActivity);
                
            }

            return intList;
        } catch (SQLException e) {
            return intList;
        }

    }

    /**
     * Book a booking
     *
     * @param b Booking to be booked
     * @param u User account for the booking
     * @param name Name of the person the booking is made for
     * @param address Address of the person the booking is made for
     * @param phoneNumber Phone Number of the person the booking is made for
     * @return Whether the booking was successfully booked
     * @throws SQLException
     */
    public boolean book(Booking b, User u, String name,
            String address, String phoneNumber) throws SQLException {

        boolean success = Bdb.iuQuery("UPDATE bookings SET customerID="
                + u.getID()
                + ", name='" + name + "'"
                + ", address='" + address + "'"
                + ", phonenumber='" + phoneNumber + "'"
                + " WHERE id=" + b.getId());

        return success;
    }

}
