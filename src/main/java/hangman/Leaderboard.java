package hangman;

public class Leaderboard {
    private String username;
    private String time;
    private int wrongGusses;
    private String word;

    public Leaderboard(String username , String time , int wrongGusses , String word) {
        this.username = username;
        this.time = time;
        this.wrongGusses = wrongGusses;
        this.word = word;
    }

    public String getUsername() {
        return username;
    }
    public String getTime() {
        return time;
    }
    public int getWrongGusses() {
        return wrongGusses;
    }

    public String getWord() {
        return word;
    }
}
