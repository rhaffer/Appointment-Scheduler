package view_controller.nav_view_controller;

import dao.AppointmentDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.Appointment;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/** This class acts as the Controller for the Navigation page. */
public class Navigation extends BaseController {
    @FXML
    AnchorPane navigationPane;

    @FXML
    StackPane stackPane;

    @FXML
    Button customersButton;

    @FXML
    Button appointmentsButton;

    @FXML
    Button reportButton;

    /** This method checks for an appointment within 15 minutes of logging in. */
    private void checkAppointments() throws SQLException {
        LocalDateTime currentTime = LocalDateTime.now();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        for (Appointment appt : appointments){
            if (appt.getStart().isAfter(currentTime)) {
                long diff = Math.abs(ChronoUnit.MINUTES.between(currentTime, appt.getStart()));
                Alert timeAlert = new Alert(Alert.AlertType.INFORMATION);
                if (diff <= 15) {
                    timeAlert.setHeaderText("Upcoming Appointment");
                    timeAlert.setContentText("Appointment ID: " + appt.getAppointment_id() + " Start Time: " +
                            appt.getStart() + " is in " + diff + " minutes!");
                    timeAlert.show();
                }
            }
        }
    }

    /** Upon logging in and the Navigation controller loading, the check appointments method will be called. */
    @FXML
    private void initialize() throws SQLException { checkAppointments(); }

    /** This method acts as the handler for the Customers button. If the Customers button is clicked, it loads the
     * Customer FXML. */
    @FXML
    private void customersButtonClicked() { loadNewScene(stackPane, "cust_view_controller/Customers.fxml"); }

    /** This method acts as the handler for the Appointments button. If the Appointments button is clicked, it loads the
     * Appointment FXML. */
    @FXML
    private void appointmentsButtonClicked(){ loadNewScene(stackPane, "appt_view_controller/Appointments.fxml");}

    /** This method acts as the handler for the Reports button. If the Reports button is clicked, it loads the
     * Reports FXML. */
    @FXML
    private void reportButtonClicked(){ loadNewScene(stackPane, "rpt_view_controller/Reports.fxml"); }
}
