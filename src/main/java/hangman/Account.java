package hangman;

import java.util.UUID;

public class Account {
    private String name;
    private String username;
    private String password;
    private final int USER_ID;

    public Account(String name , String username , String password , int userId) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.USER_ID = userId;;
    }

    public Account(String name , String username , String password) {
        this.name = name;
        this.username = username;
        this.password = password;

        int userId = HangmanApp.databaseManager.insertAccount(this);
        this.USER_ID = userId;;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getUserId() {
        return this.USER_ID;
    }
}
