package view_controller.nav_view_controller.cust_view_controller;

import dao.CountryDAO;
import dao.CustomerDAO;
import dao.FLDDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Create_New_Customer extends BaseController {
    @FXML
    TextField custNameTF;

    @FXML
    TextField custPhoneTF;

    @FXML
    TextField custAddressTF;

    @FXML
    TextField custPostalTF;

    @FXML
    ComboBox<Country> custCountryCB;

    @FXML
    ComboBox<FirstLevelDivision> custFldCB;

    @FXML
    Button custSubmitButton;

    private Boolean checkEntryFields() {
        return !(custNameTF.getText().equals("") | custAddressTF.getText().equals("") | custPostalTF.getText().equals("") |
                custCountryCB.getItems().isEmpty() | custFldCB.getItems().isEmpty());
    }

    private Customer createNewCustomer() {
        Customer customerToAdd = new Customer();
        customerToAdd.setCustomerName(custNameTF.getText());
        customerToAdd.setAddress(custAddressTF.getText());
        customerToAdd.setPostalCode(custPostalTF.getText());
        customerToAdd.setPhoneNumber(custPhoneTF.getText());
        customerToAdd.setCreateDate(LocalDateTime.now().toString());
        customerToAdd.setCreatedBy(LOGGED_IN_USER.getUserName());
        customerToAdd.setLastUpdatedBy(LOGGED_IN_USER.getUserName());
        customerToAdd.setDivisionID(custFldCB.getValue().getDivisionID());
        return customerToAdd;
    }

    @FXML
    private void initialize() throws SQLException {
        generateCountryComboBox();
    }

    private void generateCountryComboBox() throws SQLException {
        CountryDAO dao = new CountryDAO();
        ObservableList<Country> countries = dao.getAll(CONN);
        custCountryCB.setItems(countries);
    }

    @FXML
    private void generateDivisionComboBox() throws SQLException {
        FLDDAO dao = new FLDDAO();
        ObservableList<FirstLevelDivision> divisions = dao.getAllByCountryID(CONN, custCountryCB.getValue().getCountryID());
        custFldCB.setItems(divisions);
    }

    @FXML
    private void saveNewCustomer() throws SQLException {
        if (checkEntryFields()) {
            CustomerDAO dao = new CustomerDAO();
            Customer customer = new Customer(custNameTF.getText(), custAddressTF.getText(), custPostalTF.getText(),
                    custPhoneTF.getText(), LocalDateTime.now().toString(), LOGGED_IN_USER.getUserName(),
                    LOGGED_IN_USER.getUserName(), custFldCB.getValue().getDivisionID());
            if (dao.save(CONN, customer)) {
                Alert saveAlert = new Alert(Alert.AlertType.INFORMATION);
                saveAlert.setHeaderText("Customer Created!");
                saveAlert.setContentText("Customer created successfully!");
                saveAlert.show();
            } else {
                Alert saveAlert = new Alert(Alert.AlertType.ERROR);
                saveAlert.setHeaderText("Save Unsuccessful");
                saveAlert.setContentText("Error creating customer!");
                saveAlert.show();
            }
        } else {
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Missing Fields");
            newAlert.setContentText("All fields must be filled in!");
            newAlert.show();
        }
    }
}
