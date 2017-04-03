/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.ZoneId;

public class business {
    
    public static business currBusiness;
    
    private int id;
    private String name;
    private String address;
    private String username;
    private String password;
    private String phoneNumber;
    
    public business(int id, String username, String password)
            throws SQLException, Exception {
        ResultSet rs = bdb.selectQuery("SELECT * from businesses WHERE id=" +
                id);
        
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
    
    public boolean addEmployee(String name) {
        try {
            bdb.iuQuery("INSERT INTO employees (businessID, name) " +
                    "VALUES (" +
                    this.id + ", " +
                    "'" + name + "')");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public employee getEmployee(int employeeID) {
        try {
            ResultSet rs = bdb.selectQuery("SELECT * from employees WHERE " +
                    "businessID=" + this.id);
            if (rs.isClosed()) {
                return null;
            }
            employee em = new employee(rs.getInt("id"),
                        rs.getInt("businessID"), rs.getString("name"));
            return em;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public ArrayList<employee> getEmployees() {
        ArrayList<employee> employees = new ArrayList();
        
        ResultSet rs;
        
        try {
        rs = bdb.selectQuery("SELECT * from employees WHERE businessID=" +
                this.id);
        
            if (rs.isClosed()) {
                return employees;
            }
            
            while (rs.next()) {
                employee em = new employee(rs.getInt("id"),
                        rs.getInt("businessID"), rs.getString("name"));
                employees.add(em);
            }
            
        } catch (SQLException e) {
            return employees;
        }
        
        return employees;
        
    }
    
    public ArrayList<booking> getABookingsFromDate(Date d) {
        
        ArrayList<booking> bookings = new ArrayList<booking>();
        
        LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(),
                ZoneId.systemDefault());
        Timestamp tldt = Timestamp.valueOf(ldt);
        Timestamp tldtPlusOneDay = Timestamp.valueOf(ldt.plusDays(1));
        
        try {
            ResultSet rs = bdb.selectQuery("SELECT * from bookings WHERE " +
                    "businessID=" + this.id + " AND timeStart > " +
                    tldt.getTime()/1000 + " AND timeStart < " +
                    tldtPlusOneDay.getTime()/1000 + " AND customerID IS NULL");
            
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
            
            return bookings;
        } catch (SQLException e) {
            return bookings;
        }
    }
    
}
