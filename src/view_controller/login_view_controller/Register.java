package view_controller.login_view_controller;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;
import view_controller.BaseController;

import java.sql.SQLException;
import java.time.LocalDateTime;

/** This class acts as the Controller for the Register screen. */
public class Register extends BaseController {
    @FXML
    AnchorPane registerPane;

    @FXML
    Button backToSignInButton;

    @FXML
    Button registerButton;

    @FXML
    Label createAccountLabel;

    @FXML
    TextField usernameField;

    @FXML
    TextField password1Field;

    @FXML
    TextField password2Field;

    /** This method initializes the controller to have the correct Resource Bundle based on Locale. */
    @FXML
    private void initialize(){
        createAccountLabel.setText(RB.getString("createAccountLabel"));
        usernameField.setPromptText(RB.getString("userPrompt"));
        password1Field.setPromptText(RB.getString("passwordPrompt"));
        password2Field.setPromptText(RB.getString("passwordPrompt"));
        registerButton.setText(RB.getString("register"));
        backToSignInButton.setText(RB.getString("backToSignIn"));
    }

    /** This method handles the "Back to Login" button. Returns the user to the Login screen. */
    public void backToSignInClicked(){
        loadNewScene(registerPane, "Login.fxml");
    }

    /** This method handles the Register button. If the passwords entered match, then the User is entered into the
     * database. An error is thrown if the User is unable to inserted or if the passwords do not match. */
    public void registerButtonClicked() throws SQLException {
        if (passwordsMatch()){
            UserDAO userDAO = new UserDAO();
            User newUser = new User(usernameField.getText(), password1Field.getText(), LocalDateTime.now().toString(), null, null);
            if(userDAO.save(CONN, newUser)){
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                newAlert.setHeaderText(RB.getString("userCreated"));
                newAlert.setContentText(newUser.getUserName() + " " + RB.getString("usernameCreated"));
                newAlert.show();
            }else{
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText(RB.getString("userCreationError"));
                newAlert.setContentText(RB.getString("userNameExists"));
                newAlert.show();
            }
        }else{
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText(RB.getString("userCreationError"));
            newAlert.setContentText(RB.getString("passwordMatch"));
            newAlert.show();
        }
    }

    /** This method checks to ensure that the passwords entered match.
     @return True if the passwords match, false otherwise. */
    public Boolean passwordsMatch(){
        String password1 = password1Field.getText();
        String password2 = password2Field.getText();
        return password1.equals(password2) & password1.length() > 1;
    }
}
