package hangman;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class HangmanController {
    @FXML
    private ImageView stickBase;
    @FXML
    private ImageView stickStanding;
    @FXML
    private ImageView stickUp;
    @FXML
    private ImageView Head;
    @FXML
    private ImageView Body;
    @FXML
    private ImageView rightHand;
    @FXML
    private ImageView leftHand;
    @FXML
    private ImageView rightLeg;
    @FXML
    private ImageView leftLeg;

    private int stage = 0;
    private final int FINAL_STAGE = 9;


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
}