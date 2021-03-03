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

public class Create_New_Appointment extends BaseController {

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
    ComboBox<String> startComboBox;

    @FXML
    ComboBox<String> endComboBox;

    @FXML
    Button createApptButton;

    @FXML
    private void initialize() throws SQLException {
        populateCustomerComboBox();
    }

    private void populateCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }
}
