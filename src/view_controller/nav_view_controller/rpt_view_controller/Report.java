package view_controller.nav_view_controller.rpt_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import view_controller.BaseController;

public class Report extends BaseController {
    @FXML
    StackPane reportStackPane;

    @FXML
    Button totalNumberButton;

    @FXML
    Button contactScheduleButton;

    @FXML
    public void totalNumberButtonHandler(){
        loadNewScene(reportStackPane, "Total_Number_Report.fxml"); }

    @FXML
    public void contactScheduleButtonHandler(){

    }
}
