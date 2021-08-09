package com.ezyertrade.app;

public class CustomerStockDetails {
    String emailId;
    String companyName;
    String purchasePrice;
    String currentMarketPrice;
    String Performance;
    int quantity;

    public CustomerStockDetails() {

    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(String currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }

    public String getPerformance() {
        return Performance;
    }

    public void setPerformance(String performance) {
        Performance = performance;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return companyName + "  " +
                currentMarketPrice + "  " +
                 Performance + "  " +
               quantity
                ;
    }
}
