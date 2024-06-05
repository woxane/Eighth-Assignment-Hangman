package hangman;

import java.util.UUID;

public class Account {
    private String name;
    private String username;
    private String password;
    private final UUID USER_ID;
    public Account(String name , String username , String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.USER_ID = UUID.randomUUID();;
    }

    public Account(String name , String username , String password , UUID USER_ID) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.USER_ID = USER_ID;;
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

    public UUID getUserId() {
        return this.USER_ID;
    }
}
