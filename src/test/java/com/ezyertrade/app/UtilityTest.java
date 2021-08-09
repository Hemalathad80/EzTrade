package com.ezyertrade.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    @Test
    public void testIsValidEmail() {
        assertTrue(Utility.isValidEmail("hema@gmail.com"));
        assertFalse(Utility.isValidEmail("seamail.com"));
    }

    @Test
    public void testIsValidPassword(){
        assertTrue(Utility.isValidPassword("Capstone@2021"));
        assertFalse(Utility.isValidPassword("Capstone021"));
    }

    @Test
    public void testIsValidAccountNumber() {

        assertTrue(Utility.isValidAccountNumber("12345678"));
        assertFalse(Utility.isValidAccountNumber("123456789"));
    }

    @Test
    public void testIsValidExistingOrNewCustomerInput() {

        assertTrue(Utility.isValidExistingOrNewCustomerInput("1"));
        assertFalse(Utility.isValidExistingOrNewCustomerInput("12"));
    }

    @Test
    public void testIsValidBuyOrSellInput() {

        assertTrue(Utility.isValidBuyOrSellInput("buy"));
        assertTrue(Utility.isValidBuyOrSellInput("sell"));
        assertFalse(Utility.isValidBuyOrSellInput("buyer"));
    }


}
