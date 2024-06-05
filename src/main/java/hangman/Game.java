package hangman;

import java.util.UUID;

public class Game {
    private final UUID GAME_ID;
    private final UUID USER_ID;
    private final String text;
    private int wrongGuesses;
    private int time;
    private boolean win;


    public Game(UUID gameId, UUID userId, String text , int wrongGuesses , int time , boolean win) {
        this.GAME_ID = gameId;
        this.USER_ID = userId;
        this.text = text;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }


    public Game(String text) {
        this.GAME_ID = UUID.randomUUID();
        this.USER_ID = UUID.randomUUID();
        this.text = text;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
