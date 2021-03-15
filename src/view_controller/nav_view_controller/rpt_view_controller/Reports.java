package view_controller.nav_view_controller.rpt_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

/**
 * This class handles the Report navigation screen.
 */
public class Reports extends BaseController {
    @FXML
    StackPane reportStackPane;

    @FXML
    Button apptByDateButton;

    @FXML
    Button totalNumberButton;

    @FXML
    Button contactScheduleButton;

    /**
     * Handles the TotalNumber button.
     */
    @FXML
    public void totalNumberButtonHandler(){ loadNewScene(reportStackPane, "Total_Number_Report.fxml"); }

    /**
     * Handles the Contact Schedule button.
     */
    @FXML
    public void contactScheduleButtonHandler(){ loadNewScene(reportStackPane, "Contact_Schedule_Report.fxml"); }

    /**
     * Handles the Appt By Date button.
     */
    @FXML
    public void apptByDateButtonHandler(){ loadNewScene(reportStackPane, "Appt_By_Date.fxml"); }
}
