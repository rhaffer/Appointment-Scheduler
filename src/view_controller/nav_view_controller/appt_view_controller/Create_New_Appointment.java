package view_controller.nav_view_controller.appt_view_controller;

import dao.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Customer;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.*;
import java.util.TimeZone;

public class Create_New_Appointment extends BaseController {
    int APPOINTMENT_LENGTH = 30;
    //Business hours are 8AM to 10PM EST
    int BUSINESS_OPEN = 8;
    int BUSINESS_CLOSED = 22;

    @FXML
    ComboBox<Customer> customerComboBox;

    @FXML
    TextField appointmentIDTextField;

    @FXML
    TextField titleTextField;

    @FXML
    TextField descTextField;

    @FXML
    TextField locationTextField;

    @FXML
    TextField emailTextField;

    @FXML
    TextField apptTypeTextField;

    @FXML
    DatePicker dateDatePicker;

    @FXML
    ComboBox<LocalTime> startComboBox;

    @FXML
    ComboBox<LocalTime> endComboBox;

    @FXML
    Button createApptButton;

    @FXML
    private void initialize() throws SQLException {
        populateCustomerComboBox();
        populateStartTimeBox();
    }

    private void populateCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    private ZonedDateTime convertEstToLocalTime(LocalDateTime date){
        //Converts a LocalDateTime to EST and returns that time in Local
        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZonedDateTime estZDT = ZonedDateTime.of(date, estZoneId);
        // Returns a ZDT object in the local time zone
        return estZDT.withZoneSameInstant(localZoneId);
    }

    private void populateStartTimeBox(){
        //Only gives available appointment times for business open hours
        LocalDate currentDate = LocalDate.now();
        LocalTime startTime = LocalTime.of(BUSINESS_OPEN, 0);
        //Turns current date and business open hours to LDT
        LocalDateTime startDateTime = LocalDateTime.of(currentDate, startTime);
        //Converts that time from EST to Local
        ZonedDateTime estStartDateTime = convertEstToLocalTime(startDateTime);
        LocalTime start = estStartDateTime.toLocalTime();

        LocalTime endTime = LocalTime.of(BUSINESS_CLOSED, 0);
        //Turns current date and business closed hours to LDT
        LocalDateTime endDateTime = LocalDateTime.of(currentDate, endTime);
        //Converts that time from EST to Local
        ZonedDateTime estEndDateTime = convertEstToLocalTime(endDateTime);
        LocalTime end = estEndDateTime.toLocalTime();

        while(start.isBefore(end.plusSeconds(1))){
            startComboBox.getItems().add(start);
            start = start.plusMinutes(APPOINTMENT_LENGTH);
            //TODO check DB to see if times overlap
        }
    }

    private void populateEndTimeBox(){
        //TODO have this populate once start time is selected and then add all the conversions
        LocalTime start = startComboBox.getValue();
        LocalTime end = LocalTime.of(12, 0);
        while(start.isBefore(end.plusSeconds(1))){
            endComboBox.getItems().add(start);
            start = start.plusMinutes(APPOINTMENT_LENGTH);
        }
    }
}
