import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DBConnection;

/** This class runs the application. */
public class Main extends Application {
    // Completed.
    /** Overrides the start() method.
     @param primaryStage The primary stage for the FXML application */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view_controller/login_view_controller/Login.fxml"));
        primaryStage.requestFocus();
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /** The main() method to be executed. Once the program is closed, this also closes the database Connection. */
    public static void main(String[] args) {
        launch(args);
        DBConnection.closeConnection();
    }
}
