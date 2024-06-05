package hangman;

import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:src/main/java/hangman/database.db";
    private Connection connection;
    private Statement statement;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("ERROR WHILE CONNECTING TO DATABASE");
            throw new RuntimeException(e);
        }
    }


    public boolean checkUsernameInUse(String username) {
        String query = "SELECT * FROM UserInfo WHERE Username = '" + username+ "'";

        try (ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE checkUsernameInUse called !");
            throw new RuntimeException(e);
        }
    }


    public Account checkLoginData(String usernameToCheck , String passwordToCheck) {
        String query = "SELECT * FROM UserInfo WHERE Username = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usernameToCheck);
            statement.setString(2, passwordToCheck);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("UserID");
                    String name = resultSet.getString("Name");
                    String username = resultSet.getString("Username");
                    String password = resultSet.getString("Password");
                    Account account = new Account(name, username, password ,userId);
                    return account;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE checkLoginData called!");
            e.printStackTrace();
        }

        return null;
    }


    public int insertAccount(Account account) {
        /*return of this function is going to be the GameId that is primary key*/
        String query = "INSERT INTO UserInfo (Name, Username, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, account.getName());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int lastInsertedRowId = resultSet.getInt(1);
                        return lastInsertedRowId;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE insertAccount called !");
            e.printStackTrace();
            throw new RuntimeException(e);
        }


        return 0;
    }


    public int insertGame(Game game) {
        /*return of this function is going to be the GameId that is primary key*/
        String query = "INSERT INTO GameInfo (UserId , Word) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, game.getUserId());
            statement.setString(2, game.getWord());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int lastInsertedRowId = resultSet.getInt(1);
                        return lastInsertedRowId;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE insertGame called !");
            throw new RuntimeException(e);
        }

        return 0;
    }

    public void updateGame(Game game) {
        String sql = "UPDATE GameInfo SET WrongGuesses = ?, Time = ?, Win = ? WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, game.getWrongGuesses());
            statement.setInt(2, game.getTime());
            statement.setBoolean(3, game.getWin());
            statement.setInt(4, game.getUserId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0){
                System.err.println("NO ROW UPDATED IN udpateGame stack !");
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE updateGame called!");
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Leaderboard> getLeaderboard() {
        String query = "SELECT UserInfo.Username, GameInfo.Time, GameInfo.WrongGuesses, GameInfo.Word " +
                "FROM UserInfo " +
                "INNER JOIN GameInfo ON UserInfo.UserID = GameInfo.UserID " +
                "ORDER BY GameInfo.Time";
        Leaderboard leaderboard;
        ArrayList<Leaderboard> leaderboards = new ArrayList<>();

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String username = resultSet.getString("Username");
                int time = resultSet.getInt("Time");
                int wrongGuesses = resultSet.getInt("WrongGuesses");
                String word = resultSet.getString("Word");
                leaderboard = new Leaderboard(username , time , wrongGuesses , word);
                leaderboards.add(leaderboard)
            }
        } catch (SQLException e) {
            System.err.println("ERROR WHILE getLeaderboard called!");
            e.printStackTrace();
        }

        return leaderboards;
    }

}