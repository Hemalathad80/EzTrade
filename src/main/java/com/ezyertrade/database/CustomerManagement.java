
package com.ezyertrade.database;

import com.ezyertrade.app.Customers;

import java.sql.*;

public class CustomerManagement {

    //create table query
    private static final String createTableSQL = "create table EzyerTradeusers (\r\n" + "  id bigint auto_increment,\r\n" +
            "  email varchar(350),\r\n" +
            "  password varchar(20)\r\n" + "  );";

    //Insert query
    private static final String INSERT_USERS_SQL = "INSERT INTO EzyerTradeusers" +
            "  (email, password) VALUES " +
            " (?, ?);";

    private static final String QUERY = "select id,email,password from EzyerTradeusers where email =?";

    public void createCustomerManagementTable() throws SQLException {

        CreateConnection.createTable(createTableSQL);
    }

    //Insert customer record
    public void insertCustomerRecord(String emailId, String pwd) throws SQLException {
        //System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, pwd);

            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            CreateConnection.printSQLException(e);
        }
    }

    //read customer record
    public Customers readCustomerDetails(String existingEmailId) {
        Customers cObj = null;

        // using try-with-resources to avoid closing resources (boilerplate code)

        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {

            preparedStatement.setString(1, existingEmailId);
            //System.out.println(preparedStatement);


            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                //int id = rs.getInt("id");
                if (rs.getString("email").equalsIgnoreCase(existingEmailId)) {
                    cObj = new Customers();
                    cObj.setEmailId(rs.getString("email"));
                    cObj.setPassword(rs.getString("password"));
                }

            }

        } catch (SQLException e) {
            CreateConnection.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return cObj;
    }
}



