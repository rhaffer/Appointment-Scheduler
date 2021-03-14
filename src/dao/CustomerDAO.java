package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.FirstLevelDivision;
import model.User;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/** This class is the Data Access Object for the Customer class. This class performs all database queries for the
 * Customer class to include all create, read, update and delete functionalities. */
public class CustomerDAO {
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    /** This method returns a list of all Customers within the database.
     @param conn The database Connection object to perform the query
     @return ObservabeList Customer customers */
    public ObservableList<Customer> getAll(Connection conn) throws SQLException {
        String selectAllStatement = "SELECT * FROM customers";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getTimestamp("Last_Update").toString();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            customers.add(new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy,
                    lastUpdate, lastUpdatedBy, divisionID));
        }
        return customers;
    }

    /** This method returns True if database insertion is successful, false otherwise.
     @param conn The database Connection object used to perform the query
     @param customer The Customer to be inserted into the database
     @return True if insertion successful, false otherwise */
    public Boolean save(Connection conn, Customer customer) throws SQLException {
        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getAddress());
        statement.setString(3, customer.getPostalCode());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getCreateDate());
        statement.setString(6, customer.getCreatedBy());
        statement.setString(7, customer.getLastUpdate());
        statement.setString(8, customer.getLastUpdatedBy());
        statement.setInt(9, customer.getDivisionID());
        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.out.println("Duplicate Key Error.");
            }
            return false;
        }
    }

    /** This method updates a customer based off of the Customer ID.
     @param conn The database Connection object to perform the query
     @param customer The customer to be updated
     @param division The FirstLevelDivision object that associates with the Customer
     @param user The User who is performing the update
     @return True if update is successful, false otherwise */
    public Boolean update(Connection conn, Customer customer, FirstLevelDivision division, User user) throws SQLException {
        String updateStatement = "UPDATE " +
                "customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, " +
                "Last_Updated_By = ?, Division_ID = ?" +
                "WHERE customer_ID = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getAddress());
        statement.setString(3, customer.getPostalCode());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, LocalDateTime.now().toString());
        statement.setString(6, user.getUserName());
        statement.setString(7, String.valueOf(division.getDivisionID()));
        statement.setInt(8, customer.getCustomerID());
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    /** This method deletes a Customer from the database.
     @param conn The Connection objected used to perform the query
     @param customerID The Customer ID to be deleted
     @return True if deletion is successful, false otherwise */
    public Boolean delete(Connection conn, int customerID) throws SQLException {
        String delStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, delStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, customerID);
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    /** This method performs a Join operation in order to gather all Customer information. Used to populate Update/Delete
     * form.
     @param conn The Connection object used to perform the query
     @param customer The Customer object the query is targeting
     @return ObservabeList String customerInfo */
    public ObservableList<String> getCustomerDivisionCountry(Connection conn, Customer customer) throws SQLException {
        ObservableList<String> customerInfo = FXCollections.observableArrayList();
        String sqlStatement = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, " +
                "customers.Postal_Code, customers.Phone, first_level_divisions.Division, countries.Country " +
                "FROM first_level_divisions " +
                "INNER JOIN customers ON (customers.Division_ID=first_level_divisions.Division_ID) " +
                "INNER JOIN countries ON (countries.Country_ID=first_level_divisions.COUNTRY_ID) " +
                "WHERE customers.Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, sqlStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, customer.getCustomerID());
        try{
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()){
                String customerID = String.valueOf(rs.getInt("Customer_ID"));
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String divison = rs.getString("Division");
                String country = rs.getString("Country");
                customerInfo.addAll(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divison, country);
            }
        }
        catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());

        }
        return customerInfo;
    }
}
