/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class employee {
    private int id;
    private int businessID;
    private String name;
    
    public employee(int id, int businessID, String name) {
        this.id = id;
        this.businessID = businessID;
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public int getBusinessID() {
        return businessID;
    }
    public ArrayList<booking> getEmployeeAvailability() throws SQLException{
    
        ArrayList<booking> bookings = new ArrayList<booking>();
        
        ResultSet rs = bdb.selectQuery("SELECT * from bookings WHERE employeeID = "
        +"'"+ this.id +"'" +" AND businessID = " + "'" + this.businessID +"'" +
                " ORDER BY timeStart ASC ");
        
        if(rs.isClosed()){
          
            return bookings;
        }
        
        while(rs.next()){
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
        
        
    }
}
