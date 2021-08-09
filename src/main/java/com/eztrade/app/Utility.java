package com.eztrade.app;

import java.util.Scanner;

public class Utility {

    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {

        String PASSWORD_REGEX = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

        return password.matches(PASSWORD_REGEX);

    }

    public static boolean isValidAccountNumber(String accNo) {
        String NUMBER_REGEX = "\\W*\\d{8}\\b";

        return accNo.matches(NUMBER_REGEX);
    }

    public static boolean isValidExistingOrNewCustomerInput(String in) {
        String NUMBER_REGEX = "\\W*\\d{1}\\b";
        return in.matches(NUMBER_REGEX);
    }

    public static boolean isValidBuyOrSellInput(String in) {
        return (in.equalsIgnoreCase("buy") || in.equalsIgnoreCase("sell"));
    }


}
