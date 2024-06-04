package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label credentialLabel;

    public void login(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (checkLoginData(username , password) == false) {
            setWrongCredentialStyle();
            return;
        }

        switchMenuPage();
    }

}
