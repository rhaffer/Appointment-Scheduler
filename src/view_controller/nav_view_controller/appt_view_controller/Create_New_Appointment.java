package view_controller.nav_view_controller.appt_view_controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Appointment;
import model.Contact;
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
            //TODO check DB to see if times overlap -- don't allow those times in
        }
    }

    @FXML
    private void populateEndTimeBox(){
        //Start time is already converted from populateStartTimeBox
        LocalDate currentDate = LocalDate.now();
        LocalTime start = startComboBox.getValue().plusMinutes(APPOINTMENT_LENGTH);

        //Convert and populate End Time Box -- same as populateStartTimeBox
        LocalTime endTime = LocalTime.of(BUSINESS_CLOSED, 0);
        LocalDateTime endDateTime = LocalDateTime.of(currentDate, endTime);
        ZonedDateTime estEndDateTime = convertEstToLocalTime(endDateTime);
        LocalTime end = estEndDateTime.toLocalTime();

        while(start.isBefore(end.plusSeconds(1))){
            endComboBox.getItems().add(start);
            start = start.plusMinutes(APPOINTMENT_LENGTH);
        }
    }

    private void saveContact() throws SQLException {
        ContactDAO contactDAO = new ContactDAO();
        Contact apptContact = new Contact(customerComboBox.getValue().getCustomerName(), emailTextField.getText());
        if (contactDAO.get(CONN, apptContact) == null){
            contactDAO.save(CONN, apptContact);
        }
    }

    private int getContactID() throws SQLException{
        ContactDAO contactDAO = new ContactDAO();
        Contact apptContact = new Contact(customerComboBox.getValue().getCustomerName(), emailTextField.getText());
        return contactDAO.get(CONN, apptContact).getContactID();
    }

    @FXML
    private void createAppointment() throws SQLException {
        //#TODO Add in exception handling for
        // 1. Weekends
        // 2. Overlapping
        saveContact();

        // Prepping data to put into Appointment class
        String title = titleTextField.getText();
        String description = descTextField.getText();
        String location = locationTextField.getText();
        String type = apptTypeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), startComboBox.getValue());
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), endComboBox.getValue());
        LocalDateTime create_date = LocalDateTime.now();
        String created_by = LOGGED_IN_USER.getUserName();
        String last_update = LocalDateTime.now().toString();
        String last_updated_by = LOGGED_IN_USER.getUserName();
        int customer_id = customerComboBox.getValue().getCustomerID();
        int user_id = LOGGED_IN_USER.getUserId();
        int contact_id = getContactID();

        //Creating new Appointment
        Appointment appt = new Appointment(title, description, location, type, startTime, endTime, create_date,
                created_by, last_update, last_updated_by, customer_id, user_id, contact_id);

        //Inserting into DB
        AppointmentDAO dao = new AppointmentDAO();
        if (dao.save(CONN, appt)) {
            Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
            saveAlert.setHeaderText("Appointment Created!");
            saveAlert.setContentText("Appointment created successfully!");
            saveAlert.show();
        } else {
            Alert saveAlert = new Alert(Alert.AlertType.ERROR);
            saveAlert.setHeaderText("Save Unsuccessful");
            saveAlert.setContentText("Error creating appointment!");
            saveAlert.show();
        }
    }
}
