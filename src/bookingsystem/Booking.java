/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import static bookingsystem.User.roleType.customer;
import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    
    public Booking(int id, int businessID, int employeeID, int customerID, long timeStart,
            long timeFinish, String name, String address, String phoneNumber) {

        this.id = id;
        this.businessID = businessID;
        this.employeeID = employeeID;
        this.customerID = customerID;
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
