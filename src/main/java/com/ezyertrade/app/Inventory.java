package com.ezyertrade.app;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    //Initializing 12 Stock objects
    static StockDetails stockOne = new StockDetails(1, "Dick Smith Holdings Limited", "+8.7%", "$0.355", 100);
    static StockDetails stockTwo = new StockDetails(2, "Viasat", "+1.4%", "$52.14", 1000);
    static StockDetails stockThree = new StockDetails(3, "Gladstone Commercial", "-4.07%", "$23.11", 100);
    static StockDetails stockFour = new StockDetails(4, "CyrusOne", "-0.44%", "$75.04", 100);
    static StockDetails stockFive = new StockDetails(5, "Cal-Maine Foods", "+8.34%", "$36.67", 100);
    static StockDetails stockSix = new StockDetails(6, "Pega Systems", "+3.14%", "$127.54", 100);
    static StockDetails stockSeven = new StockDetails(7, "American Campus Communities", "-0.56%", "$75.74", 100);
    static StockDetails stockEight = new StockDetails(8, "Telcos", "-3%", "$28.43", 100);
    static StockDetails stockNine = new StockDetails(9, "ServiceNow", "+0.82%", "$595.50", 100);
    static StockDetails stockTen = new StockDetails(10, "EOG Resources", "-9.6%", "$70.74", 100);
    static StockDetails stockEleven = new StockDetails(11, "Star Surgical", "+ 3.33%", "$165.05", 100);
    static StockDetails stockTwelve = new StockDetails(12, "Bright Horizons Family Solutions", "- 1.21%", "$145.74", 100);

    List<StockDetails> listOfStocks;

    //Constructor
    public Inventory() {

        listOfStocks = new ArrayList<>();

        listOfStocks.add(stockOne);
        listOfStocks.add(stockTwo);
        listOfStocks.add(stockThree);
        listOfStocks.add(stockFour);
        listOfStocks.add(stockFive);
        listOfStocks.add(stockSix);
        listOfStocks.add(stockSeven);
        listOfStocks.add(stockEight);
        listOfStocks.add(stockNine);
        listOfStocks.add(stockTen);
        listOfStocks.add(stockEleven);
        listOfStocks.add(stockTwelve);

    }

    //Displays the list of stocks
    public static StringBuilder stockDisplay() {

        StringBuilder sb = new StringBuilder();

        List<StockDetails> lsObj = new Inventory().listOfStocks;

        for (StockDetails s : lsObj) {

            sb.append(s);
            sb.append("\n");

        }
        return sb;

    }

    public static List<StockDetails> getStockDetails() {
        return new Inventory().listOfStocks;
    }


}


































































































