package bookingsystem;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Java representation of a customer database item.
 * Caution should be used to make sure a User object always
 * represents an actual database item.
 */
public class User {

    public static User currUser;
    private int id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String ownerName;
    private String email;

    public enum roleType {
        customer, owner
    }

    private roleType role;

    /**
     * Instance a user from an existing user in the database.
     * @param username User's username
     * @param password User's password
     * @throws SQLException
     * @throws Exception 
     */
    public User(String username, String password) throws SQLException,
            Exception {
        
        // Check the customers table to find the user.
        ResultSet rs = Bdb.selectQuery(
                "SELECT * from customers WHERE username='" + username
                + "'");

        this.role = roleType.customer;

        if (rs.isClosed()) {
            // User wasn't a customer, check if they are a business owner.
            rs = Bdb.selectQuery(
                    "SELECT * from businesses WHERE username='" + username
                    + "'");

            this.role = roleType.owner;

            if (rs.isClosed()) {
                // User isn't a customer or business owner.
                throw new Exception("Invalid username");
            }
        }

        if (!rs.getString("password").equals(password)) {
            throw new Exception("Invalid password");
        }

        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.phoneNumber = rs.getString("phonenumber");
        this.address = rs.getString("address");
        this.username = username;
        this.password = password;
        this.email = rs.getString("email");
    }
    
    /**
     * Create a new user
     * @param name Customer name
     * @param username Customer username
     * @param password Customer password
     * @param confirmPassword Customer confirmPassword
     * @param address Customer address
     * @param phoneNumber Customer phone number
     * @param email Customer email address
     * @throws IOException
     * @throws Exception 
     */
    public User(String name, String username, String password, String confirmPassword, String address, String phoneNumber, String email) throws Exception {

        //check if the user fill all the blanks.
        if (name.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")) {
            throw new Exception("Please fill all the blanks. ");
        }
        
       InputCheck.check.checkEmail(email);
       InputCheck.check.checkPassword(password);
       InputCheck.check.checkShortName(name);
       InputCheck.check.checkLong(address);
       InputCheck.check.checkPhone(phoneNumber);

        ResultSet rs = Bdb.selectQuery("Select * from businesses WHERE username = '" + username + "'");
       if (!rs.isClosed()) {
            throw new Exception("ALready exist username! Please try another!");
        }
        
         rs = Bdb.selectQuery("Select * from  customers WHERE username = '" + username + "'");
        if (!rs.isClosed()) {
            throw new Exception("ALready exist username! Please try another!");
        }
        
        if(!password.equals(confirmPassword)){
        throw new Exception("The password doesn't match! Please re-enter them!");}

        Bdb.iuQuery("INSERT INTO customers (username, password, name, address, phonenumber, email) VALUES ("+"'"
                + username + "', '" + password + "', '" + name + "', '" + address + "', '" + phoneNumber + "', '" + email + "')");

    }
    
    public User(String username) throws SQLException,
            Exception {
        
        // Check the customers table to find the user.
        ResultSet rs = Bdb.selectQuery(
                "SELECT * from customers WHERE username='" + username
                + "'");

        this.role = roleType.customer;

        if (rs.isClosed()) {
            throw new Exception("Invalid username");
        }

        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.phoneNumber = rs.getString("phonenumber");
        this.address = rs.getString("address");
        this.username = username;
        this.password = password;
        this.email = rs.getString("email");
    }
    
    /**
     * Get all a customer's bookings for a business sorted by time.
     * @param b The business that has the customer's bookings
     * @return ArrayList of Bookings
     */
    public ArrayList<Booking> getSortedBookings(Business b) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        
        try {
            String query = "SELECT * from bookings WHERE "
                    + "customerID=" + this.id + " AND businessID=" + b.getID()
            + " ORDER BY timeStart ASC";
            System.out.println(query);
            ResultSet rs = Bdb.selectQuery(query);
            
            if (rs.isClosed()) {
                return bookings;
            }
            
            while(rs.next()) {
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
            
            
        } catch (SQLException e) {
            return bookings;
        }
        
        return bookings;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public roleType getRole() {
        return role;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getEmail() {
        return email;
    }
    
    public int getID() {
        return id;
    }
    
    /**
     * Get a user ID from their username from the database.
     * @param username
     * @return User ID
     */
    public static int getIDfromUsername(String username) {
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from customers WHERE " +
                    "username = '" + username + "'");
            if (rs.isClosed()) {
                return 0;
            }
            return rs.getInt("id");
        } catch (SQLException e) {
            return 0;
        }
    }

}
