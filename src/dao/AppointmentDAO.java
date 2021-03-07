package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AppointmentDAO {
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public ObservableList<LocalDateTime> getStartTimes(Connection conn) throws SQLException{
        ObservableList<LocalDateTime> startTimes = FXCollections.observableArrayList();
        String selectStatement = "SELECT Start FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()){
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            startTimes.add(start);
        }
        return startTimes;
    }

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
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
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

    public Boolean save(Connection conn, Appointment appt) throws SQLException{
        String insertStatement = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, " +
                "Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, appt.getTitle());
        statement.setString(2, appt.getDescription());
        statement.setString(3, appt.getLocation());
        statement.setString(4, appt.getType());
        statement.setString(5, appt.getStartTime().toString());
        statement.setString(6, appt.getEndTime().toString());
        statement.setString(7, appt.getCreateDate().toString());
        statement.setString(8, appt.getCreatedBy());
        statement.setString(9, appt.getLastUpdatedBy());
        statement.setInt(10, appt.getCustomerID());
        statement.setInt(11, appt.getUserID());
        statement.setInt(12, appt.getContactID());
        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }
}
