package com.eztrade.app;

import com.eztrade.app.Controller;
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

}
