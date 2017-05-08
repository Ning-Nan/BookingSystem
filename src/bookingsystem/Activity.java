/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

/**
 * Java representation of an Activity database item.
 * Caution should be used to make sure an Activity object always
 * represents an actual database item.
 */
public class Activity {
    private int id;
    private int businessID;
    private int duration;
    private String name;
    
    
    /**
     * Create an activity object with the given parameters.
     * @param id Activity's id from the database
     * @param businessID businessID from the database
     * @param name Activity's name from the database
     * @param duration Activity's duration from the database
     */
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
