package com.eztrade.app;

import com.eztrade.app.Controller;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {
    @Test
    public void testGetEmail() throws FileNotFoundException {
        String inputData = "sandra@gmail.com";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        assertEquals("sandra@gmail.com", Controller.getEmail());

        /*inputData = "sandragmail.com";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));

        String outputData = "Email is not valid. Please provide a valid email id\n" + "Email Id : ";
        System.setOut(new PrintStream(outputData) );


        assertEquals(, Controller.getEmail());*/



    }

    @Test
    public void testGetPassword() {

        String inputData = "Capstone@2021";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        assertEquals("Capstone@2021", Controller.getPassword());

    }


}
