package hangman;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class SignUpController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label usernameWarningLabel;

    public void SignUp(ActionEvent event) {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (checkUsername(username) == false) {
            setWrongUsernameStyle();
            return;
        }

        switchMenuPage();
    }


    public void shake(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50) , node);

        translateTransition.setByX(10f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);

        translateTransition.playFromStart();
    }


}
