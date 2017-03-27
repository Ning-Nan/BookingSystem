/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.*;
import java.util.StringTokenizer;
import java.sql.SQLException;
import java.sql.ResultSet;

public class user {

    private int id;
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String ownerName;

    public enum roleType {
        customer, owner
    }

    private roleType role;

    // Instance user from existing user
    public user(String username, String password) throws SQLException,
            Exception {
        
        ResultSet rs = bdb.selectQuery(
                "SELECT * from customers WHERE username='" + username +
                "'");
        
        this.role = roleType.customer;
        
        if (rs.isClosed()) {
            rs = bdb.selectQuery(
            "SELECT * from businesses WHERE username='" + username +
                    "'");
            
            this.role = roleType.owner;
            
            if (rs.isClosed()) {
                throw new Exception("Invalid username");
            }
        }
        
        if (!rs.getString("password").equals(password)) {
            throw new Exception("Invalid password");
        }
        
        this.id = rs.getInt("id");
        this.name = rs.getString("name");
        this.phoneNumber = rs.getString("phonenumber");
        this.address = rs.getString("address");
        this.username = username;
        this.password = password;
    }

    public user(String name, String username, String password, String confirmPassword, String address, String phoneNumber) throws IOException,
            Exception {

        //check if the user fill all the blanks.
        if (name.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("") || address.equals("") || phoneNumber.equals("")) {
            throw new Exception("Please fill all the blanks. ");
        }
       

        //read from file to check the repeat username
        BufferedReader reader
                = new BufferedReader(new FileReader(utils.CUSTOMERINFOFILENAME));

        String line;

        while ((line = reader.readLine()) != null) {
            StringTokenizer strtok = new StringTokenizer(line, "|", false);
            String tok;
            String usernameTmp;
            strtok.nextToken();
            usernameTmp = strtok.nextToken();
            if (usernameTmp.equals(username)) {
                throw new Exception("ALready exist username! ");
            }
        }

        reader.close();
        //check are two passwords same.
        if (!(password.equals(confirmPassword))) {
            throw new Exception("passwords not match!");
        }

        FileWriter fw = new FileWriter(utils.CUSTOMERINFOFILENAME, true);

        BufferedWriter bufw = new BufferedWriter(fw);
        bufw.newLine();
        bufw.write(name + "|" + username + "|" + password + "|" + address + "|" + phoneNumber);
        bufw.flush();
        bufw.close();

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

    public roleType getRole() {
        return role;
    }

    public String getOwnerName() {
        return ownerName;
    }

}
