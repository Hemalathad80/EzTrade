
package com.ezyertrade.database;

import com.ezyertrade.app.CustomerStockDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockManagement {

    private static final String createTableSQL = "create table EzyerTradeStockManagement (\r\n" + "  id bigint auto_increment,\r\n" +
            "  email varchar(350),\r\n" +
            "  companyname varchar(500),\r\n" +
            "  purchaseprice varchar(500),\r\n" +
            "  currentmarketprice varchar(500),\r\n" +
            "  performance varchar(500),\r\n" +
            "  quantity int \r\n" +
            "  );";

    private static final String INSERT_STOCK_DETAILS_SQL = "INSERT INTO EzyerTradeStockManagement" +
            "  (email, companyname, purchaseprice, currentmarketprice, performance, quantity ) VALUES " +
            " (?, ?, ?, ?, ?, ?);";


    private static final String UPDATE_STOCK_DETAILS_SQL = "update EzyerTradeStockManagement set quantity = ? where email = ? and companyname =?;";

    private static final String GET_STOCK_DETAILS_BY_EMAIL = "select id,email, companyname, purchaseprice, currentmarketprice, performance, quantity  from EzyerTradeStockManagement where email =?";



    public void createStockManagementTable() throws SQLException {

        CreateConnection.createTable(createTableSQL);
    }

    public void insertStockRecord(CustomerStockDetails csdObj) throws SQLException {
        //System.out.println(INSERT_STOCK_DETAILS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STOCK_DETAILS_SQL)) {
            preparedStatement.setString(1, csdObj.getEmailId());
            preparedStatement.setString(2, csdObj.getCompanyName());
            preparedStatement.setString(3, csdObj.getPurchasePrice());
            preparedStatement.setString(4, csdObj.getCurrentMarketPrice());
            preparedStatement.setString(5, csdObj.getPerformance());
            preparedStatement.setInt(6, csdObj.getQuantity());
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            CreateConnection.printSQLException(e);
        }
    }

    public void updateStockRecord(String email, String companyname, int quantity) throws SQLException {
        //System.out.println(UPDATE_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK_DETAILS_SQL)) {

            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, companyname);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            CreateConnection.printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public List<CustomerStockDetails> readStockDetails(String existingEmailId) {

        List<CustomerStockDetails> cObj = new ArrayList<>();

        // using try-with-resources to avoid closing resources (boilerplate code)

        // Step 1: Establishing a Connection
        try (Connection connection = CreateConnection.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(GET_STOCK_DETAILS_BY_EMAIL);) {

            preparedStatement.setString(1, existingEmailId);
            //System.out.println(preparedStatement);


            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            CustomerStockDetails tempObj;

            while (rs.next()) {
                tempObj = new CustomerStockDetails();
                tempObj.setEmailId(rs.getString("email"));
                tempObj.setCompanyName(rs.getString("companyname"));
                tempObj.setCurrentMarketPrice(rs.getString("currentmarketprice"));
                tempObj.setPerformance(rs.getString("performance"));
                tempObj.setPurchasePrice(rs.getString("purchaseprice"));
                tempObj.setQuantity(rs.getInt("quantity"));
                cObj.add(tempObj);
            }

        } catch (SQLException e) {
            CreateConnection.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return cObj;
    }


}
