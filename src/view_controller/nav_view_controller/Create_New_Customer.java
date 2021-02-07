package view_controller.nav_view_controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Country;
import model.FirstLevelDivision;
import view_controller.BaseController;

public class Create_New_Customer extends BaseController {
    @FXML
    TextField custNameTF;

    @FXML
    TextField custAddressTF;

    @FXML
    TextField custPostalTF;

    @FXML
    ComboBox<Country> custCountryCB;

    @FXML
    ComboBox<FirstLevelDivision> custFldCB;

    @FXML
    Button custSubmitButton;

}
