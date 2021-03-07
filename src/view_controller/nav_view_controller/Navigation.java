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
    Button calendarButton;

    @FXML
    Button reportButton;

    private void checkAppointments() throws SQLException {
        // Checks for appointments within 15 minutes on login
        LocalDateTime currentTime = LocalDateTime.now();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        for (Appointment appt : appointments){
            if (appt.getStartTime().isAfter(currentTime)){
                long diff = Math.abs(ChronoUnit.MINUTES.between(currentTime, appt.getStartTime()));
                if (diff <= 15){
                    Alert timeAlert = new Alert(Alert.AlertType.INFORMATION);
                    timeAlert.setHeaderText("Upcoming Appointment");
                    timeAlert.setContentText("There is an appointment coming up in " + diff + " minutes!");
                    timeAlert.show();
                }
            }
        }
    }

    @FXML
    private void initialize() throws SQLException {
        checkAppointments();
    }
    @FXML
    private void customersButtonClicked() {
        loadNewScene(stackPane, "cust_view_controller/Customers.fxml");
    }

    @FXML
    private void appointmentsButtonClicked(){ loadNewScene(stackPane, "appt_view_controller/Appointments.fxml");}

    @FXML
    private void calendarButtonClicked(){
        loadNewScene(stackPane, "cal_view_controller/Calendar.fxml");
    }

    @FXML
    private void reportButtonClicked(){
        System.out.println("Report button clicked.");
    }

}
