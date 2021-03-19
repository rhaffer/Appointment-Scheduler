package view_controller.nav_view_controller.cust_view_controller;

import dao.AppointmentDAO;
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

/**
 * This class handles the updating and deleting behavior for the Customer class.
 */

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

    /**
     * This method handles populating the Customer ComboBox.
     */
    private void setCustomerComboBox() throws SQLException {
        CustomerDAO dao = new CustomerDAO();
        ObservableList<Customer> customers = dao.getAll(CONN);
        customerComboBox.setItems(customers);
    }

    /**
     * This method populates the Table View for Customers.
     */
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

    /**
     * This method populates the Country ComboBox
     * @param countryName The country name
     * @return Country result
     */
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

    /**
     * This method populates the First Level Division ComboBox based off of the country ID
     * @param divisionName The division name
     * @param countryID The country ID
     * @return FirstLevelDivision result
     */
    private FirstLevelDivision populateDivisionCB(String divisionName, int countryID) throws SQLException{
        FirstLevelDivision result = null;
        FLDDAO divisionDAO = new FLDDAO();
        ObservableList<FirstLevelDivision> divisions = divisionDAO.getAllByCountryID(CONN, countryID);
        custFLDCB.setItems(divisions);
        for (FirstLevelDivision division : divisions) {
            if (division.getDivisionName().equals(divisionName)) {
                result = division;
            }
        }
        return result;
    }

    @FXML
    private void resetDivisionCB() throws SQLException {
        custFLDCB.getItems().clear();
        FLDDAO divisionDAO = new FLDDAO();
        ObservableList<FirstLevelDivision> divisions = divisionDAO.getAllByCountryID(CONN,
                custCountryCB.getValue().getCountryID());
        custFLDCB.setItems(divisions);
    }

    /**
     * This method clears all fields on the current screen.
     */
    private void clearFields() {
        custIDTF.setText("");
        custNameTF.setText("");
        custPhoneTF.setText("");
        custAddressTF.setText("");
        custPostalTF.setText("");
        custCountryCB.setValue(null);
        custFLDCB.setValue(null);
    }

    /**
     * This method populates all of the Update Fields when a Customer is selected.
     */
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

    /**
     * This method handles updating the Customer information within the database.
     */
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

    /**
     * This method handles deletion of both the Customer and any associated appointments.
     * Lambda 1 -- filters the response made by the User. This allows for a function chaining for the deleteAlert
     * and allows for a quick response reaction from the program.
     * Lambda 2 -- checks what the response is by the user and either deletes or cancels the operation accordingly.This
     * allows for a function chaining for the deleteAlert and allows for a quick response reaction from the program.
     */
    @FXML
    private void deleteButtonHandler(){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting this customer will also delete all " +
                "associated appointments.");
        deleteAlert.setHeaderText("Are you sure?");
        deleteAlert.showAndWait()
                .filter(response -> response == ButtonType.OK) // Lambda 1
                .ifPresent(response -> {
                    try {
                        deleteCustomer(); // Lambda 2
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });
    }

    /**
     * This method deletes the Customer and associated Appointments.
     */
    @FXML
    private void deleteCustomer() throws SQLException {
        AppointmentDAO apptDao = new AppointmentDAO();
        if (apptDao.delete(CONN, custIDTF.getText())){
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
        }else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText("Error deleting appointments.");
            newAlert.setContentText("Could not delete Customer's appointments");
            newAlert.show();
        }

    }

    /**
     * Initializes the cell value factories for the table view as well as sets the Customer ComboBox and Table View.
     */
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
