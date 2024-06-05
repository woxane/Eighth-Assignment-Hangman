package hangman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HangmanController {
    @FXML
    private ImageView stickBase;
    @FXML
    private ImageView stickStanding;
    @FXML
    private ImageView stickUp;
    @FXML
    private ImageView head;
    @FXML
    private ImageView body;
    @FXML
    private ImageView rightHand;
    @FXML
    private ImageView leftHand;
    @FXML
    private ImageView rightLeg;
    @FXML
    private ImageView leftLeg;
    @FXML
    private Label guessLabel;
    @FXML
    private Label timerLabel;
    @FXML
    private Label wrongGuessLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label nameOfObjectLabel;
    @FXML
    private Label elapsedTimeLabel;
    @FXML
    private AnchorPane statusPane;

    private int stage = 0;
    private final int FINAL_STAGE = 9;
    public static Game game;
    private Timeline timeline;
    private int seconds = 0;
    private String nameOfObject;

    public void handleKeyPressed(javafx.scene.input.KeyEvent event) {
        String pressedKey = event.getCode().getName();
       if (!nameOfObject.contains(pressedKey)) {
            stage += 1;
            changeWrongGuessLabel(pressedKey);

            if (stage == FINAL_STAGE + 1) {
                stopTimer();
                game.setTime(seconds);
                game.setWin(false);
                game.setWrongGuesses(FINAL_STAGE);
                game.updateData();

                stopKeyEvent(event);
                showLostScreen();
            } else {
                showNextStage(stage);
            }
       } else {
            changeGuessLabel(pressedKey.charAt(0));
       }

       if (!nameOfObject.contains("-")) {
            stopTimer();
            game.setTime(seconds);
            game.setWin(true);
            game.setWrongGuesses(stage);
            game.updateData();

            stopKeyEvent(event);
            showWinScreen();
       }

        KeyCode keyCode = event.getCode();
        System.out.println("Key Pressed: " + keyCode.getName());
    }

    public void showNextStage(int stage) {
        switch (stage) {
            case 1 :
                stickBase.setVisible(true);
                break;

            case 2 :
                stickStanding.setVisible(true);
                break;

            case 3 :
                stickUp.setVisible(true);
                break;
            case 4 :
                head.setVisible(true);
                break;

            case 5 :
                body.setVisible(true);
                break;

            case 6 :
                rightHand.setVisible(true);
                break;

            case 7 :
                leftHand.setVisible(true);
                break;

            case 8 :
                rightLeg.setVisible(true);
                break;

            case 9 :
                leftLeg.setVisible(true);
                break;

            default :
                System.err.println("ERROR IN SHOWING STAGE OF HANGMAN");
        }
    }


    public void setGuessText(int numberOfText) {
        String text = "";
        for (int i = 0 ; i < numberOfText ; i++) {
            text += "-";
        }

        guessLabel.setText(text);
    }

    public void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                seconds++;
                updateTimerLabel();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stopTimer() {
        timeline.stop();
        updateTimerLabel();
    }

    public void updateTimerLabel() {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, remainingSeconds));
    }

    public void setNameOfObject(String name) {
        this.nameOfObject = name;
    }


    public void changeGuessLabel(char key) {
        int index = -1;
        while ((index = nameOfObject.indexOf(key, index + 1)) != -1) {
            if (index < guessLabel.getText().length()) {
                StringBuilder sb = new StringBuilder(guessLabel.getText());
                sb.setCharAt(index, key);
                guessLabel.setText(sb.toString());
            }
        }
    }


    public void changeWrongGuessLabel(String key) {
        if (!wrongGuessLabel.getText().contains(key)) {
               wrongGuessLabel.setText(wrongGuessLabel.getText() + key);
        }
    }

    public void showLostScreen() {
        statusLabel.setStyle("-fx-text-fill: #FF5555");
        statusLabel.setText("You Lost");

        usernameLabel.setText("#" + HangmanApp.account.getUsername());

        nameOfObjectLabel.setText(nameOfObject);
        elapsedTimeLabel.setText(timerLabel.getText());

        statusLabel.setVisible(true);
    }

    public void showWinScreen() {
        statusLabel.setStyle("-fx-text-fill: #50fa7b");
        statusLabel.setText("You Won");

        usernameLabel.setText("#" + HangmanApp.account.getUsername());

        nameOfObjectLabel.setText(nameOfObject);
        elapsedTimeLabel.setText(timerLabel.getText());

        statusLabel.setVisible(true);
    }

    public void stopKeyEvent(KeyEvent event) {
        ((Node)event.getSource()).getScene().removeEventFilter(KeyEvent.KEY_PRESSED , this::handleKeyPressed);
    }
}