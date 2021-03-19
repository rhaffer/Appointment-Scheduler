package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import report.ContactScheduleReport;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class is the Data Access Object for the Contact class. This class performs all database queries for the
 * Contact class to include all create, read, update and delete functionalities. */
public class ContactDAO {
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

    /**
     * This method retrieves a Contact from the database by Contact_ID
     * @param conn The Connection object used to connect to the database
     * @param contact_id The Contact_ID to retrieve a Contact from the database
     * @return A Contact object if one exists with the Contact_ID, null otherwise
     */
    public Contact get(Connection conn, int contact_id) throws SQLException{
        String selectStatement = "SELECT * FROM contacts WHERE Contact_ID = ?";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, contact_id);
        try{
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                return new Contact(contactID, contactName, contactEmail);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }
    }

    /**
     * This method retrieves all Contacts from the database
     *
     * @param conn The Connection object used to connect to the database
     * @return ObservableList Contact contacts
     */
    public ObservableList<Contact> getAll(Connection conn) throws SQLException {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String selectStatement = "SELECT * FROM contacts";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        try {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                contacts.add(new Contact(contactID, contactName, contactEmail));
            }
            return contacts;
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }
    }


    public ObservableList<ContactScheduleReport> getReport(Connection conn) throws SQLException {
        ObservableList<ContactScheduleReport> results = FXCollections.observableArrayList();
        String joinStatement = "SELECT contacts.Contact_Name, contacts.Contact_ID, appointments.Appointment_ID, " +
                "appointments.Title, appointments.Description, appointments.Start, appointments.End, " +
                "appointments.Customer_ID " +
                "FROM contacts " +
                "INNER JOIN appointments ON (appointments.Contact_ID=contacts.Contact_ID)";
        DBQuery.setPreparedStatement(conn, joinStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        try{
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                String contactName = rs.getString("Contact_Name");
                String contactID = String.valueOf(rs.getInt("Contact_ID"));
                String appointmentID = String.valueOf(rs.getInt("Appointment_ID"));
                String appointmentTitle = rs.getString("Title");
                String appointmentDesc = rs.getString("Description");
                String appointmentStart = rs.getTimestamp("Start").toString();
                String appointmentEnd = rs.getTimestamp("End").toString();
                String customerID = String.valueOf(rs.getInt("Customer_ID"));
                ContactScheduleReport result = new ContactScheduleReport(contactName, contactID, appointmentID, appointmentTitle, appointmentDesc,
                        appointmentStart, appointmentEnd, customerID);
                results.add(result);
            }
            return results;
        }catch (SQLException ex){
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
