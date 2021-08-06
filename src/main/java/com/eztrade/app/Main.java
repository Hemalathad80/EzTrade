package com.eztrade.app;

import com.eztrade.app.Controller;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        //Welcome message
        Controller.welcomeMessage();

        //getting input from the customer
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();

        // log in the customer
        if (userName.equalsIgnoreCase("Existing")) {
            Controller.existingCustomer();
        } else if (userName.equalsIgnoreCase("New")) {
            Controller.newCustomer();
            System.out.println("Re enter your details");
            Controller.existingCustomer();
        }
        /*else{
                throw new Error("Error. Please give the correct details.");
            }*/


        //Asking the option buy or sell
        Controller.askBuyOrSell();

        System.out.println("Do you want continue with another transaction");
        Scanner in = new Scanner(System.in);
        if (in.hasNext("yes")) {
            Controller.askBuyOrSell();
        } else if (in.hasNext("no"))
            System.out.println("Thanks for choosing us!" + "\n" + "Have a great fortune");
    }
}
