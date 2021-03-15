package view_controller.nav_view_controller.rpt_view_controller;

import dao.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import report.ApptByDate;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * This class handles the number of appointment by date screen.
 */
public class Appt_By_Date extends BaseController {
    @FXML
    TableView<ApptByDate> apptByDateTableView;

    @FXML
    TableColumn<ApptByDate, String> numberColumn;

    @FXML
    TableColumn<ApptByDate, String> dateColumn;

    /**
     * Initializes the controller. Sets the cell value factory for each column as well as inserts data into the TableView.
     */
    @FXML
    private void initialize() throws SQLException {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<ApptByDate> report = getNumberApptByDate();
        apptByDateTableView.setItems(report);
    }

    /**
     * This method gets the number of appointments by date.
     * @return ObservableList ApptByDate reports
     */
    private ObservableList<ApptByDate> getNumberApptByDate() throws SQLException {
        AppointmentDAO dao = new AppointmentDAO();
        ObservableList<Appointment> appointments = dao.getAll(CONN);
        Set<LocalDate> dates = new HashSet<>();
        ObservableList<ApptByDate> reports = FXCollections.observableArrayList();
        int appt_count = 0;
        for (Appointment appointment: appointments){
            dates.add(appointment.getStart().toLocalDate());
        }
        for (LocalDate date: dates){
            for (Appointment appointment: appointments){
                if (appointment.getStart().toLocalDate().equals(date)){
                    appt_count += 1;
                }
            }
            ApptByDate report = new ApptByDate(appt_count, date);
            reports.add(report);
            appt_count = 0;
        }
        return reports;
    }
}
