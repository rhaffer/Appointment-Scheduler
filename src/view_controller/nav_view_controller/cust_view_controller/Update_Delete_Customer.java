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
    ComboBox<FirstLevelDivision> custFLDCB;

    @FXML
    Button deleteButton;

    @FXML
    Button updateButton;

    private void setCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    private Country populateCountryCB(String countryName) throws SQLException {
        Country result = null;
        CountryDAO countryDAO = new CountryDAO();
        ObservableList<Country> countries = countryDAO.getAll(CONN);
        custCountryCB.setItems(countries);
        for (Country country: countries){
            if (country.getCountryName().equals(countryName)){
                result = country;
            }
        }
        return result;
    }

    private FirstLevelDivision populateDivisionCB(String divisionName, int countryID) throws SQLException{
        FirstLevelDivision result = null;
        FLDDAO divisionDAO = new FLDDAO();
        ObservableList<FirstLevelDivision> divisions = divisionDAO.getAllByCountryID(CONN, countryID);
        custFLDCB.setItems(divisions);
        for (FirstLevelDivision division : divisions){
            if (division.getDivisionName().equals(divisionName)){
                result = division;
            }
        }
        return result;
    }

    private void clearFields(){
        custIDTF.setText("");
        custNameTF.setText("");
        custPhoneTF.setText("");
        custAddressTF.setText("");
        custPostalTF.setText("");
        custCountryCB.setValue(null);
        custFLDCB.setValue(null);
    }
    
    @FXML
    private void populateUpdateFields() throws SQLException {
        Customer updateCustomer = customerComboBox.getValue();
        CustomerDAO dao = new CustomerDAO();
        ObservableList<String> customerInfo = dao.getCustomerDivisionCountry(CONN, updateCustomer);
        custIDTF.setText(customerInfo.get(0));
        custNameTF.setText(customerInfo.get(1));
        custPhoneTF.setText(customerInfo.get(4));
        custAddressTF.setText(customerInfo.get(2));
        custPostalTF.setText(customerInfo.get(3));
        custCountryCB.setValue(populateCountryCB(customerInfo.get(6)));
        custFLDCB.setValue(populateDivisionCB(customerInfo.get(5), custCountryCB.getValue().getCountryID()));
    }

    @FXML
    private void updateCustomer() throws SQLException{
        CustomerDAO dao = new CustomerDAO();
        if(dao.update(CONN, customerComboBox.getValue(), custFLDCB.getValue(), LOGGED_IN_USER)){
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setHeaderText("Customer Updated");
            newAlert.setContentText("Customer updated successfully");
            newAlert.show();
        }else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Customer Not Updated");
            newAlert.setContentText("Customer not updated!");
            newAlert.show();
        }
    }

    @FXML
    private void deleteCustomer() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        if(dao.delete(CONN, Integer.parseInt(custIDTF.getText()))){
            setCustomerComboBox();
            clearFields();
            customerComboBox.setValue(null);
            Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
            newAlert.setHeaderText("Customer Deleted");
            newAlert.setContentText("Customer deleted successfully!");
            newAlert.show();
        }else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Error deleting customer");
            newAlert.setContentText("Customer not deleted successfully");
            newAlert.show();
        }
    }

    @FXML
    private void initialize() throws SQLException {
        setCustomerComboBox();
    }
}
