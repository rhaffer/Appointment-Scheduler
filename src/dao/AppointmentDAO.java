package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import util.DBQuery;
import util.TimeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/** This class is the Data Access Object for the Appointment class. This class performs all database queries for the
 * Appointment class to include all create, read, update and delete functionalities. */
public class AppointmentDAO {
    private final TimeConverter converter = new TimeConverter();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /** Returns a list of start times. This is used to gather data for the "Start Times" ComboBox. This function
     * handles conversion from UTC to local time zone.
     @param conn The database Connection object to perform the query.
     @return ObservableList LocalDateTime startTimes */
    public ObservableList<LocalDateTime> getStartTimes(Connection conn) throws SQLException{
        ObservableList<LocalDateTime> startTimes = FXCollections.observableArrayList();
        String selectStatement = "SELECT Start FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()){
            ZonedDateTime zoneStart = ZonedDateTime.of(rs.getTimestamp("Start").toLocalDateTime(), ZoneId.of("UTC"));
            LocalDateTime start = converter.convertToLocal(zoneStart);
            startTimes.add(start);
        }
        return startTimes;
    }

    /** Returns a list of all appointments in the database. This function handles the time conversion from GMT
     * to Local time zones.
     @param conn The database Connection object to perform the query
     @return ObservableList Appointment appointments */
    public ObservableList<Appointment> getAll(Connection conn) throws SQLException{
        String selectAllStatement = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = converter.convertToLocal(ZonedDateTime.of(rs.getTimestamp("Start").toLocalDateTime(), ZoneId.of("UTC")));
            LocalDateTime end = converter.convertToLocal(ZonedDateTime.of(rs.getTimestamp("End").toLocalDateTime(), ZoneId.of("UTC")));
            LocalDateTime createDate = converter.convertToLocal(ZonedDateTime.of(rs.getTimestamp("Create_Date").toLocalDateTime(), ZoneId.of("UTC")));
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getString("Last_Update");
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            appointments.add(new Appointment(appointmentID, title, description, location, type, start, end, createDate,
                    createdBy,lastUpdate, lastUpdatedBy, customerID, userID, contactID));
        }
        return appointments;
    }

    /** This method returns True if the database insertion completed successfully, false otherwise. This function
     * handles conversion from Local to GMT.
     @param conn The database Connection object to perform the query.
     @param appt The appointment to be saved
     @return True if insertion completed successfully, false otherwise */
    public Boolean save(Connection conn, Appointment appt) throws SQLException{
        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, " +
                "Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, appt.getTitle());
        statement.setString(2, appt.getDescription());
        statement.setString(3, appt.getLocation());
        statement.setString(4, appt.getType());
        statement.setString(5, converter.convertToGMT(ZonedDateTime.of(appt.getStart(),
                ZoneId.systemDefault())).toString());
        statement.setString(6, converter.convertToGMT(ZonedDateTime.of(appt.getEnd(),
                ZoneId.systemDefault())).toString());
        statement.setString(7, converter.convertToGMT(ZonedDateTime.of(appt.getCreate_date(),
                ZoneId.systemDefault())).toString());
        statement.setString(8, appt.getCreated_by());
        statement.setString(9, appt.getLastUpdatedBy());
        statement.setInt(10, appt.getCustomer_id());
        statement.setInt(11, appt.getUserID());
        statement.setInt(12, appt.getContact_id());
        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * This method updates an Appointment based off of changes made.
     * @param conn The Connection object used to connect to the database
     * @param appointment The appointment to be updated
     * @return True if update was successful, false otherwise
     */
    public Boolean update(Connection conn, Appointment appointment) throws SQLException {
        String updateStatement = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?," +
                "End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                "WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(conn, updateStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, appointment.getTitle());
        statement.setString(2, appointment.getDescription());
        statement.setString(3, appointment.getLocation());
        statement.setString(4, appointment.getType());
        statement.setString(5, converter.convertToGMT(ZonedDateTime.of(appointment.getStart(),
                ZoneId.systemDefault())).toString());
        statement.setString(6, converter.convertToGMT(ZonedDateTime.of(appointment.getEnd(),
                ZoneId.systemDefault())).toString());
        statement.setString(7,  converter.convertToGMT(ZonedDateTime.of(LocalDateTime.now(),
                ZoneId.systemDefault())).toString());
        statement.setString(8, appointment.getLastUpdatedBy());
        statement.setInt(9, appointment.getCustomer_id());
        statement.setInt(10, appointment.getUserID());
        statement.setInt(11, appointment.getContact_id());
        statement.setInt(12, appointment.getAppointment_id());
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * This method returns True if the appointment is deleted from the database, false otherwise.
     * @param conn The Connection object used to connect to the DB
     * @param appointmentID The Appointment ID of the Appointment to be deleted
     * @return True if deletion completes successfully, false otherwise
     */
    public Boolean delete(Connection conn, int appointmentID) throws SQLException {
        String delStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(conn, delStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, appointmentID);
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    /**
     * This method returns True if the appointment(s) is deleted from the database, false otherwise.
     * @param conn The Connection object used to connect to the DB
     * @param customerID The Customer ID for the appointments to be deleted
     * @return True if deletion completes successfully, false otherwise
     */
    public Boolean delete(Connection conn, String customerID) throws SQLException{
        String delStatement = "DELETE FROM appointments WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, delStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, customerID);
        try{
            statement.execute();
            return true;
        }catch(SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }
}
