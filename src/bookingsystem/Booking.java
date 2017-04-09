package bookingsystem;

import static bookingsystem.User.roleType.customer;
import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Java representation of a booking database item.
 * Caution should be used to make sure a Booking object always
 * represents an actual database item.
 */
public class Booking {
    private int id;
    private int businessID;
    private int employeeID;
    private int customerID;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    private String name;
    private String address;
    private String phoneNumber;
    
    /**
     * Create a booking object with the provided parameters
     * @param id Booking id from booking table
     * @param businessID Business id from booking table
     * @param employeeID Employee id from booking table
     * @param customerID customer id from booking table
     * @param timeStart timeStart from booking table
     * @param timeFinish timeFinish from booking table
     * @param name name from booking table
     * @param address address from booking table
     * @param phoneNumber phoneNumber from booking table
     */
    public Booking(int id, int businessID, int employeeID, int customerID, long timeStart,
            long timeFinish, String name, String address, String phoneNumber) {

        this.id = id;
        this.businessID = businessID;
        this.employeeID = employeeID;
        this.customerID = customerID;
        /*
            Timestamps are stored as unix timestamps (seconds),
            Java uses milliseconds. (Divide/Multiply by 1000).
        */
        this.timeStart = (new Timestamp(timeStart * 1000).toLocalDateTime());
        this.timeFinish = (new Timestamp(timeFinish * 1000).toLocalDateTime());
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        
    }
    
    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }
    
    public LocalDateTime getTimeFinish() {
        return this.timeFinish;
    }
    
    public int getEmployeeID() {
        return this.employeeID;
    }

    public int getId() {
        return id;
    }

    public int getBusinessID() {
        return businessID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    
}
