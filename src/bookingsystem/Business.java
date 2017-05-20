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
    private int start;
    private int close;
    private boolean[] days;

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
        
        this.start = rs.getInt("start");
        this.close = rs.getInt("close");
        
        this.days = decodeDaysString(rs.getString("days"));

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
     * Get available bookings for a certain date which has enough time for the
     * activity
     *
     * @param d Date
     * @param duration Activity duration
     * @return ArrayList of Bookings
     */
    public ArrayList<Booking> getABookingsFromDate(Date d, int duration) {

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
            ResultSet rs = Bdb.selectQuery(
                    "SELECT *, (timeFinish - timeStart) AS duration from bookings WHERE "
                    + "businessID=" + this.id + " AND timeStart > "
                    + tldt.getTime() / 1000 + " AND timeStart < "
                    + tldtPlusOneDay.getTime() / 1000 + " AND customerID IS NULL"
                    + " AND duration >= " + (duration * 60));

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
            System.out.println(e.getMessage());
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

    /**
     * Get all activities that this business have from database
     *
     * @return List of activities
     */
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
     * @param timeStart Start Time of the booking
     * @param timeFinish Finish time of the booking
     * @param userID User id for the booking
     * @param name Name of the person the booking is made for
     * @param address Address of the person the booking is made for
     * @param phoneNumber Phone Number of the person the booking is made for
     * @param activityId Activity id for the activity that user chooses
     * @return Whether the booking was successfully booked
     * @throws SQLException
     */
    public boolean book(Booking b, LocalDateTime timeStart, LocalDateTime timeFinish,
            int userID, String name,
            String address, String phoneNumber, int activityId) throws SQLException {

        System.out.println("START");

        /* If the user's booking duration is not fully used the booking duration,
         * will add one new available booking which use the left duration,
         * and the available booking before is deleted or updated.
         * The booked booking will add a new one into the database.
         */
        if (!b.getTimeStart().equals(timeStart)) {
            Bdb.iuQuery("UPDATE bookings SET"
                    + " timeFinish=" + (Timestamp.valueOf(timeStart).getTime() / 1000)
                    + " WHERE id=" + b.getId());
        } else {
            Bdb.iuQuery("DELETE FROM bookings WHERE id=" + b.getId());
        }

        Bdb.iuQuery("INSERT INTO bookings (employeeID, businessID, timeStart,"
                + " timeFinish, customerID, name, address, phoneNumber, activity) "
                + "VALUES(" + b.getEmployeeID()
                + ", " + b.getBusinessID()
                + ", " + (Timestamp.valueOf(timeStart).getTime() / 1000)
                + ", " + (Timestamp.valueOf(timeFinish).getTime() / 1000)
                + ", " + userID
                + ", '" + name + "'"
                + ", '" + address + "'"
                + ", '" + phoneNumber + "'"
                + ", " + activityId + ")");

        if (!b.getTimeFinish().equals(timeFinish)) {
            Bdb.iuQuery("INSERT INTO bookings (employeeID, businessID, timeStart,"
                    + " timeFinish) VALUES("
                    + b.getEmployeeID() + ", " + b.getBusinessID() + ", "
                    + (Timestamp.valueOf(timeFinish).getTime() / 1000) + ", "
                    + (Timestamp.valueOf(b.getTimeFinish()).getTime() / 1000) + ")");
        }

        /*boolean success = Bdb.iuQuery("UPDATE bookings SET customerID="
                + u.getID()
                + ", name='" + name + "'"
                + ", address='" + address + "'"
                + ", phonenumber='" + phoneNumber + "'"
                + ", activity='" + activityId + "'"
                + " WHERE id=" + b.getId());*/
        return true;
    }

    public ArrayList<Business> getBusiness() throws Exception {

        ArrayList<Business> tepBusiness = new ArrayList<Business>();

        try {
            ResultSet rs = Bdb.selectQuery("SELECT * FROM businesses ORDER BY id");

            if (rs.isClosed()) {
                // no activity
                return tepBusiness;
            }

            while (rs.next()) {

                Business business = new Business(rs.getInt("id"), rs.getString("username"),
                        rs.getString("password"));

                tepBusiness.add(business);

            }

            return tepBusiness;
        } catch (SQLException e) {
            return tepBusiness;
        }

    }

    /**
     * Business owner add an activity
     *
     * @param name Activity name
     * @param duration Activity duration
     * @return Whether the add action was successfully booked
     * @throws SQLException
     */
    public boolean addActivity(String name, int duration) throws SQLException {
        Bdb.iuQuery("INSERT INTO activity (name, duration, businessID) VALUES('"
                + name + "', " + duration + ", " + this.id + ")");
        return true;
    }

    /**
     * Business owner delete a booking
     *
     * @param b the booking is needed to be deleted
     * @return Whether the delete action was successfully booked
     * @throws SQLException
     */
    public boolean deleteBooking(Booking b) throws SQLException {
        boolean success = Bdb.iuQuery("DELETE from bookings WHERE id= " + b.getId());
        return success;
    }

    /**
     * Register a new business
     *
     * @param name Business Name
     * @param username Business Owner's Login Username
     * @param password Business Owner's Login Password
     * @param address Business Address
     * @param phoneNumber Business Phone Number
     * @param email Business Email Address
     * @return Registration success/failure
     * @throws SQLException
     * @throws Exception
     */
    public static boolean register(String name, String username,
            String password, String confirmPassword, String address,
            String phoneNumber, String email,
            int start, int close, String days) throws SQLException, Exception {

        //check if the user fill all the blanks.
        if (name.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")) {
            throw new Exception("Please fill all the blanks. ");
        }
        InputCheck.check.checkEmail(email);
        InputCheck.check.checkPassword(password);
        InputCheck.check.checkPassword(confirmPassword);
        InputCheck.check.checkShortName(name);
        InputCheck.check.checkLong(address);
        InputCheck.check.checkPhone(phoneNumber);

        boolean success = false;

        ResultSet rs = Bdb.selectQuery("SELECT * from businesses WHERE "
                + "username='" + username + "'");

        if (!rs.isClosed()) {
            throw new Exception("Username already taken");
        }

        rs = Bdb.selectQuery("SELECT * from customers WHERE "
                + "username='" + username + "'");

        if (!rs.isClosed()) {
            throw new Exception("Username already taken");
        }
        if (!password.equals(confirmPassword)) {
            throw new Exception("The password doesn't match! Please re-enter them!");
        }

        success = Bdb.iuQuery("INSERT INTO businesses (name, username,"
                + " password, address, phonenumber, email, start, close, days) VALUES ("
                + "'" + name + "', "
                + "'" + username + "', "
                + "'" + password + "', "
                + "'" + address + "', "
                + "'" + phoneNumber + "', "
                + "'" + email + "', "
                + start + ", "
                + close + ", "
                + "'" + days + "')");

        return success;
    }
    
    public static boolean[] decodeDaysString(String daysString) {
        boolean[] days = new boolean[7];
        String[] spl = daysString.split(",");
        
        for (int i = 0; i < 7; i++) {
            days[i] = spl[i].equals("1");
        }
        
        return days;
    }

}
