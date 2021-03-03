package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentDao {
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

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
            String start = rs.getString("Start");
            String end = rs.getString("End");
            String createDate = rs.getString("Create_Date");
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
}
