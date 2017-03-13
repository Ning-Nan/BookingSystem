/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.*;
import java.util.StringTokenizer;

public class user {
    
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    
    public enum roleType {
        customer, owner
    }
    
    private roleType role;
    
    // Instance user from existing user
    public user(String username, String password) throws FileNotFoundException,
            Exception {
        
        Boolean authenticated = false;
        
        BufferedReader reader =
                new BufferedReader(new FileReader(utils.CUSTOMERINFOFILENAME));
        
        // Each customer is on a new line
        String line;
        // Read until the end of the file
        while ((line = reader.readLine()) != null) {
            // Customer data seperated by commas
            StringTokenizer strtok = new StringTokenizer(line, "|", false);
            String tok;
            String nameTmp, usernameTmp;
            nameTmp = strtok.nextToken();
            usernameTmp = strtok.nextToken();
            if (usernameTmp.equals(username)) {
                tok = strtok.nextToken();
                if (tok.equals(password)) {
                    // Found correct user.
                    authenticated = true;
                    this.name = nameTmp;
                    this.username = usernameTmp;
                    this.password = tok;
                    tok = strtok.nextToken();
                    this.address = tok;
                    tok = strtok.nextToken();
                    this.phoneNumber = tok;
                    this.role = roleType.customer;
                } else {
                    throw new Exception("Incorrect password");
                }
            }
        }
        
        reader.close();
        
        if (authenticated == false) {
            throw new Exception("Invalid username");
        }
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
}
