package view_controller.nav_view_controller.cust_view_controller;

import dao.CustomerDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import view_controller.BaseController;

import java.sql.SQLException;

public class Update_Delete_Customer extends BaseController {
    @FXML
    ComboBox<Customer> customerComboBox;

    @FXML
    TextField custIDTF;

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
    Button deleteButton;

    @FXML
    Button updateButton;

    private void setCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    @FXML
    private void initialize() throws SQLException {
        setCustomerComboBox();
    }
}
