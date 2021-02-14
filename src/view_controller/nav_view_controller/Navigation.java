package view_controller.nav_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

public class Navigation extends BaseController {
    @FXML
    AnchorPane navigationPane;

    @FXML
    StackPane stackPane;

    @FXML
    Button customersButton;

    @FXML
    Button appointmentsButton;

    @FXML
    Button calendarButton;

    @FXML
    Button reportButton;

    @FXML
    private void customersButtonClicked() {
        loadNewScene(stackPane, "cust_view_controller/Customers.fxml");
    }

    @FXML
    private void appointmentsButtonClicked(){
        System.out.println("Appointments clicked.");
    }

    @FXML
    private void calendarButtonClicked(){
        System.out.println("Calendar clicked.");
    }

    @FXML
    private void reportButtonClicked(){
        System.out.println("Report button clicked.");
    }

}
