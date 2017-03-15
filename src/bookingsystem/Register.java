/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.io.*;
import java.util.StringTokenizer;


public class Register {
    private String name;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    
   public Register(String name, String username, String password, String address, String phoneNumber)throws FileNotFoundException,
           Exception{
       
       //check if the user fill all the blanks.
       if(this.name == null || this.username == null || this.password == null || this.address == null || this.phoneNumber == null){
           throw new Exception("Please fill all the blanks. ");
       }
       
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
           
      
       
       
       
   }
}
