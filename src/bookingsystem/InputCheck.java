/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.util.regex.*;

/**
 * A class contains functions to check user input.
 *
 */
public class InputCheck {

    public static InputCheck check;
    private String error;

    /**
     * Check username input.
     *
     * @param username username input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkUserName(String username) throws Exception {
        String check1 = "^[a-zA-Z]\\w{6,12}$";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(username);

        boolean isMatched = matcher.matches();

        if (!isMatched) {
            throw new Exception("Username is in wrong format");
        }
        return true;

    }

    /**
     * Check password input.
     *
     * @param password password input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkPassword(String password) throws Exception {
        String check1 = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(password);

        boolean isMatched = matcher.matches();

        if (!isMatched) {
            throw new Exception("Password is in wrong format");
        }
        return true;

    }

    /**
     * Check short name input.
     *
     * @param name name input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkShortName(String name) throws Exception {

        String check1 = "^([A-Z][A-Za-z ,.'`-]{3,30})$";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(name);

        boolean isMatched = matcher.matches();

        if (!isMatched) {
            throw new Exception("Name is in wrong format");
        }
        return true;

    }

    /**
     * Check long string input.
     *
     * @param address address input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkLong(String address) throws Exception {
        /*String check1 = "^[A-Za-z0-9]\\w{2,40}$";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(address);

        boolean isMatched = matcher.matches();

        System.out.println(!isMatched);

        if (isMatched == false) {
            throw new Exception("Address is in wrong format");
        }*/

        return true;
    }

    /**
     * Check email input.
     *
     * @param email email input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkEmail(String email) throws Exception {
        String check1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(email);

        boolean isMatched = matcher.matches();

        System.out.println(!isMatched);

        if (isMatched == false) {
            throw new Exception("Email is in wrong format");
        }
        return true;

    }

    /**
     * Check phone no. input.
     *
     * @param phone phone number input
     * @throws Exception
     * @return success or not for checking input
     */
    public boolean checkPhone(String phone) throws Exception {

        String check1 = "^\\d{10}$";

        Pattern regex = Pattern.compile(check1);

        Matcher matcher = regex.matcher(phone);

        boolean isMatched = matcher.matches();

        if (isMatched == false) {
            throw new Exception("PhoneNo. is in wrong format");
        }
        return true;

    }

    public String getError() {
        return error;
    }

}
