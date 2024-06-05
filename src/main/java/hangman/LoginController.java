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

public class LoginController {

    private Parent root;
    private Scene scene;
    private Stage stage;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label credentialLabel;

    public void login(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        Account account = checkLoginData(username , password);

        if (account == null) {
            setWrongCredentialStyle();
            return;
        }

        HangmanApp.account = account;
        switchMenuPage(event);
    }

    public void setWrongCredentialStyle() {
        String css = "-fx-background-radius: 20;-fx-background-color: #181818;-fx-border-color: " +
                "red;-fx-border-radius: 20;-fx-border-width: 1.6;-fx-text-inner-color: white";
        usernameTextField.setStyle(css);
        passwordTextField.setStyle(css);
        credentialLabel.setVisible(true);

        shake(usernameTextField);
        shake(passwordTextField);
    }


    public void shake(Node node) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50) , node);

        translateTransition.setByX(10f);
        translateTransition.setCycleCount(4);
        translateTransition.setAutoReverse(true);

        translateTransition.playFromStart();
    }


    public void switchSignUpPage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
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
