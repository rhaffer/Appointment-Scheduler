package view_controller.nav_view_controller.appt_view_controller;

import dao.AppointmentDAO;
import dao.ContactDAO;
import dao.CustomerDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * This class handles the update/delete functionality for Appointments.
 */
public class Update_Delete_Appointment extends BaseController {

    private static final int APPOINTMENT_LENGTH = 30;
    private static final int BUSINESS_OPEN = 8;
    private static final int BUSINESS_CLOSED = 22;

    @FXML
    ComboBox<User> userComboBox;

    @FXML
    ComboBox<Customer> customerComboBox;

    @FXML
    ComboBox<Contact> contactNameComboBox;

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
    Tab weekViewTab;

    @FXML
    Tab monthViewTab;

    @FXML
    Tab allAppointmentsTab;

    @FXML
    TableView<Appointment> weekTableView;

    @FXML
    TableColumn<Appointment, String> weekAppointmentColumn;

    @FXML
    TableColumn<Appointment, String> weekTitleColumn;

    @FXML
    TableColumn<Appointment, String> weekDescriptionColumn;

    @FXML
    TableColumn<Appointment, String> weekLocationColumn;

    @FXML
    TableColumn<Appointment, String> weekContactColumn;

    @FXML
    TableColumn<Appointment, String> weekStartColumn;

    @FXML
    TableColumn<Appointment, String> weekEndColumn;

    @FXML
    TableColumn<Appointment, String> weekCustomerIDColumn;

    @FXML
    TableView<Appointment> monthTableView;

    @FXML
    TableColumn<Appointment, String> monthAppointmentColumn;

    @FXML
    TableColumn<Appointment, String> monthTitleColumn;

    @FXML
    TableColumn<Appointment, String> monthDescriptionColumn;

    @FXML
    TableColumn<Appointment, String> monthLocationColumn;

    @FXML
    TableColumn<Appointment, String> monthContactColumn;

    @FXML
    TableColumn<Appointment, String> monthStartColumn;

    @FXML
    TableColumn<Appointment, String> monthEndColumn;

    @FXML
    TableColumn<Appointment, String> monthCustomerIDColumn;

    @FXML
    TableView<Appointment> allAppointmentsTableView;

    @FXML
    TableColumn<Appointment, String> allAppointmentIDColumn;

    @FXML
    TableColumn<Appointment, String> allTitleColumn;

    @FXML
    TableColumn<Appointment, String> allDescriptionColumn;

    @FXML
    TableColumn<Appointment, String> allLocationColumn;

    @FXML
    TableColumn<Appointment, String> allContactColumn;

    @FXML
    TableColumn<Appointment, String> allStartColumn;

    @FXML
    TableColumn<Appointment, String> allEndColumn;

    @FXML
    TableColumn<Appointment, String> allCustomerIDColumn;

    @FXML
    Button updateButton;

    @FXML
    Button deleteButton;

    /**
     * This method sets all of the Cell Value Factories for the All Appointments table.
     */
    private void setAllColumnsCellValueFactory() {
        allAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        allTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        allContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        allStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        allEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        allCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    /**
     * This method sets the Cell Value Factory for the Month table view.
     */
    private void setMonthCellValueFactory() {
        monthAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        monthTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        monthStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        monthCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    /**
     * This method sets the Week Cell Value Factory for the Week table view.
     */
    private void setWeekCellValueFactory() {
        weekAppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        weekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        weekStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        weekEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        weekCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
    }

    /**
     * This method clears all of the fields on the Update Appointment page.
     */
    private void clearAllFields() {
        userComboBox.getItems().clear();
        contactNameComboBox.getItems().clear();
        customerComboBox.getItems().clear();
        appointmentIDTextField.setText(null);
        titleTextField.clear();
        descTextField.clear();
        locationTextField.clear();
        emailTextField.clear();
        apptTypeTextField.clear();
        dateDatePicker.setValue(null);
        startComboBox.getItems().clear();
        startComboBox.setValue(null);
        endComboBox.getItems().clear();
    }

    /**
     * This method sets all of the cell value factories for all table views as well as populates all of the data for each
     * table view.
     */
    @FXML
    private void initialize() throws SQLException {
        setAllColumnsCellValueFactory();
        setMonthCellValueFactory();
        setWeekCellValueFactory();
        populateAllTableView();
        populateMonthTableView();
        populateWeekTableView();
    }

    /**
     * This method populates the All Appointments table view.
     */
    @FXML
    private void populateAllTableView() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> items = dao.getAll(CONN);
        appointments.addAll(items);
        allAppointmentsTableView.setItems(appointments);
    }

    /**
     * This method populates the Month table view.
     */
    @FXML
    private void populateMonthTableView() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> items = dao.getAll(CONN);
        for (Appointment item : items) {
            if (item.getStart().getMonthValue() == LocalDateTime.now().getMonthValue()) {
                appointments.add(item);
            }
        }
        monthTableView.setItems(appointments);
    }

