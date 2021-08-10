package com.ezyertrade.app;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws InterruptedException, SQLException {


        //Displays Welcome message
        Controller.welcomeMessage();

        //Ask whether existing or New user
        Controller.askExistingOrNewUser();

        //Asking the option buy or sell
        Controller.askBuyOrSell();


    }
}
