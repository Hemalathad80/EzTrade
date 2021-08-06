package com.eztrade.app;

import com.eztrade.app.Inventory;
import com.eztrade.app.StockDetails;

import java.util.Scanner;

public class Controller {

    public static void welcomeMessage() {
        System.out.println("Welcome to EzTrade online stock management system");
        System.out.println("Are you an existing customer or new customer?");

    }

    public static void existingCustomer() {

        System.out.println("Log in using your credentials");
        System.out.println("Email Id : ");
        Scanner in = new Scanner(System.in);
        String emailId = in.next();
        System.out.println("Password : ");
        Scanner password = new Scanner(System.in);
        String pwd = password.next();


    }

    public static void newCustomer() {
        System.out.println("Please Sign up");
        System.out.println("Enter your name: ");
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.next();
        System.out.println(" Enter your email id");
        Scanner emailIdInput = new Scanner(System.in);
        String emailId = emailIdInput.next();
        System.out.println("Enter your password");
        Scanner passwordInput = new Scanner(System.in);
        String password = passwordInput.next();
        System.out.println("You are successfully signed up");


    }

    public static void askBuyOrSell() throws InterruptedException {
        System.out.println("Choose one option: Buy or Sell");
        Scanner buyOrSellInput = new Scanner(System.in);
        String buyOrSell = buyOrSellInput.next();
        if (buyOrSell.equalsIgnoreCase("Buy")) {
            buy();

        } else if (buyOrSell.equalsIgnoreCase("Sell")) {
            sell();

        }
    }


    public static void buy() throws InterruptedException {
        System.out.println("List of companies  \n" + Inventory.stockDisplay().toString() + "\n");
        System.out.println("Choose one");
        Scanner scanner = new Scanner(System.in);
        String compName = scanner.nextLine();

        System.out.println("Do you want to proceed?");
        Scanner input = new Scanner(System.in);
        String in = input.next();
        if (in.equalsIgnoreCase("yes")) {
            System.out.println("Enter your brokerage account number");
            Scanner no = new Scanner(System.in);
            int number = no.nextInt();
            System.out.println("Transaction processing......");
            Thread.sleep(2000);
            System.out.println("Transaction is successful");

            //update the number of shares

            System.out.println("Transaction report");
            System.out.println("Transaction report will be sent to your email Id");

        }
    }



    public static void sell() throws InterruptedException {
        //Displays your list of stocks from the database
        System.out.println("Displaying your list of stocks with price at the time of purchase and current market price");
        System.out.println("Choose one to sell ");

        Scanner sellIn = new Scanner(System.in);

        String sellInput = sellIn.next();

        if (new StockDetails().getCompanyName().equals(sellInput)) {
            System.out.println("Enter your brokerage account number");
            Scanner no = new Scanner(System.in);
            int number = no.nextInt();
            System.out.println("Transaction processing......");
            Thread.sleep(2000);
            System.out.println("Transaction is successful");

            //update number of shares

            System.out.println("Transaction report");
            System.out.println("Transaction report will be sent to your email Id");


        }


    }

    public static void updateAfterSell() {


    }


}