    /**
     * This method populates the fields for the Week table view.
     */
    @FXML
    private void populateWeekTableView() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> items = dao.getAll(CONN);
        LocalDate current = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = current.get(weekFields.weekOfWeekBasedYear());
        for (Appointment item : items) {
            LocalDate itemDate = item.getStart().toLocalDate();
            int itemWeekNumber = itemDate.get(weekFields.weekOfWeekBasedYear());
            if (weekNumber == itemWeekNumber) {
                appointments.add(item);
            }
        }
        weekTableView.setItems(appointments);
    }

    /**
     * This method handles populating the fields if an entry from the All Appointment table is selected.
     */
    @FXML
    private void populateFieldsAllAppt() throws SQLException {
        if (allAppointmentsTableView.getSelectionModel().getSelectedItem() != null) {
            setFields(allAppointmentsTableView);
        }
    }

    /**
     * This method handles populating the fields if an entry from the Month table is selected.
     */
    @FXML
    private void populateFieldsMonthView() throws SQLException {
        if (monthTableView.getSelectionModel().getSelectedItem() != null) {
            setFields(monthTableView);
        }
    }

    /**
     * This method handles populating the fields if an entry from the Week table is selected.
     */
    @FXML
    private void populateFieldsWeekView() throws SQLException {
        if (weekTableView.getSelectionModel().getSelectedItem() != null) {
            setFields(weekTableView);
        }
    }

    /**
     * This method takes a Table and sets all of the JavaFX Gui fields to autofill the information from a selection
     * made from the passed table.
     *
     * @param table The table that the selection is made from
     */
    private void setFields(TableView<Appointment> table) throws SQLException {
        Appointment appointment = table.getSelectionModel().getSelectedItem();
        clearAllFields();
        populateUserComboBox();
        populateContactComboBox();
        populateCustomerComboBox();
        populateStartTimeBox(appointment);

        for (User user : userComboBox.getItems()) {
            if (user.getUserId() == appointment.getUserID()) {
                userComboBox.setValue(user);
            }
        }

        for (Contact contact : contactNameComboBox.getItems()) {
            if (contact.getContactID() == appointment.getContact_id()) {
                contactNameComboBox.setValue(contact);
            }
        }

        for (Customer customer : customerComboBox.getItems()) {
            if (customer.getCustomerID() == appointment.getCustomer_id()) {
                customerComboBox.setValue(customer);
            }
        }
        appointmentIDTextField.setText(String.valueOf(appointment.getAppointment_id()));
        titleTextField.setText(appointment.getTitle());
        descTextField.setText(appointment.getDescription());
        locationTextField.setText(appointment.getLocation());
        emailTextField.setText(getContactEmail(appointment));
        apptTypeTextField.setText(appointment.getType());
        dateDatePicker.setValue(appointment.getStart().toLocalDate());
        endComboBox.setValue(appointment.getEnd().toLocalTime());
    }

    /**
     * This method populates the Contact ComboBox with all available Contacts
     */
    private void populateContactComboBox() throws SQLException {
        ContactDAO dao = new ContactDAO();
        ObservableList<Contact> contacts = dao.getAll(CONN);
        contactNameComboBox.setItems(contacts);
    }

    /**
     * This method populates the User ComboBox with all available Users
     */
    private void populateUserComboBox() throws SQLException {
        UserDAO dao = new UserDAO();
        ObservableList<User> users = dao.getAll(CONN);
        userComboBox.setItems(users);
    }

    /**
     * This method populates the Customer ComboBox with all available Customers
     */
    private void populateCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    /**
     * This method looks for a Contact's email address from the DB
     *
     * @param appointment The appointment that holds the Contact information
     * @return String contact_email
     */
    private String getContactEmail(Appointment appointment) throws SQLException {
        ContactDAO contactDAO = new ContactDAO();
        return contactDAO.get(CONN, appointment.getContact_id()).getEmail();
    }

    /**
     * This method handles populating the available Start times in the StartTime ComboBox. Time is converted to
     * only allow Start times within business hours. Those available times are then converted to Local time.
     *
     * @param appointment The Appointment that holds the Start LocalDateTime information
     */
    private void populateStartTimeBox(Appointment appointment) throws SQLException {
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<LocalDateTime> startTimes = dao.getStartTimes(CONN);
        //Business start hours are in EST
        LocalDate chosenDate = appointment.getStart().toLocalDate();
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
            //If the Start time selected is not within the DB, then add to ComboBox
            if (!startTimes.contains(LocalDateTime.of(appointment.getStart().toLocalDate(), start))) {
                startComboBox.getItems().add(start);
            }
            start = start.plusMinutes(APPOINTMENT_LENGTH);
        }
        startComboBox.setValue(appointment.getStart().toLocalTime());
        populateEndTimeBox(appointment);
    }

    /**
     * This method handles populating the available End times in the EndTime ComboBox. Time is converted to
     * only allow end times within business hours. Those available times are then converted to Local time.
     *
     * @param appointment The appointment that holds the end time data
     */
    @FXML
    private void populateEndTimeBox(Appointment appointment) {
        //Start time is already converted from populateStartTimeBox
        LocalDate currentDate = appointment.getEnd().toLocalDate();
        if (startComboBox.getItems() != null) {
            LocalTime start = startComboBox.getValue().plusMinutes(APPOINTMENT_LENGTH);

            //Convert and populate End Time Box -- same as populateStartTimeBox
            LocalTime endTime = LocalTime.of(BUSINESS_CLOSED, 0);
            ZonedDateTime endDateTime = ZonedDateTime.of(currentDate, endTime, ZoneId.of("America/New_York"));
            LocalDateTime estEndDateTime = converter.convertToLocal(endDateTime);
            LocalTime end = estEndDateTime.toLocalTime();

            while (start.isBefore(end.plusSeconds(1))) {
                endComboBox.getItems().add(start);
                start = start.plusMinutes(APPOINTMENT_LENGTH);
            }
        }
    }

    /**
     * This method checks to see if a contact already exists within the database for an Appointment and then saves the
     * new Contact if that Contact doesn't exist.
     */
    private void saveContact() throws SQLException {
        ContactDAO contactDAO = new ContactDAO();
        Contact apptContact = new Contact(contactNameComboBox.getValue().getContactName(), emailTextField.getText());
        if (contactDAO.get(CONN, apptContact) == null) {
            contactDAO.save(CONN, apptContact);
        }
    }

    /**
     * This method checks to see if the potential Appointment to be created overlaps with another existing Appointment.
     *
     * @param curAppt The appointment that is currently being updated. This ensures that a false overlapping flag
     *                doesn't occur.
     * @return True if appointments overlap, false otherwise
     */
    private Boolean checkOverlapping(Appointment curAppt) throws SQLException {
        boolean overlaps = false;
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        LocalDateTime appointmentStartDateTime = LocalDateTime.of(dateDatePicker.getValue(), startComboBox.getValue());
        LocalDateTime appointmentEndDatetime = LocalDateTime.of(dateDatePicker.getValue(), endComboBox.getValue());
        for (Appointment appointment : appointments) {
            if (!(appointment.getAppointment_id() == curAppt.getAppointment_id())) {
                if (appointmentStartDateTime.isBefore(appointment.getEnd()) &&
                        appointment.getStart().isBefore(appointmentEndDatetime)) {
                    overlaps = true;
                    break;
                }
            }

        }
        return overlaps;
    }

    /**
     * This method handles the Delete button. If an Appointment is successfully deleted, then an alert is raised.
     * After deletion, all fields and tables are reset.
     */
    @FXML
    private void deleteButtonHandler() throws SQLException {
        AppointmentDAO dao = new AppointmentDAO();

        if (dao.delete(CONN, Integer.parseInt(appointmentIDTextField.getText()))) {
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setHeaderText("Appointment Deleted");
            newAlert.setContentText(appointmentIDTextField.getText() + " " + apptTypeTextField.getText() +
                    " has been cancelled.");
            newAlert.show();
            populateAllTableView();
            populateMonthTableView();
            populateWeekTableView();
            clearAllFields();
        } else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Error deleting appointment");
            newAlert.setContentText("Appointment not deleted successfully");
            newAlert.show();
        }
    }


    /**
     * This method handles the "Update" button. It checks to see if a contact exists, and if it does, saves the Contact.
     * After that, it updates the appointment information as entered into the associated fields.
     */
    @FXML
    private void updateButtonHandler() throws SQLException {
        saveContact();
        ContactDAO contactDAO = new ContactDAO();
        Contact contact = contactDAO.get(CONN, contactNameComboBox.getValue().getContactName(), emailTextField.getText());
        // Prepping data to put into Appointment class
        String title = titleTextField.getText();
        String description = descTextField.getText();
        String location = locationTextField.getText();
        String type = apptTypeTextField.getText();
        LocalDateTime startTime = LocalDateTime.of(dateDatePicker.getValue(), startComboBox.getValue());
        LocalDateTime endTime = LocalDateTime.of(dateDatePicker.getValue(), endComboBox.getValue());
        String last_update = LocalDateTime.now().toString();
        String last_updated_by = LOGGED_IN_USER.getUserName();
        int customer_id = customerComboBox.getValue().getCustomerID();
        int user_id = userComboBox.getValue().getUserId();
        int contact_id = contact.getContactID();

        //Creating new Appointment
        Appointment appt = new Appointment(Integer.parseInt(appointmentIDTextField.getText()), title, description, location, type, startTime, endTime, last_update,
                last_updated_by, customer_id, user_id, contact_id);

        if (!checkOverlapping(appt)) {
            AppointmentDAO dao = new AppointmentDAO();
            if (dao.update(CONN, appt)) {
                Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                saveAlert.setHeaderText("Appointment Updated!");
                saveAlert.setContentText("Appointment updated successfully!");
                saveAlert.show();
                populateAllTableView();
                populateMonthTableView();
                populateWeekTableView();
                clearAllFields();
            } else {
                Alert saveAlert = new Alert(Alert.AlertType.ERROR);
                saveAlert.setHeaderText("Update Unsuccessful");
                saveAlert.setContentText("Error updating appointment!");
                saveAlert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Appointments overlap!");
            alert.setContentText("Error updating appointments. One or more appointments overlap.");
            alert.show();
        }
    }
}
