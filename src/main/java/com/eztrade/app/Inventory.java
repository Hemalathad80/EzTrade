package com.eztrade.app;

import java.util.ArrayList;
import java.util.List;

import com.eztrade.app.StockDetails;

public class Inventory {

    static StockDetails stockOne = new StockDetails("Dick Smith Holdings Limited", "+8.7%", "$0.355", 100);
    static StockDetails stockTwo = new StockDetails("Viasat", "+1.4%", "$52.14", 1000);
    static StockDetails stockThree = new StockDetails("Gladstone Commercial", "-4.07%", "$23.11", 100);
    static StockDetails stockFour = new StockDetails("CyrusOne", "-0.44%", "$75.04", 100);
    static StockDetails stockFive = new StockDetails("Cal-Maine Foods", "+8.34%", "$36.67", 100);
    static StockDetails stockSix = new StockDetails("Pega Systems", "+3.14%", "$127.54", 100);
    static StockDetails stockSeven = new StockDetails("American Campus Communities", "-0.56%", "$75.74", 100);
    static StockDetails stockEight = new StockDetails("Telcos", "-3%", "$28.43", 100);
    static StockDetails stockNine = new StockDetails("ServiceNow", "+0.82%", "$595.50", 100);
    static StockDetails stockTen = new StockDetails("EOG Resources", "-9.6%", "$70.74", 100);
    static StockDetails stockEleven = new StockDetails("Star Surgical", "+ 3.33%", "$165.05", 100);
    static StockDetails stockTwelve = new StockDetails("Bright Horizons Family Solutions", "- 1.21%", "$145.74", 100);


    public static StringBuilder stockDisplay() {


        List<StockDetails> listOfStocks;
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



        StringBuilder sb = new StringBuilder();


        for (StockDetails s : listOfStocks) {

            sb.append(s);
            sb.append("\n");

        }
        return sb;

    }
}

































































































