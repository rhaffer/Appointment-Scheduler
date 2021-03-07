package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public Contact get(Connection conn, Contact contact) throws SQLException {
        String selectStatement = "SELECT * FROM contacts WHERE Contact_Name = ? AND Email = ?";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, contact.getContactName());
        statement.setString(2, contact.getEmail());
        try{
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                return new Contact(contactID, contactName, contactEmail);
            }else{
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }
    }

    public Boolean save(Connection conn, Contact contact) throws SQLException{
        String insertStatement = "INSERT INTO contacts(Contact_Name, Email) VALUES (?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, contact.getContactName());
        statement.setString(2, contact.getEmail());
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
}
