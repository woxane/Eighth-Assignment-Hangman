package hangman;

import javafx.scene.chart.PieChart;

import java.sql.*;


public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:test.db";
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

}