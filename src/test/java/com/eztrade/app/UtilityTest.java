package com.eztrade.app;

import com.eztrade.app.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {
    @Test
    public void testIsValidEmail(){
        assertTrue(Utility.isValidEmail("hema@gmail.com"));
        assertFalse(Utility.isValidEmail("seamail.com"));
    }
}
