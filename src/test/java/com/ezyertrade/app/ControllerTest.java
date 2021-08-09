package com.ezyertrade.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {
    @Test
    public void testGetEmail() throws FileNotFoundException {
        String inputData = "sandra@gmail.com";
        assertEquals("sandra@gmail.com", Controller.getEmail(new java.io.ByteArrayInputStream(inputData.getBytes())));

        //inputData = "sandragmail.com\nsandra@gmail.com";
        inputData = "sandragmail.com\nsandragmail.com\nsandragmail.com";

        //System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));

        String expectedOutputData = "Email is not valid. Please provide a valid email id";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
         System.setOut(ps);
         Controller.getEmail(new java.io.ByteArrayInputStream(inputData.getBytes()));
         System.out.flush();
        String actualOutput = baos.toString();
        assertTrue(actualOutput.contains(expectedOutputData));
    }

    @Test
    public void testGetPassword() {

        String inputData = "Capstone@2021";
        assertEquals("Capstone@2021", Controller.getPassword(new java.io.ByteArrayInputStream(inputData.getBytes())));

        inputData ="capstone2021\nCapstone@2021";

        String expectedOutputData = "Password is not valid. Please provide a valid password";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Controller.getPassword(new java.io.ByteArrayInputStream(inputData.getBytes()));
        System.out.flush();
        String actualOutput = baos.toString();
        assertTrue(actualOutput.contains(expectedOutputData));



    }


}
