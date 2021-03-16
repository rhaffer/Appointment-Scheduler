package view_controller.nav_view_controller.appt_view_controller;

import dao.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Customer;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.LocalTime;

/**
 * This class handles the update/delete functionality for Appointments.
 */
public class Update_Delete_Appointment extends BaseController {

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
    Tab weekViewTab;

    @FXML
    Tab monthViewTab;

    @FXML
    Tab allAppointmentsTab;

    @FXML
    TableView<Appointment> weekTableView;

    @FXML
    TableView<Appointment> monthTableView;

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

    @FXML
    private void initialize() throws SQLException {
        setAllColumnsCellValueFactory();
        populateAllTableView();
    }

    private void populateAllTableView() throws SQLException {
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> items = dao.getAll(CONN);
        appointments.addAll(items);
        allAppointmentsTableView.setItems(appointments);
    }
}
