package com.eztrade.app;
public class StockDetails {

    private String companyName;
    private String performance;
    private String price;
    private int numberAvailable;

    public StockDetails() {

    }
    public StockDetails(String companyName, String performance, String price, int numberAvailable) {
        this.companyName = companyName;
        this.performance = performance;
        this.price = price;
        this.numberAvailable = numberAvailable;
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

    @Override
    public String toString() {
        return companyName + "   " +
                performance +  "    " +
                price + "   "  +
                numberAvailable + "\n"
               ;
    }
}
