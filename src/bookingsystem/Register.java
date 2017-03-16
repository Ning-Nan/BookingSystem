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
    private String confirmPassword;
   public Register(String name, String username, String password, String confirmPassword, String address, String phoneNumber)throws FileNotFoundException,
           Exception{
       
       //check if the user fill all the blanks.
       if(name == null || username == null || password == null ||confirmPassword == null|| address == null || phoneNumber == null){
           throw new Exception("Please fill all the blanks. ");
       }
       
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
       
       //check are two passwords same.
       if(!(password.equals(confirmPassword))){
           throw new Exception("passwords not match!");
       }
           
      
       
       
       
   }
}
