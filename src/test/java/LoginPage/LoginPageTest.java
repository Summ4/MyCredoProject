package LoginPage;

import data.DataProvider_UserData;
import data.Language;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steps.LoginPageSteps;
import utils.BaseSetup;
import utils.Configuration;

import static data.LoginPageAssertionMessages.BLOCKUSER;
import static data.LoginPageAssertionMessages.INVALIDOTPCODE;
import static data.Messages.getBlockedUserText;
import static data.Messages.getIncorrectDataText;

public class LoginPageTest extends BaseSetup {

    private LoginPageSteps loginPageSteps;
    Language language;

    LoginPageTest(Language language) {
        this.language = language;
    }

    @BeforeMethod
    public void initStep() {

        loginPageSteps = new LoginPageSteps(getLoginPage(), new SoftAssert());

        if (language == Language.ENG)
            loginPageSteps.changeLanguageENG();
        else if (language == Language.GEO)
            loginPageSteps.changeLanguageGEO();
        else Assert.fail("Invalid language selected");

    }

    @Test(description = "Login with wrong data", dataProviderClass = DataProvider_UserData.class, dataProvider = "invalidData", priority = 1)
    public void invalidData(String username, String password, String message) {
        loginPageSteps.inputUserName(username)
                .inputPassword(password)
                .clickOnLoginButton()
                .checkNotificationText(getIncorrectDataText(language))
                .assertAll(message);
    }

    @Test(description = "Trying to login when username is empty", dataProviderClass = DataProvider_UserData.class, dataProvider = "emptyUserName", priority = 2)
    public void emptyUserName(String username, String password, String message) {
        loginPageSteps.inputUserName(username)
                .inputPassword(password)
                .checkIfLoginButtonIsDisabled()
                .assertAll(message);
    }

    @Test(description = "Trying to login when password is empty", dataProviderClass = DataProvider_UserData.class, dataProvider = "emptyPassword", priority = 3)
    public void emptyPassword(String username, String password, String message) {
        loginPageSteps.inputUserName(username)
                .inputPassword(password)
                .checkIfLoginButtonIsDisabled()
                .assertAll(message);
    }

    //დაბლოკილ იუზერზე მაინც გვაძლევს დალოგინების შესაძლებლობას (Failing Case)
    @Test(description = "Login with blocked user", dataProviderClass = DataProvider_UserData.class, dataProvider = "blockedUser", priority = 4)
    public void blockedAccount(String username, String message) {

        loginPageSteps.inputUserName(username)
                .inputPassword(Configuration.MYCREDO_USER_PASSWORD)
                .clickOnLoginButton()
                .checkNotificationText(getBlockedUserText(language))
                .assertAll(message);
    }

    @Test(description = "Input Invalid OTP Code", dataProviderClass = DataProvider_UserData.class, dataProvider = "activeUser", priority = 5)
    public void invalidOTPCode(String username) {

        loginPageSteps.inputUserName(username)
                .inputPassword(Configuration.MYCREDO_USER_PASSWORD)
                .clickOnLoginButton()
                .inputInvalidOTP()
                .clickOnSubmitButtonOnAuthPopup()
                .checkNotificationText(getIncorrectDataText(language))
                .assertAll(INVALIDOTPCODE);
    }

    @Test(description = "Check password Visibility", priority = 6)
    public void passwordVisibility() {

        loginPageSteps
                .inputPassword("Lion")
                .makePasswordVisible()
                .checkPasswordVisibility();
    }

    @Test(description = "Block active user and check if the user become blocked", dataProviderClass = DataProvider_UserData.class, dataProvider = "activeUser", priority = 7)
    public void blockUser(String username) {

        loginPageSteps.inputUserName(username)
                .inputPassword(" ")
                .blockUser()
                .checkNotificationText(getBlockedUserText(language))
                .assertAll(BLOCKUSER);
    }

}