package com.eztrade.app;

import com.eztrade.app.Controller;
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

        inputData = "sandragmail.com\nsandra@gmail.com";

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
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
        assertEquals("Capstone@2021", Controller.getPassword());

    }


}
