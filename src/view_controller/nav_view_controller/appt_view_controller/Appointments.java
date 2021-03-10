package view_controller.nav_view_controller.appt_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

/** This class acts as the handler for the Appointments FXML. This controller is used as a way to navigate between
 * creating and updating/deleting Appointments. */
public class Appointments extends BaseController {
    @FXML
    StackPane apptStackPane;

    @FXML
    Button createNewButton;

    @FXML
    Button updateDeleteButton;

    /** This method handles the Create New button being clicked. If clicked, the Create New Appointment FXML is loaded. */
    public void createNewButtonClicked(){
        loadNewScene(apptStackPane, "Create_New_Appointment.fxml");
    }

    /** This method handles the Update/Delete button being clicked. If clicked, the Update/Delete FXML is loaded. */
    public void updateDeleteButtonClicked(){
        loadNewScene(apptStackPane, "Update_Delete_Appointment.fxml");
    }
}
