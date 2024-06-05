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

}