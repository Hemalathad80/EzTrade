package com.eztrade.app;

import com.eztrade.app.Inventory;
import com.eztrade.app.StockDetails;
import com.eztrade.database.CustomerManagement;
import com.eztrade.database.StockManagement;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    static String emailId;

    public static void welcomeMessage() {
        System.out.println("Welcome to EzTrade online stock management system");
        System.out.println("Are you an existing customer? Please enter 1 " + "\n" + "Are you a new customer? Please enter 2");

    }

    public static void askExistingOrNewUser() throws SQLException {
        //getting input from the customer
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.next();

        // log in the customer
        if (userName.equalsIgnoreCase("1")) {
            existingCustomer();
        } else if (userName.equalsIgnoreCase("2")) {
            newCustomer();
            System.out.println("Please enter your details");
            existingCustomer();
        }
    }

    public static void existingCustomer() {

        System.out.println("Log in using your credentials");
        System.out.println("Email Id : ");
        Scanner in = new Scanner(System.in);
        emailId = in.next();

        while (!Utility.isValidEmail(emailId)) {
            System.out.println("Email is not valid. Please provide a valid email id");
            System.out.println("Email Id : ");
            in = new Scanner(System.in);
            emailId = in.next();
        }

        System.out.println("Password : ");
        Scanner password = new Scanner(System.in);
        String pwd = password.next();
        CustomerManagement cmObj = new CustomerManagement();
        Customers cObj = cmObj.readCustomerDetails(emailId);

        if (cObj == null) {
            System.out.println("Your email Id is not in our records. Please enter your correct email Id");
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
        System.out.println(" Enter your email id");
        Scanner emailIdInput = new Scanner(System.in);
        String emailId = emailIdInput.next();

        System.out.println("Enter your password");
        Scanner passwordInput = new Scanner(System.in);
        String password = passwordInput.next();
        System.out.println("You are successfully signed up");
        cmObj.insertCustomerRecord(3, emailId, password);


    }

    public static void askBuyOrSell() throws InterruptedException, SQLException {
        System.out.println("Choose one option: Buy or Sell");
        Scanner buyOrSellInput = new Scanner(System.in);
        String buyOrSell = buyOrSellInput.next();
        if (buyOrSell.equalsIgnoreCase("Buy")) {
            buy();

        } else if (buyOrSell.equalsIgnoreCase("Sell")) {
            sell();

        }
    }


    public static void buy() throws InterruptedException, SQLException {

        System.out.println("List of companies  \n" + Inventory.stockDisplay().toString() + "\n");
        System.out.println("Choose the stock number");
        Scanner scanner = new Scanner(System.in);
        int stockNumberFromUser = scanner.nextInt();

        System.out.println("How many stocks do you want to buy?");
        Scanner input = new Scanner(System.in);
        int requestedQuantity = input.nextInt();

        while (Inventory.getStockDetails().get(stockNumberFromUser - 1).getNumberAvailable() < requestedQuantity) {
            System.out.println("Choose the quantity less than the available number" + Inventory.getStockDetails().get(stockNumberFromUser).getNumberAvailable());
            input = new Scanner(System.in);
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
        boolean checktheRecordAlreadyExist = false;
        int existingQuantity=0;

        for(CustomerStockDetails stkDetails : existingStockDetails) {
            if(stkDetails.getCompanyName().equalsIgnoreCase(sdObj.getCompanyName())) {
                checktheRecordAlreadyExist = true;
                existingQuantity = stkDetails.getQuantity();
            }
        }
        if(checktheRecordAlreadyExist) {
            stkObj.updateStockRecord(emailId, sdObj.getCompanyName(), existingQuantity + requestedQuantity);
        }
        else {
            stkObj.insertStockRecord(csdObj);
        }


        //update the number of shares
        int numberDifference = sdObj.getNumberAvailable() - requestedQuantity;
        sdObj.setNumberAvailable(numberDifference);

        askYesOrNoForAnotherTransaction();

    }

    public static void afterBuyOrSell() throws InterruptedException, SQLException {


        System.out.println("Transaction processing......");

        Thread.sleep(2000);
        System.out.println("Transaction is successful");
        System.out.println("Transaction report will be sent to your email Id");

    }


    public static void sell() throws InterruptedException, SQLException {
        //Displays your list of stocks from the database
        System.out.println("Displaying your list of stocks with price at the time of purchase and current market price");
        List<CustomerStockDetails> csdObj = new StockManagement().readStockDetails(emailId);
        int counter = 1;
        for(CustomerStockDetails c : csdObj) {

            System.out.println(counter++ + "  " + c.toString());
        }
        System.out.println("Choose the stock number to sell ");

        Scanner sellIn = new Scanner(System.in);
        int sellInput = sellIn.nextInt();
        System.out.println("How many stocks do you want to sell?");
        Scanner input = new Scanner(System.in);
        int quantity = input.nextInt();

        getBrokerageAccountNumber();

        afterBuyOrSell();

        //update database after sell
         int newQuantity = csdObj.get(sellInput - 1).getQuantity()- quantity;
         new StockManagement().updateStockRecord( emailId ,  csdObj.get(sellInput -1).getCompanyName(), newQuantity);

        askYesOrNoForAnotherTransaction();
    }

    public static void getBrokerageAccountNumber() {
        System.out.println("Enter your brokerage account number");
        Scanner no = new Scanner(System.in);
        int number = no.nextInt();
    }

    public static void askYesOrNoForAnotherTransaction() throws SQLException, InterruptedException {

        System.out.println("Do you want continue with another transaction");
        Scanner i = new Scanner(System.in);
        if (i.hasNext("yes")) {
            askBuyOrSell();
        } else if (i.hasNext("no")) {
            System.out.println("Thanks for choosing us!" + "\n" + "Have a great fortune");
        }

    }

}







