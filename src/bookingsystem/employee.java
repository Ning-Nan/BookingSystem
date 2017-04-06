/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

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
    
}
