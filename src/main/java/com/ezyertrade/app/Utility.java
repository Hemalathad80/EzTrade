package com.ezyertrade.app;

public class Utility {

    //Validates the email command line input
    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    //Validates the password command line input
    public static boolean isValidPassword(String password) {

        String PASSWORD_REGEX = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

        return password.matches(PASSWORD_REGEX);

    }

    //Validates the account number command line input
    public static boolean isValidAccountNumber(String accNo) {
        String NUMBER_REGEX = "\\W*\\d{8}\\b";

        return accNo.matches(NUMBER_REGEX);
    }

    //Validates the existing or new user command line input
    public static boolean isValidExistingOrNewCustomerInput(String in) {
        String NUMBER_REGEX = "\\W*\\d{1}\\b";
        return in.matches(NUMBER_REGEX);
    }

    //Validates buy or sell command line input
    public static boolean isValidBuyOrSellInput(String in) {
        return (in.equalsIgnoreCase("buy") || in.equalsIgnoreCase("sell"));
    }


}
