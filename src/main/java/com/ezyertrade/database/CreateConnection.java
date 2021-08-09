/**
 * Reference : https://www.javaguides.net/2019/08/java-h2-database-tutorial.html
 */
package com.ezyertrade.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateConnection {


    private static String jdbcURL = "jdbc:h2:tcp://localhost:9092/~/tmp/h2dbs/eztradedb";
    private static String jdbcUsername = "sa";
    private static String jdbcPassword = "capstone";


    public static Connection getConnection() {
        Connection connection = null;
        try {
      //      Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }


    public static void createTable(String createTableSQL) throws SQLException {

        //System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            // print SQL exception information
            CreateConnection.printSQLException(e);
        }
    }
     public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
