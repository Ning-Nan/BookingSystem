/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class user {

    public static user currUser;
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

    // Instance user from existing user
    public user(String username, String password) throws SQLException,
            Exception {

        ResultSet rs = bdb.selectQuery(
                "SELECT * from customers WHERE username='" + username
                + "'");

        this.role = roleType.customer;

        if (rs.isClosed()) {
            rs = bdb.selectQuery(
                    "SELECT * from businesses WHERE username='" + username
                    + "'");

            this.role = roleType.owner;

            if (rs.isClosed()) {
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

    public user(String name, String username, String password, String confirmPassword, String address, String phoneNumber, String email) throws IOException,
            Exception {

        //check if the user fill all the blanks.
        if (name.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || address.equals("") || phoneNumber.equals("") || email.equals("")) {
            throw new Exception("Please fill all the blanks. ");
        }

        ResultSet rs = bdb.selectQuery("Select * from businesses WHERE username = '" + username + "'");
       if (!rs.isClosed()) {
            throw new Exception("ALready exist username! Please try another!");
        }
        
         rs = bdb.selectQuery("Select * from  customers WHERE username = '" + username + "'");
        if (!rs.isClosed()) {
            throw new Exception("ALready exist username! Please try another!");
        }
        
        if(!password.equals(confirmPassword)){
        throw new Exception("The password doesn't match! Please re-enter them!");}

        bdb.iuQuery("INSERT INTO customers (username, password, name, address, phonenumber, email) VALUES ("+"'"
                + username + "', '" + password + "', '" + name + "', '" + address + "', '" + phoneNumber + "', '" + email + "')");

    }
    
    public ArrayList<booking> getSortedBookings(business b) {
        ArrayList<booking> bookings = new ArrayList<booking>();
        
        try {
            String query = "SELECT * from bookings WHERE "
                    + "customerID=" + this.id + " AND businessID=" + b.getID()
            + " ORDER BY timeStart ASC";
            System.out.println(query);
            ResultSet rs = bdb.selectQuery(query);
            
            if (rs.isClosed()) {
                return bookings;
            }
            
            while(rs.next()) {
                booking tmpBooking = new booking(rs.getInt("id"),
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

}
