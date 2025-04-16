package data;

import org.testng.annotations.DataProvider;
import utils.Configuration;

import static data.LoginPageAssertionMessages.*;
import static utils.Helper.generateRandomString;

public class DataProvider_UserData {

    String randomString = generateRandomString();
    private Database database;

    public DataProvider_UserData() {
        this.database = new Database(Configuration.DB_HOST, Configuration.DB_USERNAME, Configuration.DB_PASSWORD);
    }

    @DataProvider(name = "invalidData")
    public Object[][] invalidData() {

        String username = database.getActiveUserUserName();

        return new Object[][]{
                {randomString, randomString, WRONGCREDINTIALS},
                {username, " ", USERNAMEANDONLYSPACEINPASSWORD},
                {username, randomString, USERNAMEANDWRONGPASSWORD}
        };
    }

    @DataProvider(name = "emptyUserName")
    public Object[][] emptyUserName() {

        String username = database.getActiveUserUserName();
        database.resetUser(username);

        return new Object[][]{
                {"", " ", EMPTYUSERNAMEANDSPACEINSPASSWORD},
                {"", randomString, EMPTYUSERNAMEANDRADOMPASSWORD},
                {"", Configuration.MYCREDO_USER_PASSWORD, EMPTYUSERNAMEANDCORRECTINSPASSWORD},
                {"ტესტ", Configuration.MYCREDO_USER_PASSWORD, GEORGIANUSERNAMEANDSPACEINSPASSWORD},
                {"Lion", Configuration.MYCREDO_USER_PASSWORD, "Fail Case"},
        };
    }

    @DataProvider(name = "emptyPassword")
    public Object[][] emptyPassword() {

        String username = database.getActiveUserUserName();
        database.resetUser(username);

        return new Object[][]{
                {randomString, "", RANDOMUSERNAMEANDEMPTYPASSWORD},
                {username, "", USERNAMEANDEMPTYPASSWORD},
                {" ", "", SPACEINUSERNAMEANDEMPTYPASSWORD}
        };
    }

    @DataProvider(name = "blockedUser")
    public Object[][] blockedUser() {

        String username = database.getBlockedUserUserName();
        database.resetUser(username);

        return new Object[][]{
                {username, BLOCKEDUSER}
        };
    }

    @DataProvider(name = "activeUser")
    public Object[][] activeUser() {

        String username = database.getActiveUserUserName();
        database.resetUser(username);

        return new Object[][]{
                {username}
        };
    }

}
