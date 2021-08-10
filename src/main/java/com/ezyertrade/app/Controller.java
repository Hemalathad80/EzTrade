package com.ezyertrade.app;

import com.ezyertrade.database.CustomerManagement;
import com.ezyertrade.database.StockManagement;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    static String emailId;

    public static void welcomeMessage() {
        System.out.println("***************************************************** \n Welcome to EzyerTrade online stock management system\n ****************************************************\n");
        System.out.println("Are you an existing customer? Please enter 1 \nAre you a new customer? Please enter 2");

    }

    public static void askExistingOrNewUser() throws SQLException {
        //getting input from the customer
        Scanner scanner = new Scanner(System.in);
        String customerChoice = scanner.next();
        while (!Utility.isValidExistingOrNewCustomerInput(customerChoice)) {
            System.out.println("Please enter 1 for buy or 2 for sell \n");
            customerChoice = scanner.next();
        }
        // log in the customer
        if (customerChoice.equals("1")) {
            existingCustomer();
        } else if (customerChoice.equals("2")) {
            newCustomer();
            System.out.println("Please enter your details\n");
            existingCustomer();
        }
    }

    public static void existingCustomer() {

        String emailId = getEmail(System.in);
        String pwd = getPassword(System.in);

        CustomerManagement cmObj = new CustomerManagement();
        Customers cObj = cmObj.readCustomerDetails(emailId);

        if (cObj == null) {
            System.out.println("Your email Id is not in our records. Please enter your correct email Id\n");
            existingCustomer();
        }


    }

    public static void newCustomer() throws SQLException {

        CustomerManagement cmObj = new CustomerManagement();
        //cmObj.createCustomerManagementTable();
        System.out.println("Please Sign up");
        System.out.println("Enter your name: ");
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.next();

        String emailId = getEmail(System.in);

        String password = getPassword(System.in);

        System.out.println("You are successfully signed up \n 😊😊😊😊😊😊😊😊😊😊😊😊😊 \n");
        cmObj.insertCustomerRecord(emailId, password);


    }

    public static void askBuyOrSell() throws InterruptedException, SQLException {
        System.out.println("Choose one option: Buy or Sell");
        Scanner buyOrSellInput = new Scanner(System.in);
        String buyOrSell = buyOrSellInput.next();

        while (!Utility.isValidBuyOrSellInput(buyOrSell)) {
            System.out.println("Please enter buy or sell");
            buyOrSell = buyOrSellInput.next();

        }
        if (buyOrSell.equalsIgnoreCase("Buy")) {
            buy();

        } else if (buyOrSell.equalsIgnoreCase("Sell")) {
            sell();

        }

    }


    public static void buy() throws InterruptedException, SQLException {

        System.out.println("List of companies : StockNo -- Name --Performance -- Price -- Quantity  \n" + Inventory.stockDisplay().toString());
        System.out.println("Choose the stock number from 1 to 12");
        Scanner scanner = new Scanner(System.in);
        int stockNumberFromUser = scanner.nextInt();

        System.out.println("How many stocks do you want to buy? (Look for the last column for quantity)");
        Scanner input = new Scanner(System.in);
        int requestedQuantity = input.nextInt();

        while (Inventory.getStockDetails().get(stockNumberFromUser - 1).getNumberAvailable() < requestedQuantity) {
            System.out.println("Choose the quantity less than the available number" + Inventory.getStockDetails().get(stockNumberFromUser).getNumberAvailable());
            requestedQuantity = input.nextInt();

        }
        getBrokerageAccountNumber();

        afterBuyOrSell();

        StockManagement stkObj = new StockManagement();
        //stkObj.createStockManagementTable();

        StockDetails sdObj = Inventory.getStockDetails().get(stockNumberFromUser - 1);

        //Database changes
        CustomerStockDetails csdObj = new CustomerStockDetails();
        csdObj.setEmailId(emailId);
        csdObj.setCompanyName(sdObj.getCompanyName());
        csdObj.setPerformance(sdObj.getPerformance());
        csdObj.setPurchasePrice(sdObj.getPrice());
        csdObj.setCurrentMarketPrice(sdObj.getPrice());
        csdObj.setQuantity(requestedQuantity);

        List<CustomerStockDetails> existingStockDetails = stkObj.readStockDetails(emailId);
        boolean checkTheRecordAlreadyExist = false;
        int existingQuantity = 0;

        for (CustomerStockDetails stkDetails : existingStockDetails) {
            if (stkDetails.getCompanyName().equalsIgnoreCase(sdObj.getCompanyName())) {
                checkTheRecordAlreadyExist = true;
                existingQuantity = stkDetails.getQuantity();
            }
        }
        if (checkTheRecordAlreadyExist) {
            stkObj.updateStockRecord(emailId, sdObj.getCompanyName(), existingQuantity + requestedQuantity);
        } else {
            stkObj.insertStockRecord(csdObj);
        }


        //update the number of shares
        int numberDifference = sdObj.getNumberAvailable() - requestedQuantity;
        sdObj.setNumberAvailable(numberDifference);

        askYesOrNoForAnotherTransaction();

    }

    public static void afterBuyOrSell() throws InterruptedException, SQLException {


        System.out.println("Transaction processing......\n ");

        Thread.sleep(2000);

        System.out.println("Transaction is successful \n🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 🤝 ");
        // System.out.println("Transaction report will be sent to your email Id");

    }


    public static void sell() throws InterruptedException, SQLException {
        //Displays your list of stocks from the database
        System.out.println("Displaying your list of stocks: StockNo -- Name -- current market price  -- Performance -- Quantity Available");
        List<CustomerStockDetails> csdObj = new StockManagement().readStockDetails(emailId);
        int counter = 1;
        for (CustomerStockDetails c : csdObj) {

            System.out.println(counter++ + "  " + c.toString());
        }
        System.out.println("Choose the stock number to sell from the left most side");

        Scanner sellIn = new Scanner(System.in);
        int sellInput = sellIn.nextInt();

        while (!(sellInput <= csdObj.size())) {
            System.out.println("Choose the correct stock number to sell  ( Choose from left most column)");

            sellInput = sellIn.nextInt();
        }
        System.out.println("How many stocks do you want to sell?  ( Choose from right most column)");
        Scanner input = new Scanner(System.in);
        int quantity = input.nextInt();


        while ((csdObj.get(sellInput - 1).getQuantity() < quantity)) {
            System.out.println("Choose the quantity less than the available number" + csdObj.get(sellInput - 1).getQuantity());

            quantity = input.nextInt();


        }

        getBrokerageAccountNumber();

        afterBuyOrSell();

        //update database after sell
        int newQuantity = csdObj.get(sellInput - 1).getQuantity() - quantity;
        new StockManagement().updateStockRecord(emailId, csdObj.get(sellInput - 1).getCompanyName(), newQuantity);

        askYesOrNoForAnotherTransaction();
    }

    //Get customer's online brokerage account number
    public static void getBrokerageAccountNumber() {
        System.out.println("Enter your brokerage account number   ( 8 digits only)");
        Scanner no = new Scanner(System.in);
        String number = no.next();
        while (!Utility.isValidAccountNumber(number)) {
            System.out.println("Please enter the valid 8 digit only account number");
            number = no.next();
        }

    }

    public static void askYesOrNoForAnotherTransaction() throws SQLException, InterruptedException {

        System.out.println("Do you want continue with another transaction. Please type Yes or No ");
        Scanner i = new Scanner(System.in);
        if (i.hasNext("yes")) {
            askBuyOrSell();
        } else if (i.hasNext("no")) {
            System.out.println("Thanks for choosing us!\n" + "\n🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏\n" + "Have a great fortune \n 👍👍👍👍👍👍👍👍👍👍👍");
        }

    }

    public static String getEmail(InputStream inputstream) {
        emailId = "no id";
        System.out.println("Log in using your credentials");
        System.out.println("Email Id( Example format:  xxxx@yyyyy.com) :  ");
        Scanner in = new Scanner(inputstream);
        emailId = in.next();
        int attempt = 1;
        while (!Utility.isValidEmail(emailId)) {
            System.out.println("Email is not valid. Please provide a valid email id");


            if (attempt == 3) {
                System.out.println("You have not provided a valid email. So try again later.");
                System.exit(0);
            }
            attempt++;
            System.out.println("Email Id : ");
            emailId = in.next();

        }

        return emailId;
    }

    public static String getPassword(InputStream inputstream) {
        System.out.println("Password (Minimum - 8 char,one digit, one lower and one upper case char, one special char,should not contain space,tab etc.): ");
        Scanner password = new Scanner(inputstream);
        String pwd = password.next();

        int attempt = 1;
        while (!Utility.isValidPassword(pwd)) {

            System.out.println("Password is not valid. Please provide a valid password");

            System.out.println("At least 8 chars\n Contains at least one digit\n Contains at least one lower alpha char and one upper alpha char\n Contains at least one char within a set of special chars (@#%$^ etc.)\n Does not contain space, tab, etc.");
            if (attempt == 3) {
                System.out.println("You have not provided a valid password. So try again later.");
            System.exit(0);

            }
            attempt++;
            System.out.println("Password : ");
            pwd = password.next();

        }

        return pwd;
    }


}








