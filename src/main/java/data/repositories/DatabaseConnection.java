package data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private String jdbcURL = "jdbc:oracle:thin:@//localhost:1521/XE";
    private String userID = "erik";
    private String password = "oracle";
    private Connection connection;

    public DatabaseConnection(){
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(this.jdbcURL, this.userID, this.password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
