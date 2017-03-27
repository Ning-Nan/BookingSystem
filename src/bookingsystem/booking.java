/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import static bookingsystem.user.roleType.customer;
import java.io.*;
import java.util.*;

/**
 *
 * @author User
 */
public class booking {
    //formateDate means String type date
    private String formateDate;
    private Date date;
    private String worker;
    private String username;
    private String bussiness;
    private String phoneNumber;
    private String address;
    //use list to store the appointments that the current user has
    List bookingList = new ArrayList();

    public booking(user User) throws FileNotFoundException,
            Exception {
        //by now we focus on single business
        if (User.getRole() == customer) {
            BufferedReader reader1 = new BufferedReader(new FileReader(utils.CARSERVICE));

            String line;

            while ((line = reader1.readLine()) != null) {

                StringTokenizer strtok = new StringTokenizer(line, "|", false);
                String tok;
                String usernameTmp;
                usernameTmp = strtok.nextToken();

                if (usernameTmp.equals(User.getUsername())) {
                    booking tepBooking = new booking();
                    tepBooking.username = usernameTmp;
                    tepBooking.worker = strtok.nextToken();
                    tepBooking.formateDate = strtok.nextToken();
                    tepBooking.phoneNumber = strtok.nextToken();
                    tepBooking.bussiness = "Car Service";
                    tepBooking.address = strtok.nextToken();
                    bookingList.add(tepBooking);
                }

            }

            reader1.close();
        }

    }

    private booking() {

    }

    public String getFormateDate() {
        return formateDate;
    }

    public Date getDate() {
        return date;
    }

    public String getWorker() {
        return worker;
    }

    public String getUsername() {
        return username;
    }

    public String getBussiness() {
        return bussiness;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

}
