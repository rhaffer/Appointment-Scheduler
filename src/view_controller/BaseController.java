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

import java.io.IOException;
import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController {
    protected static User LOGGED_IN_USER;
    protected static Connection CONN = DBConnection.openConnection();
    protected static Locale LOCALE = Locale.getDefault();
    protected static ResourceBundle RB = ResourceBundle.getBundle("locales", Locale.getDefault());

    protected void loadNewScene(AnchorPane pane, String resource) {
        // Each page is recreated -- kind of annoying, but is what it is
        if (!pane.getChildren().isEmpty()) {
            pane.getChildren().clear();
        }

        try {
            // Loads the new FXML with appropriate template for each config
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
            System.out.println("Cannot load new scene. Reason: " + e.getMessage());
        }
    }

    protected void loadNewScene(StackPane pane, String resource){
        // Each page is recreated -- kind of annoying, but is what it is
        if (!pane.getChildren().isEmpty()){ pane.getChildren().clear(); }
        try {
            // Loads the new FXML with appropriate template for each config
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
            System.out.println("Cannot load new scene. Reason: " + e.getMessage());
        }
    }
}
