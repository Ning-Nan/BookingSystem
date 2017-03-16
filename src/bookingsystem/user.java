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
    
      public user(String name, String username, String password, String confirmPassword, String address, String phoneNumber)throws IOException,
           Exception{
       
       //check if the user fill all the blanks.
       if(name == null || username == null || password == null ||confirmPassword == null|| address == null || phoneNumber == null){
           throw new Exception("Please fill all the blanks. ");
       }
      /* 
       if(username.length() <6 || username.length()>12 || password.length()<6 || password.length()>12|| phoneNumber.length()!= 10){
           throw new Exception("the lenth of username and password should be between 6 and 12. The length of phone number should be 10!");
       }*/
       
       //read from file to check the repeat username
       BufferedReader reader = 
               new BufferedReader(new FileReader(utils.CUSTOMERINFOFILENAME));
       
       String line;
       
       while((line = reader.readLine())!= null){
        StringTokenizer strtok = new StringTokenizer(line, "|", false);
        String tok;
        String usernameTmp;
        strtok.nextToken();
        usernameTmp = strtok.nextToken();
        if(usernameTmp.equals(username)){
            throw new Exception("ALready exist username! ");
        }
       }
       
       reader.close();
       //check are two passwords same.
       if(!(password.equals(confirmPassword))){
           throw new Exception("passwords not match!");
       }
           
      FileWriter fw = new FileWriter(utils.CUSTOMERINFOFILENAME,true);
      
      BufferedWriter bufw = new BufferedWriter(fw);
      bufw.newLine();
      bufw.write(name+"|"+username+"|"+password+"|"+address+"|"+phoneNumber);
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
    
}
