package view_controller.nav_view_controller.cust_view_controller;

import dao.CountryDAO;
import dao.CustomerDAO;
import dao.FLDDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import model.Country;
import model.Customer;
import model.FirstLevelDivision;
import view_controller.BaseController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    TableView<Map<String, Object>> updateCustomerTable;

    @FXML
    TableColumn<Map, String> nameColumn;

    @FXML
    TableColumn<Map, String> phoneColumn;

    @FXML
    TableColumn<Map, String> postalColumn;

    @FXML
    TableColumn<Map, String> countryColumn;

    @FXML
    TableColumn<Map, String> stateColumn;

    @FXML
    Button deleteButton;

    @FXML
    Button updateButton;

    private void setCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    private void setCustomerTableView() throws SQLException {
        ObservableList<Map<String, Object>> allCustomers = FXCollections.observableArrayList();
        CustomerDAO dao = new CustomerDAO();

        ObservableList<Customer> customers = dao.getAll(CONN);
        for (Customer customer : customers) {
            ObservableList<String> customerInfo = dao.getCustomerDivisionCountry(CONN, customer);
            Map<String, Object> item = new HashMap<>();
            item.put("name_column", customerInfo.get(1));
            item.put("phone_column", customerInfo.get(4));
            item.put("postal_column", customerInfo.get(3));
            item.put("country_column", customerInfo.get(6));
            item.put("state_column", customerInfo.get(5));
            allCustomers.add(item);
        }
        updateCustomerTable.setItems(allCustomers);

    }

    private Country populateCountryCB(String countryName) throws SQLException {
        Country result = null;
        CountryDAO countryDAO = new CountryDAO();
        ObservableList<Country> countries = countryDAO.getAll(CONN);
        custCountryCB.setItems(countries);
        for (Country country : countries) {
            if (country.getCountryName().equals(countryName)) {
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
        // This causes a null pointer exception to be caught when deleting a Customer
        // due to it being an #onAction event. Does not affect overall performance of application
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
        setCustomerTableView();
    }

    @FXML
    private void deleteCustomer() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        if(dao.delete(CONN, Integer.parseInt(custIDTF.getText()))){
            setCustomerComboBox();
            setCustomerTableView();
            clearFields();
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
        nameColumn.setCellValueFactory(new MapValueFactory<>("name_column"));
        phoneColumn.setCellValueFactory(new MapValueFactory<>("phone_column"));
        postalColumn.setCellValueFactory(new MapValueFactory<>("postal_column"));
        countryColumn.setCellValueFactory(new MapValueFactory<>("country_column"));
        stateColumn.setCellValueFactory(new MapValueFactory<>("state_column"));
        setCustomerComboBox();
        setCustomerTableView();
    }
}
