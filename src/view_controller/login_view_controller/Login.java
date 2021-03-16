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

/** This class acts as the Controller for the Login page. */
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

    /** This method initializes the Login screen with appropriate resource bundles depending on Locale. */
    @FXML
    private void initialize(){
        titleLabel.setText(RB.getString("title"));
        userNameField.setPromptText(RB.getString("userPrompt"));
        passwordField.setPromptText(RB.getString("passwordPrompt"));
        signInButton.setText(RB.getString("signIn"));
        noAccountLabel.setText(RB.getString("noAccount"));
        createAccountLabel.setText(RB.getString("createAccount"));
        languageLabel.setText(RB.getString("location"));
        defaultLocaleLabel.setText(ZONE.toString());
    }

    /** This method handles the Sign In button. This method first checks to make sure that all fields have something
     * inputted inside them, then if the password comes back as a match, it logs the attempt as successful and loads
     * the Navigation FXML file. If the password is incomplete or there is nothing in the Username or Password fields
     * then the login attempt is logged as unsuccessful. */
    @FXML
    public void signInClicked() throws SQLException {
        if (checkUserFields()){
            if (checkUsernamePassword()){
                writeLoginAttempt("Successful");
                loadNewScene(loginPane, "/view_controller/nav_view_controller/Navigation.fxml");
                UserDAO dao = new UserDAO();
                LOGGED_IN_USER = dao.get(CONN, userNameField.getText());

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

    /** This method loads the Register FXML if "Create One!" is clicked. */
    @FXML
    public void registerClicked(){ loadNewScene(loginPane,"Register.fxml"); }

    /** This method checks to see if the username and password match based off of entries in the database.
     @return True if username and password match in database, false otherwise. */
    private Boolean checkUsernamePassword() throws SQLException {
        String userName = userNameField.getText();
        String password = passwordField.getText();
        User newUser = new User(userName, password, LocalDateTime.now().toString(), null, null);
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(CONN, userName);
        return validateLogin(user, newUser);
    }

    /** This method checks the username and password fields to validate if they are empty.
     @return True if neither fields are empty, false otherwise. */
    private Boolean checkUserFields(){
        return !(userNameField.getText().equals("") | passwordField.getText().equals(""));
    }

    /** This method checks to ensure that the username and passwords match.
     @param user1 The first User to be checked
     @param user2 The second User to be checked
     @return True if username and passwords match, false otherwise. */
    private Boolean validateLogin(User user1, User user2){
        if (user1 == null | user2 == null){
            return false;
        }
        return user1.getUserName().equals(user2.getUserName()) & user1.getPassword().equals(user2.getPassword());
    }

    /** This method writes the login attempt status to login_activity.txt.
     @param attemptStatus The variable that says whether the attempt was successful or not. */
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
