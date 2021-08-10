package com.ezyertrade.app;

import com.ezyertrade.database.CustomerManagement;
import com.ezyertrade.database.StockManagement;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    static String emailId;

    //Method for displaying welcome message
    public static void welcomeMessage() {
        System.out.println("***************************************************** \n Welcome to EzyerTrade online stock management system\n ****************************************************\n");
        System.out.println("Are you an existing customer? Please enter 1 \nAre you a new customer? Please enter 2");

    }

    //Method to ask the user whether existing or new user
    public static void askExistingOrNewUser() throws SQLException {

        //getting input from the customer
        Scanner scanner = new Scanner(System.in);
        String customerChoice = scanner.next();

        //validation
        while (!Utility.isValidExistingOrNewCustomerInput(customerChoice)) {
            System.out.println("Please enter 1 for buy or 2 for sell \n");
            customerChoice = scanner.next();
        }
        //1 for existing and 2 for new
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

        //Checks whether this email exists in the DB
        CustomerManagement cmObj = new CustomerManagement();
        Customers cObj = cmObj.readCustomerDetails(emailId);

        if (cObj == null) {
            System.out.println("Your email Id is not in our records. Please enter your correct email Id\n");
            existingCustomer();
        }


    }

    //Method for new Customer
    public static void newCustomer() throws SQLException {

        //Sign up
        System.out.println("Please Sign up");
        System.out.println("Enter your name: ");
        Scanner nameInput = new Scanner(System.in);
        String name = nameInput.next();

        String emailId = getEmail(System.in);

        String password = getPassword(System.in);

        System.out.println("You are successfully signed up \n 😊 😊 😊 😊 😊 😊 😊 😊 😊 😊 😊 😊 😊 \n");

        //Inserts this email in the DB table
        CustomerManagement cmObj = new CustomerManagement();
        cmObj.insertCustomerRecord(emailId, password);


    }

    //Asks the option buy or sell
    public static void askBuyOrSell() throws InterruptedException, SQLException {

        System.out.println("Choose one option: Buy or Sell");
        Scanner buyOrSellInput = new Scanner(System.in);
        String buyOrSell = buyOrSellInput.next();

        //Validation
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

    //Performs buy operation
    public static void buy() throws InterruptedException, SQLException {

        System.out.println("List of companies : StockNo -- Name --Performance -- Price -- Quantity  \n" + Inventory.stockDisplay().toString());
        System.out.println("Choose the stock number from 1 to 12");
        Scanner scanner = new Scanner(System.in);
        int stockNumberFromUser = scanner.nextInt();

        System.out.println("How many stocks do you want to buy? (Look for the last column for quantity)");
        Scanner input = new Scanner(System.in);
        int requestedQuantity = input.nextInt();

        //Checks the quantity entered for buying is less than or equal to the actual available number to buy
        while (Inventory.getStockDetails().get(stockNumberFromUser - 1).getNumberAvailable() < requestedQuantity) {
            System.out.println("Choose the quantity less than the available number" + Inventory.getStockDetails().get(stockNumberFromUser).getNumberAvailable());
            requestedQuantity = input.nextInt();

        }
        getBrokerageAccountNumber();

        afterBuyOrSell();

        //Performs the operation of adding the purchased stock to CustomerStockDetails list in the DB
        StockManagement stkObj = new StockManagement();

        StockDetails sdObj = Inventory.getStockDetails().get(stockNumberFromUser - 1);

        //Database changes
        CustomerStockDetails csdObj = new CustomerStockDetails();
        csdObj.setEmailId(emailId);
        csdObj.setCompanyName(sdObj.getCompanyName());
        csdObj.setPerformance(sdObj.getPerformance());
        csdObj.setPurchasePrice(sdObj.getPrice());
        csdObj.setCurrentMarketPrice(sdObj.getPrice());
        csdObj.setQuantity(requestedQuantity);

        //If newly bought stock = previously bought stock that exists in the DB,add it up
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


        //update the number of shares in the list provided to the user for buying
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

        //Displays CustomerStocklList of stocks from the database
        System.out.println("Displaying your list of stocks: StockNo -- Name -- current market price  -- Performance -- Quantity Available");
        List<CustomerStockDetails> csdObj = new StockManagement().readStockDetails(emailId);
        int counter = 1;
        for (CustomerStockDetails c : csdObj) {

            System.out.println(counter++ + "  " + c.toString());
        }

        System.out.println("Choose the stock number to sell from the left most side");

        Scanner sellIn = new Scanner(System.in);
        int sellInput = sellIn.nextInt();

        //Checks the stock number entered for selling is less than or equal to actual number
        while (!(sellInput <= csdObj.size())) {
            System.out.println("Choose the correct stock number to sell  ( Choose from left most column)");

            sellInput = sellIn.nextInt();
        }

        System.out.println("How many stocks do you want to sell?  ( Choose from right most column)");
        Scanner input = new Scanner(System.in);
        int quantity = input.nextInt();

        //Checks the quantity entered for selling is less than or equal to actual available number to sell
        while ((csdObj.get(sellInput - 1).getQuantity() < quantity)) {
            System.out.println("Choose the quantity less than the available number" + csdObj.get(sellInput - 1).getQuantity());

            quantity = input.nextInt();


        }

        getBrokerageAccountNumber();

        afterBuyOrSell();

        //update database after selling
        int newQuantity = csdObj.get(sellInput - 1).getQuantity() - quantity;
        new StockManagement().updateStockRecord(emailId, csdObj.get(sellInput - 1).getCompanyName(), newQuantity);

        askYesOrNoForAnotherTransaction();
    }

    //Method to get customer's online brokerage account number
    public static void getBrokerageAccountNumber() {
        System.out.println("Enter your brokerage account number   ( 8 digits only)");
        Scanner no = new Scanner(System.in);
        String number = no.next();

        //Validation
        while (!Utility.isValidAccountNumber(number)) {
            System.out.println("Please enter the valid 8 digit only account number");
            number = no.next();
        }

    }

    //Method to ask yes or no for another transaction
    public static void askYesOrNoForAnotherTransaction() throws SQLException, InterruptedException {

        System.out.println("Do you want continue with another transaction. Please type Yes or No ");
        Scanner i = new Scanner(System.in);
        if (i.hasNext("yes")) {
            askBuyOrSell();
        } else if (i.hasNext("no")) {
            System.out.println("Thanks for choosing us!\n" + "\n🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏 🙏\n" + "Have a great fortune \n 👍👍👍👍👍👍👍👍👍👍👍");
        }

    }

    //Method to email from the customer
    public static String getEmail(InputStream inputstream) {
        emailId = "no id";
        System.out.println("Log in using your credentials");
        System.out.println("Email Id( Example format:  xxxx@yyyyy.com) :  ");
        Scanner in = new Scanner(inputstream);
        emailId = in.next();
        int attempt = 1;

        //Validation
        while (!Utility.isValidEmail(emailId)) {
            System.out.println("Email is not valid. Please provide a valid email id");

            //Counter for limiting customer entry to 3 attempts
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

    //Method to get password from the customer
    public static String getPassword(InputStream inputstream) {
        System.out.println("Password (Minimum - 8 char,one digit, one lower and one upper case char, one special char,should not contain space,tab etc.): ");
        Scanner password = new Scanner(inputstream);
        String pwd = password.next();

        int attempt = 1;

        //Validation
        while (!Utility.isValidPassword(pwd)) {

            System.out.println("Password is not valid. Please provide a valid password");

            System.out.println("At least 8 chars\n Contains at least one digit\n Contains at least one lower alpha char and one upper alpha char\n Contains at least one char within a set of special chars (@#%$^ etc.)\n Does not contain space, tab, etc.");

            //Counter for limiting customer entry to 3 attempts
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








