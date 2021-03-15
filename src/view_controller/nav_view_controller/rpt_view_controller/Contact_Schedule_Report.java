package view_controller.nav_view_controller.rpt_view_controller;

import dao.ContactDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view_controller.BaseController;
import report.ContactScheduleReport;

import java.sql.SQLException;

/**
 * This class handles the Contact Schedule Report.
 */
public class Contact_Schedule_Report extends BaseController {
    @FXML
    TableView<ContactScheduleReport> contactScheduleTableView;

    @FXML
    TableColumn<ContactScheduleReport, String> contactNameColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> contactIDColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> appointmentIDColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> titleColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> descriptionColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> startColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> endColumn;

    @FXML
    TableColumn<ContactScheduleReport, String> customerIDColumn;


    /**
     * Initializes the table column cell values as well as populates the TableView.
     */
    @FXML
    private void initialize() throws SQLException {
        contactNameColumn.setCellValueFactory(new PropertyValueFactory<>("contact_name"));
        contactIDColumn.setCellValueFactory(new PropertyValueFactory<>("contact_id"));
        appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        getReport();
    }

    /**
     * This method returns all of the contact schedules using an InnerJoin SQL statement.
     */
    private void getReport() throws SQLException {
        ContactDAO dao = new ContactDAO();
        ObservableList<ContactScheduleReport> report = dao.getReport(CONN);
        contactScheduleTableView.setItems(report);
    }
}
