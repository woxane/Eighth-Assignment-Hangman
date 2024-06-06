package hangman;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MenuController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchGameStage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        root = fxmlLoader.load();

        String word = randomWord();
        System.out.println("THE WORD IS : " + word);
        Game game = new Game(HangmanApp.account.getUserId(), word);
        HangmanController.game = game;

        HangmanController hangmanController = fxmlLoader.getController();
        hangmanController.setGuessText(game.getWord().length());
        hangmanController.setNameOfObject(game.getWord());
        hangmanController.startTimer();

        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene.setOnKeyPressed(hangmanController::handleKeyPressed);
        stage.setScene(scene);
        stage.show();



    }

    public String randomWord() {
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString().substring(2 , response.toString().length() - 2).toUpperCase();
        } catch (Exception e) {
            System.err.println("ERROR WHILE randomWord called !");
            e.printStackTrace();
        }
        return "TEST";
    }


    public void switchLeaderboardStage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
        root = fxmlLoader.load();
        LeaderboardController leaderboardController = fxmlLoader.getController();

        ArrayList<Leaderboard> leaderboards = HangmanApp.databaseManager.getLeaderboard();
        leaderboardController.setItems(leaderboards);

        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
