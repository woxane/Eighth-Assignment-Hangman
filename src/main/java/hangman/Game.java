package hangman;

import java.util.UUID;

public class Game {
    private final int GAME_ID;
    private final int USER_ID;
    private final String text;
    private int wrongGuesses;
    private int time;
    private boolean win;


    public Game(int gameId, int userId, String text , int wrongGuesses , int time , boolean win) {
        this.GAME_ID = gameId;
        this.USER_ID = userId;
        this.text = text;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }


    public Game(int gameId, int userId,String text) {
        this.GAME_ID = gameId;
        this.USER_ID = userId;
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

    public UUID getGameID() {
        return this.GAME_ID;
    }

    public UUID getUSER_ID() {
        return this.USER_ID;
    }

    public String getText() {
        return this.text;
    }

    public int getWrongGuesses() {
        return this.wrongGuesses;
    }

    public int getTime() {
        return this.time;
    }

    public boolean getWin() {
        return this.win;
    }
}
