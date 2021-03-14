package view_controller;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.User;
import util.DBConnection;
import util.TimeConverter;

import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class acts as the abstract class for all other Controllers. This class holds the logged in user,
 * database Connection, locale, and resource bundle information. */
public abstract class BaseController {
    protected static final TimeConverter converter = new TimeConverter();
    protected static User LOGGED_IN_USER;
    protected static Connection CONN = DBConnection.openConnection();
    protected static Locale LOCALE = Locale.getDefault();
    protected static ResourceBundle RB = ResourceBundle.getBundle("locales", Locale.getDefault());

    /** A method that loads a new scene and provides an animation.
     @param pane The AnchorPane in which the FXML is loaded to
     @param resource The new FXML file to be loaded */
    protected void loadNewScene(AnchorPane pane, String resource) {
        // Each page is recreated -- kind of annoying, but is what it is
        if (!pane.getChildren().isEmpty()) {
            pane.getChildren().clear();
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = pane.getScene();
            // Adds fancy transition up -- just wanted practice
            // TranslateYProperty() makes it go up and down!
            root.translateXProperty().set(scene.getWidth());
            pane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /** A method that loads a new scene and provides an animation.
     @param pane The StackPane in which the FXML is loaded to
     @param resource The new FXML file to be loaded */
    protected void loadNewScene(StackPane pane, String resource){
        // Each page is recreated -- kind of annoying, but is what it is
        if (!pane.getChildren().isEmpty()){ pane.getChildren().clear(); }
        try {
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = pane.getScene();
            // Adds fancy transition up -- just wanted practice
            // TranslateYProperty() makes it go up and down!
            root.translateXProperty().set(scene.getWidth());
            pane.getChildren().add(root);

            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
