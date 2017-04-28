/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

/**
 *
 * @author User
 */
public class Activity {
    private int id;
    private int businessID;
    private int duration;
    private String name;
    
    public Activity(int id, int businessID, String name, int duration){
        this.id = id;
        this.businessID = businessID;
        this.name = name;
        this.duration = duration;
    
    }

    public int getId() {
        return id;
    }

    public int getBusinessID() {
        return businessID;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
