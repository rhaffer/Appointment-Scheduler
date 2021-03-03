package view_controller.nav_view_controller.appt_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

public class Appointments extends BaseController {
    @FXML
    StackPane apptStackPane;

    @FXML
    Button createNewButton;

    @FXML
    Button updateDeleteButton;

    public void createNewButtonClicked(){
        loadNewScene(apptStackPane, "Create_New_Appointment.fxml");
    }

    public void updateDeleteButtonClicked(){
        loadNewScene(apptStackPane, "Update_Delete_Appointment.fxml");
    }
}
