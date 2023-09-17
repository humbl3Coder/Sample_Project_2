package helper;

import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This helper class is for the customer queries.
 */

public abstract class CustomersQuery {

    /**
     * This method insert customers into the customer database.
     * @param cusID the cusID of the customer
     * @param cusName the cusName of the customer
     * @param cusAdd the cusAdd of the customer
     * @param cusPos the cusPos of the customer
     * @param cusPhone the cusPhone of the customer
     * @param cusDivID the cusDivID of the customer
     * @return This inserts the new Customer in to the database.
     * @throws SQLException
     */

    public static int cusInsert (int cusID, String cusName, String cusAdd, String cusPos, String cusPhone, int cusDivID) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setInt(1, cusID);
        ps.setString(2, cusName);
        ps.setString(3, cusAdd);
        ps.setString(4, cusPos);
        ps.setString(5, cusPhone);
        ps.setInt(6, cusDivID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * This method updates a customer in the database with a specific customerID.
     * @param cusID the cusID of the customer
     * @param cusName the cusName of the customer
     * @param cusAdd the cusAdd of the customer
     * @param cusPos the cusPos of the customer
     * @param cusPhone the cusPhone of the customer
     * @param cusDivID the cusDivID of the customer
     * @return This updates the Customer in the database.
     * @throws SQLException
     */

    public static int cusUpdate (int cusID, String cusName, String cusAdd, String cusPos, String cusPhone, int cusDivID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setString(1, cusName);
        ps.setString(2, cusAdd);
        ps.setString(3, cusPos);
        ps.setString(4, cusPhone);
        ps.setInt(5, cusDivID);
        ps.setInt(6, cusID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * This method deletes a customer from the database.
     * @param cusID the cusID of the Customer to be deleted
     * @return This deletes the Customer in the database.
     * @throws SQLException
     */

    public static int cusDelete(int cusID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setInt(1, cusID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * This method selects customers from the database with division info attached.
     * @throws SQLException
     */

    public static void cusSelect() throws SQLException {
        String sql = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, c.Division_ID, d.Division FROM customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID;";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int cusID = rs.getInt("Customer_ID");
            String cusName = rs.getString("Customer_Name");
            String cusAdd = rs.getString("Address");
            String cusPos = rs.getString("Postal_Code");
            String cusPhone = rs.getString("Phone");
            int cusDivID = rs.getInt("Division_ID");
            String cusDiv = rs.getString("Division");
            CollectionLists.allCustomers.add(new Customers(cusID, cusName, cusAdd, cusPos, cusPhone, cusDivID, cusDiv));
        }
    }

    /**
     * This method selects a customer from the database with distinct customerID.
     * @throws SQLException
     */

    public static void cusAdded() throws SQLException {
        String sql = "SELECT c.Customer_ID, c.Customer_Name, c.Address, c.Postal_Code, c.Phone, c.Division_ID, d.Division FROM customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID where Customer_ID = (select max(Customer_ID) from customers);";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int cusID = rs.getInt("Customer_ID");
            String cusName = rs.getString("Customer_Name");
            String cusAdd = rs.getString("Address");
            String cusPos = rs.getString("Postal_Code");
            String cusPhone = rs.getString("Phone");
            int cusDivID = rs.getInt("Division_ID");
            String cusDiv = rs.getString("Division");
            CollectionLists.allCustomers.add(new Customers(cusID, cusName, cusAdd, cusPos, cusPhone, cusDivID, cusDiv));
        }
    }

    /**
     * This method updates a customer give a specific CustomerID.
     * @param custID the custID of the customer
     * @throws SQLException
     */

    public static void cusUpdated(int custID) throws SQLException {
        String sql = "SELECT * FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID WHERE Customer_ID = ?;";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setInt(1, custID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int cusID = rs.getInt("Customer_ID");
            String cusName = rs.getString("Customer_Name");
            String cusAdd = rs.getString("Address");
            String cusPos = rs.getString("Postal_Code");
            String cusPhone = rs.getString("Phone");
            int cusDivID = rs.getInt("Division_ID");
            String cusDiv = rs.getString("Division");
            CollectionLists.allCustomers.add(new Customers(cusID, cusName, cusAdd, cusPos, cusPhone, cusDivID, cusDiv));
        }

    }
}
