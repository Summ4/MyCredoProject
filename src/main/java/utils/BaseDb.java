package utils;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDb implements AutoCloseable {
    private static Connection connection;
    private static String connectionUrl;
    private static String dbUsername;
    private static String dbPassword;

    @SneakyThrows
    public BaseDb(String dbHost, String dbName, String dbUsername, String dbPassword) {
        BaseDb.dbUsername = dbUsername;
        BaseDb.dbPassword = dbPassword;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connectionUrl = "jdbc:sqlserver://" + dbHost +
                ";databaseName=" + dbName +
                ";encrypt=false;trustedServerCertificate=false";
    }

    @SneakyThrows
    public static Connection getConnection() {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection is closed ");
            } catch (SQLException e) {
                System.err.println("Error during connection close:  " + e.getMessage());
            }
        }
    }

}
