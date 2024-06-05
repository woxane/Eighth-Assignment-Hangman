package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class MenuController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchGameStage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = fxmlLoader.load();
        int test = 10;
        HangmanController hangmanController = fxmlLoader.getController();
        hangmanController.setGuessText(test);

        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }

}
