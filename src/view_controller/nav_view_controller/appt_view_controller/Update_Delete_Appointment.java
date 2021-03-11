package view_controller.nav_view_controller.appt_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Appointment;
import model.Customer;
import org.w3c.dom.Text;

import java.time.LocalTime;

/** This class handles the update/delete functionality for Appointments. */
public class Update_Delete_Appointment {

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
    Button updateButton;

    @FXML
    Button deleteButton;






}
