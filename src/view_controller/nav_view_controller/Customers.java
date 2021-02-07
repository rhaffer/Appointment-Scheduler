package view_controller.nav_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

public class Customers extends BaseController {

    @FXML
    StackPane customerStackPane;

    @FXML
    Label customerLabel;

    @FXML
    Button createNewButton;

    @FXML
    Button updateDeleteButton;

    @FXML
    private void createNewButtonClicked(){ loadNewScene(customerStackPane, "Create_New_Customer.fxml"); }

    @FXML
    private void updateDeleteButtonClicked(){ loadNewScene(customerStackPane, "Update_Delete_Customer.fxml"); }
}
