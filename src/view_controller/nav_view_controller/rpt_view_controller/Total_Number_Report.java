package view_controller.nav_view_controller.rpt_view_controller;

import dao.AppointmentDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Appointment;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

/**
 * This class handles the Total Number Report that describes appointments by Type and by Month.
 */
public class Total_Number_Report extends BaseController {
    @FXML
    TextArea typeTextArea;

    @FXML
    TextArea monthTextArea;

    /**
     * Initializes the data.
     */
    @FXML
    private void initialize() throws SQLException {
        getUniqueApptTypes();
        getUniqueApptMonths();
    }

    /**
     * Returns the number of appointments by Type and inserts into TextArea
     */
    private void getUniqueApptTypes() throws SQLException {
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        Set<String> types = new HashSet<>();
        StringBuilder textAreaString = new StringBuilder();
        int type_count= 0;
        for (Appointment appointment: appointments){
            types.add(appointment.getType());
        }
        for (String type : types){
            for (Appointment appointment : appointments){
                if (appointment.getType().equals(type)){
                    type_count += 1;
                }
            }
            textAreaString.append("Appointment Type: ").append(type).append(" \nTotal Count: " +
                    "").append(type_count).append("\n\n");
            type_count = 0;
        }
        typeTextArea.setText(String.valueOf(textAreaString));
    }

    /**
     * Returns the number of appointments by Month and inserts into TextArea
     */
    private void getUniqueApptMonths() throws SQLException{
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        Set<Month> months = new HashSet<>();
        StringBuilder textAreaString = new StringBuilder();
        int month_count = 0;
        for (Appointment appointment: appointments){
            months.add(appointment.getStart().getMonth());
        }
        for (Month month : months){
            for (Appointment appointment : appointments){
                if (appointment.getStart().getMonth().equals(month)){
                    month_count += 1;
                }
            }
            textAreaString.append("Month: ").append(month.toString()).append(" \nTotal Count: " +
                    "").append(month_count).append("\n\n");
            month_count = 0;
        }
        monthTextArea.setText(String.valueOf(textAreaString));
    }
}

