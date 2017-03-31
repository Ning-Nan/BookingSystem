/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
}
