package hangman;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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

    private int stage = 0;
    private final int FINAL_STAGE = 9;
    public static Game game;
    private Timeline timeline;
    private int seconds = 0;

    public void handleKeyPressed(javafx.scene.input.KeyEvent event) {
       if (checkInputChar(event.getCode().getName() , nameOfObject) == false) {
           stage += 1;

            if (stage == FINAL_STAGE) {
                showLostScreen();
            } else {
                showNextStage(stage);
            }
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
                System.out.println("ERROR IN SHOWING STAGE OF HANGMAN");
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
        seconds = 0;
        updateTimerLabel();
    }

}