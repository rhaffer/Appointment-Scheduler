package view_controller.login_view_controller;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.User;
import view_controller.BaseController;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Login extends BaseController {
    @FXML
    private AnchorPane loginPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userNameField;

    @FXML
    private Label titleLabel;

    @FXML
    private Button signInButton;

    @FXML
    private Label noAccountLabel;

    @FXML
    private Label createAccountLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label defaultLocaleLabel;

    @FXML
    private void initialize(){
        titleLabel.setText(RB.getString("title"));
        userNameField.setPromptText(RB.getString("userPrompt"));
        passwordField.setPromptText(RB.getString("passwordPrompt"));
        signInButton.setText(RB.getString("signIn"));
        noAccountLabel.setText(RB.getString("noAccount"));
        createAccountLabel.setText(RB.getString("createAccount"));
        languageLabel.setText(RB.getString("language"));
        defaultLocaleLabel.setText(LOCALE.toString());
    }

    @FXML
    public void signInClicked() throws SQLException {
        if (checkUserFields()){
            if (checkUsernamePassword()){
                writeLoginAttempt("Successful");
                loadNewScene(loginPane, "/view_controller/nav_view_controller/Navigation.fxml");
                LOGGED_IN_USER = new User(userNameField.getText(), passwordField.getText(), LocalDateTime.now().toString(),
                        null, null);
            }else{
                writeLoginAttempt("Unsuccessful");
                Alert newAlert = new Alert(Alert.AlertType.ERROR);
                newAlert.setHeaderText(RB.getString("loginFailed"));
                newAlert.setContentText(RB.getString("userNamePasswordIncorrect"));
                newAlert.show();
            }
        }
        else{
            writeLoginAttempt("Unsuccessful");
            Alert newAlert = new Alert(Alert.AlertType.ERROR);
            newAlert.setHeaderText(RB.getString("loginFailed"));
            newAlert.setContentText(RB.getString("enterUsernamePassword"));
            newAlert.show();
        }
    }

    // Loads the Registration screen
    public void registerClicked(){
        loadNewScene(loginPane,"Register.fxml");
    }

    /* Retrieves the username and password for the new user and validates vs the users in the database.
    If the username and password match based off the data in the database, then return true, else false */
    private Boolean checkUsernamePassword() throws SQLException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        User newUser = new User(userName, password, LocalDateTime.now().toString(), null, null);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(CONN, userName);
        return validateLogin(user, newUser);
    }

    // Returns false if either the username or password fields are empty
    private Boolean checkUserFields(){
        return !(userNameField.getText().equals("") | passwordField.getText().equals(""));
    }

    // Returns true if the username and passwords match, false otherwise.
    private Boolean validateLogin(User user1, User user2){
        if (user1 == null | user2 == null){
            return false;
        }
        return user1.getUserName().equals(user2.getUserName()) & user1.getPassword().equals(user2.getPassword());
    }

    private void writeLoginAttempt(String attemptStatus){
        String path = "src/login_activity.txt";
        String message = userNameField.getText() + " attempted to login at " + LocalDateTime.now().toString() + "\nLogin " + attemptStatus + "\n\n";
        try{
            FileWriter fw = new FileWriter(path, true);
            fw.write(message);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

