package hangman;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SignUpController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label usernameWarningLabel;

    public void SignUp(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (checkUsername(username) == false) {
            setWrongUsernameStyle();
            return;
        }

        Account account = new Account(name , username , password);
        HangmanApp.account = account;

        switchMenuPage(event);
    }


    public void setWrongUsernameStyle() {
        String css = "-fx-background-radius: 20;-fx-background-color: #181818;-fx-border-color: " +
                "red;-fx-border-radius: 20;-fx-border-width: 1.6;-fx-text-inner-color: white";
        usernameTextField.setStyle(css);
        usernameWarningLabel.setVisible(true);

        shake(usernameTextField);
    }

    public void shake(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50) , node);

        translateTransition.setByX(10f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);

        translateTransition.playFromStart();
    }

    public void switchLoginPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void switchMenuPage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
