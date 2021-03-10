package dao;

import model.Contact;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is the Data Access Object for the Contact class. This class performs all database queries for the
 * Contact class to include all create, read, update and delete functionalities. */
public class ContactDAO {
    // Completed.
    /** Returns a Contact based off of information provided by the Contact class. This class is used to verify if a
     * Contact exists within the database.
     @param conn The database Connection object to use to perform the query
     @param contact The contact to verify if exists
     @return Contact new Contact() if True, null otherwise. */
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

    /** This method returns True if the database insertion completed successfully, false otherwise.
     @param conn The database Connection object to perform the query
     @param contact The contact to be inserted */
    public void save(Connection conn, Contact contact) throws SQLException{
        String insertStatement = "INSERT INTO contacts(Contact_Name, Email) VALUES (?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, contact.getContactName());
        statement.setString(2, contact.getEmail());
        try{
            statement.execute();
        }catch (SQLException ex){
            System.out.println("Contact information not saved. ");
        }
    }
}
