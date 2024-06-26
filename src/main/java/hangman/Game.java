package hangman;

import java.util.UUID;

public class Game {
    private final int GAME_ID;
    private final int USER_ID;
    private final String word;
    private int wrongGuesses;
    private int time;
    private boolean win;


    public Game(int gameId, int userId, String word, int wrongGuesses , int time , boolean win) {
        this.GAME_ID = gameId;
        this.USER_ID = userId;
        this.word = word;
        this.wrongGuesses = wrongGuesses;
        this.time = time;
        this.win = win;
    }


    public Game(int userId , String word) {
        this.USER_ID = userId;
        this.word = word;

        int gameId = HangmanApp.databaseManager.insertGame(this);
        this.GAME_ID = gameId;
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

    public int getGameID() {
        return this.GAME_ID;
    }

    public int getUserId() {
        return this.USER_ID;
    }

    public String getWord() {
        return this.word;
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

    public void updateData() {
        HangmanApp.databaseManager.updateGame(this);
    }
}
