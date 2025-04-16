package data;

import utils.BaseDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database extends BaseDb {

    public Database(String dbHost, String dbUsername, String dbPassword) {
        super(dbHost, "IBankUsermanagement", dbUsername, dbPassword);
    }

    private String getActiveUsernameQuery = """
            SELECT TOP 1 [Login]
              FROM [IBankUsermanagement].[auth].[UserLogins]
              where IsBlocked = 0
              ORDER BY NEWID()
            """;

    private String getBlockedUsernameQuery = """
            SELECT TOP 1 [Login]
              FROM [IBankUsermanagement].[auth].[UserLogins]
              where IsBlocked = 1
              ORDER BY NEWID()
            """;
    private String resetUser = """
                        update a
                        set a.Password  = '+TFStFo+daaj5e1ZL0CjHE2UbQ7D8CNd' , a.Salt = 'Gpz/bYaNXjVmkh22i0mUdrRo0WKgq7WD'
                        from  [IBankUsermanagement].[auth].[UserLogins] as a
                        where a.Login = ?
            """;


    public String getActiveUserUserName() {
        String userName = "";
        Connection databaseAccessSQL = getConnection();
        ResultSet result = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseAccessSQL.prepareStatement(getActiveUsernameQuery);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                userName = result.getString("Login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userName;
    }

    public String getBlockedUserUserName() {
        String userName = "";
        Connection databaseAccessSQL = getConnection();
        ResultSet result = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseAccessSQL.prepareStatement(getBlockedUsernameQuery);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                userName = result.getString("Login");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userName;
    }

    public void resetUser(String username) {
        Connection databaseAccessSQL = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = databaseAccessSQL.prepareStatement(resetUser);
            preparedStatement.setObject(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
