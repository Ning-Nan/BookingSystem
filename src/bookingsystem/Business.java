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

public class Business {
    
    public static Business currBusiness;
    
    private int id;
    private String name;
    private String address;
    private String username;
    private String password;
    private String phoneNumber;
    
    public Business(int id, String username, String password)
            throws SQLException, Exception {
        ResultSet rs = Bdb.selectQuery("SELECT * from businesses WHERE id=" +
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
            Bdb.iuQuery("INSERT INTO employees (businessID, name) " +
                    "VALUES (" +
                    this.id + ", " +
                    "'" + name + "')");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public Employee getEmployee(int employeeID) {
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from employees WHERE " +
                    "businessID=" + this.id + " AND id=" + employeeID);
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
    
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList();
        
        ResultSet rs;
        
        try {
        rs = Bdb.selectQuery("SELECT * from employees WHERE businessID=" +
                this.id + " ORDER BY id");
        
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
    
    public ArrayList<Booking> getABookingsFromDate(Date d) 
    {
        
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        
        LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(),
                ZoneId.systemDefault());
        Timestamp tldt = Timestamp.valueOf(ldt);
        Timestamp tldtPlusOneDay = Timestamp.valueOf(ldt.plusDays(1));
        
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE " +
                    "businessID=" + this.id + " AND timeStart > " +
                    tldt.getTime()/1000 + " AND timeStart < " +
                    tldtPlusOneDay.getTime()/1000 + " AND customerID IS NULL");
            
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
            
            return bookings;
        } catch (SQLException e) {
            return bookings;
        }
    }
    
    public boolean createOpenBooking(Employee em, LocalDateTime timeStart,
            LocalDateTime timeFinish) {
        
        Timestamp startTimestamp = Timestamp.valueOf(timeStart);
        Timestamp finishTimestamp = Timestamp.valueOf(timeFinish);
        
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE " +
                "employeeID=" + em.getId() + " AND timeStart <= "
                    + (startTimestamp.getTime()/1000) 
                    + " AND timeFinish >= " + (finishTimestamp.getTime()/1000));
            
            if (!rs.isClosed()) {
                return false;
            }
            
            rs = Bdb.selectQuery("SELECT * from bookings WHERE " +
                    "employeeID=" + em.getId() + " AND timeStart < "
                    + (finishTimestamp.getTime()/1000)
                    + " AND timeFinish > " + (finishTimestamp.getTime()/1000));
                    
            if (!rs.isClosed()) {
                return false;
            }
            
            boolean success = Bdb.iuQuery("INSERT INTO bookings (employeeID," +
                    " businessID, timeStart, timeFinish) VALUES (" +
                    em.getId() + ", " + this.id + ", "
                    + startTimestamp.getTime()/1000 + ", " +
                    finishTimestamp.getTime()/1000 + ")");
            
            return success;    
            
        } catch (SQLException e) {
            return false;
        }
  
    }
    
    public ArrayList<Booking> getAllBooking()
    {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        
        try {
            ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE " +
                    "businessID=" + this.id + " AND employeeID" + " AND timeStart" + " AND timeFinish" + " ORDER BY timeStart ASC");
            
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
            
            return bookings;
        } catch (SQLException e) {
            return bookings;
        }
    }
    
    
}
