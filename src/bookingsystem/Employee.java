package bookingsystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Employee {
    private int id;
    private int businessID;
    private String name;
    
    public Employee(int id, int businessID, String name) {
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
    public ArrayList<Booking> getEmployeeAvailability() throws SQLException{
        
        Date now = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(now.toInstant(),
                ZoneId.systemDefault());
        Timestamp tldt = Timestamp.valueOf(ldt);
        Timestamp tldtPlusSevenDay = Timestamp.valueOf(ldt.plusDays(7));
        
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        
        ResultSet rs = Bdb.selectQuery("SELECT * from bookings WHERE employeeID = "
        +"'"+ this.id +"'" +" AND businessID = " + "'" + this.businessID +"'" +
                " ORDER BY timeStart ASC ");
        
        if(rs.isClosed()){
          
            return bookings;
        }
        
        while(rs.next()){
            
            if(rs.getLong("timeStart") < tldt.getTime()/1000 || rs.getLong("timeStart") > 
                    tldtPlusSevenDay.getTime()/1000){
                continue;
            }
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
        
        
    }
}
