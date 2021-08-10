package com.ezyertrade.app;

public class StockDetails {

    //Variables of the stock
    private int stockNumber;
    private String companyName;
    private String performance;
    private String price;
    private int numberAvailable;

    public StockDetails() {

    }

    //Constructor
    public StockDetails(int stockNumber, String companyName, String performance, String price, int numberAvailable) {
        this.stockNumber = stockNumber;
        this.companyName = companyName;
        this.performance = performance;
        this.price = price;
        this.numberAvailable = numberAvailable;

    }

    //Getters and Setters
    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRating() {
        return performance;
    }

    public String getPrice() {
        return price;
    }

    public int getNumberAvailable() {
        return numberAvailable;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setRating(String rating) {
        this.performance = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNumberAvailable(int numberAvailable) {
        this.numberAvailable = numberAvailable;
    }


    //toString() method
    @Override
    public String toString() {
        return stockNumber + "   " + companyName + "   " +
                performance + "    " +
                price + "   " +
                numberAvailable + "\n"
                ;
    }
}
