package view_controller.nav_view_controller.cust_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

/** This class handles the Customer navigation FXML. */
public class Customers extends BaseController {
    // Completed.
    @FXML
    StackPane customerStackPane;

    @FXML
    Label customerLabel;

    @FXML
    Button createNewButton;

    @FXML
    Button updateDeleteButton;

    /** This method loads the Create_New_Customer.fxml if the Create New button is clicked. */
    @FXML
    private void createNewButtonClicked(){ loadNewScene(customerStackPane, "Create_New_Customer.fxml"); }

    /** This method loads the Update_Delete_Customer.fxml if the Update/Delete button is clicked. */
    @FXML
    private void updateDeleteButtonClicked(){ loadNewScene(customerStackPane, "Update_Delete_Customer.fxml"); }
}
