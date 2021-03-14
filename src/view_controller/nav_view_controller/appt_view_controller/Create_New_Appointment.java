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

/** This class acts as the handler for the Create_New_Appointment FXML. This controller handles the interface for
 * creating new appointments. */
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

    /** This method populates the Customer ComboBox upon initialization. */
    @FXML
    private void initialize() throws SQLException { populateCustomerComboBox(); }

    /**
     * This method clears all of the JavaFX Gui fields for the Create Appointment page.
     */
    private void clearAllFields(){
        customerComboBox.getItems().clear();
        titleTextField.clear();
        descTextField.clear();
        locationTextField.clear();
        emailTextField.clear();
        apptTypeTextField.clear();
        dateDatePicker.setValue(null);
        startComboBox.getItems().clear();
        endComboBox.getItems().clear();
    }

    /** This method creates the Customer DAO in order to populate the Customer ComboBox. */
    private void populateCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    /** This method populates the Start time ComboBox. If a Start time is already in the database it is excluded from the
     * ComboBox. */
    private void populateStartTimeBox() throws SQLException {
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<LocalDateTime> startTimes = dao.getStartTimes(CONN);
        //Only gives available appointment times for business open hours
        //Business start hours are in EST
        LocalDate chosenDate = dateDatePicker.getValue();
        LocalTime businessStartTime = LocalTime.of(BUSINESS_OPEN, 0);
        ZonedDateTime startTime = ZonedDateTime.of(chosenDate, businessStartTime, ZoneId.of("America/New_York"));
        //Converts business start times from EST to Local
        LocalDateTime estStartDateTime = converter.convertToLocal(startTime);
        LocalTime start = estStartDateTime.toLocalTime();

        //Business end hours are in EST
        LocalTime businessEndTime = LocalTime.of(BUSINESS_CLOSED, 0);
        //Below line keeps user from starting an appointment when the business closes
        businessEndTime = businessEndTime.minusMinutes(APPOINTMENT_LENGTH);
        ZonedDateTime endTime = ZonedDateTime.of(chosenDate, businessEndTime, ZoneId.of("America/New_York"));
        //Converts business end times from EST to Local
        LocalDateTime estEndDateTime = converter.convertToLocal(endTime);
        LocalTime end = estEndDateTime.toLocalTime();

        while (start.isBefore(end.plusSeconds(1))) {
            //If the Start time selected is not within the DB, then add to Combo Box
            if (!startTimes.contains(LocalDateTime.of(dateDatePicker.getValue(), start))) {
                startComboBox.getItems().add(start);
            }
            start = start.plusMinutes(APPOINTMENT_LENGTH);
        }
    }

    /** This method populates the End time ComboBox. End times begin one Appointment_Length after the time in the
     * Start ComboBox. */
    @FXML
    private void populateEndTimeBox(){
        //Start time is already converted from populateStartTimeBox
        LocalDate currentDate = LocalDate.now();
        if (startComboBox.getItems() != null){
            LocalTime start = startComboBox.getValue().plusMinutes(APPOINTMENT_LENGTH);

            //Convert and populate End Time Box -- same as populateStartTimeBox
            LocalTime endTime = LocalTime.of(BUSINESS_CLOSED, 0);
            ZonedDateTime endDateTime = ZonedDateTime.of(currentDate, endTime, ZoneId.of("America/New_York"));
            LocalDateTime estEndDateTime = converter.convertToLocal(endDateTime);
            LocalTime end = estEndDateTime.toLocalTime();

            while(start.isBefore(end.plusSeconds(1))){
                endComboBox.getItems().add(start);
                start = start.plusMinutes(APPOINTMENT_LENGTH);
            }
        }

    }

    /** This method checks to see if a contact already exists within the database for an Appointment and then saves the
     * new Contact if that Contact doesn't exist. */
    private void saveContact() throws SQLException {
        ContactDAO contactDAO = new ContactDAO();
        Contact apptContact = new Contact(customerComboBox.getValue().getCustomerName(), emailTextField.getText());
        if (contactDAO.get(CONN, apptContact) == null){
            contactDAO.save(CONN, apptContact);
        }
    }

    /** Retreives the Contact ID for the Appointment.
     * @return The Contact ID for the Contact with the information provided from the fields in Create_New_Appointment.fxml */
    private int getContactID() throws SQLException{
        ContactDAO contactDAO = new ContactDAO();
        Contact apptContact = new Contact(customerComboBox.getValue().getCustomerName(), emailTextField.getText());
        return contactDAO.get(CONN, apptContact).getContactID();
    }

    /** This function disables weekends from being selected on the DateTimePicker. */
    private void disableWeekends(){
        if((dateDatePicker.getValue().getDayOfWeek().equals(DayOfWeek.SUNDAY)
                || dateDatePicker.getValue().getDayOfWeek().equals(DayOfWeek.SATURDAY))){
            dateDatePicker.getEditor().clear();
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Cannot schedule on Weekends");
            newAlert.setContentText("Please choose a day not on the weekend.");
            newAlert.show();
        }
    }

    /** This function handles the DatePicker object. After selecting a date time, it verifies that the date is not a
     * weekend and then populates the available start times. */
    @FXML
    private void datePickerHandler() throws SQLException {
        if(startComboBox.getItems() != null){
            startComboBox.getItems().clear();
        }
        disableWeekends();
        populateStartTimeBox();
    }

    /** This method handles the "Create Appointment" button. First this method saves the Contact information associated
     * with the Appointment being created. Next, it preps the data by putting each field into it's own respective
     * variable that is then used in an Appointment constructor. Finally, it inserts the information into the
     * database and provides an alert if insertions was successful or unsuccessful. The Start and End ComboBoxes are
     * then repopulated with new times in order to account for the new Appointment being created. */
    @FXML
    private void createAppointment() throws SQLException {
        //Saves Contact Information to contacts table
        saveContact();

        // Prepping data to put into Appointment class
        String title = titleTextField.getText();
        String description = descTextField.getText();
        String location = locationTextField.getText();
        String type = apptTypeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(dateDatePicker.getValue(), startComboBox.getValue());
        LocalDateTime endTime = LocalDateTime.of(dateDatePicker.getValue(), endComboBox.getValue());
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
        clearAllFields();
        populateCustomerComboBox();
    }
}
