package hangman;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class LeaderboardController {
    @FXML
    private TableView<Leaderboard> table;
    @FXML
    private TableColumn<Leaderboard , String> usernameColumn;
    @FXML
    private TableColumn<Leaderboard, String> timeColumn;
    @FXML
    private TableColumn<Leaderboard , String> wordColumn;
    @FXML
    private TableColumn<Leaderboard , Integer> wrongGuessesColumn;


}
