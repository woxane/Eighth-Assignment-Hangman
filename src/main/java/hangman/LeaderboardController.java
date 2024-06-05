package hangman;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class LeaderboardController {
    @FXML
    private TableView<Leaderboard> table;

    public void setItems(ArrayList<Leaderboard> leaderboards) {
        table.getItems().addAll(leaderboards);
    }

}
