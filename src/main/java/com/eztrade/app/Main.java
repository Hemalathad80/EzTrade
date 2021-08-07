package com.eztrade.app;

import com.eztrade.app.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, SQLException {


        //Welcome message
        Controller.welcomeMessage();

        //Ask whether existing or New user
        Controller.askExistingOrNewUser();

        //Asking the option buy or sell
        Controller.askBuyOrSell();


    }
}
