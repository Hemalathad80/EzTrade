
package com.ezyertrade.database;

import com.ezyertrade.app.Customers;

import java.sql.*;

public class CustomerManagement {

    private static final String createTableSQL = "create table EzTradeusers (\r\n" + "  id bigint auto_increment,\r\n" +
            "  email varchar(350),\r\n" +
            "  password varchar(20)\r\n" + "  );";

    private static final String INSERT_USERS_SQL = "INSERT INTO EzTradeusers" +
            "  (id, email, password) VALUES " +
            " (?, ?, ?);";

    private static final String QUERY = "select id,email,password from EzTradeusers where email =?";

    public void createCustomerManagementTable() throws SQLException {

        CreateConnection.createTable(createTableSQL);
    }

    public void insertCustomerRecord(int Id, String emailId,String pwd) throws SQLException {
        //System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, Id);
            preparedStatement.setString(2, emailId);
            preparedStatement.setString(3, pwd);

            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            CreateConnection.printSQLException(e);
        }
    }

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
                    if(rs.getString("email").equalsIgnoreCase(existingEmailId)){
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



