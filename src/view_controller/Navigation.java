package view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class Navigation extends BaseController{
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
    Button logOutButton;

    @FXML
    private void customersButtonClicked(){ loadNewScene(stackPane, "nav_view_controller/Customers.fxml"); }

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

    @FXML
    private void logOutButtonClicked(){
        loadNewScene(navigationPane, "Login.fxml");
        LOGGED_IN_USER = null;
    }

}
