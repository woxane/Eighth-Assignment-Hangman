package hangman;

public class Leaderboard {
    private String username;
    private int time;
    private int wrongGuesses;
    private String word;

    public Leaderboard(String username , int time , int wrongGusses , String word) {
        this.username = username;
        this.time = time;
        this.wrongGuesses = wrongGusses;
        this.word = word;
    }

    public String getUsername() {
        return username;
    }
    public int getTime() {
        return time;
    }
    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public String getWord() {
        return word;
    }
}
