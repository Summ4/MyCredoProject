package steps;

import app.popup.AuthPopup;
import app.popup.LanguageChangePopup;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.CommonSteps;

public class LoginPageSteps extends CommonSteps<LoginPageSteps> {

    LanguageChangePopup languageChangePopup;
    AuthPopup authPopup;
    SoftAssert softAssert;

    public LoginPageSteps(LoginPage loginPage, SoftAssert softAssert) {
        super(loginPage);
        this.softAssert = softAssert;
    }

    public void assertAll(String message) {
        softAssert.assertAll(message);
    }

    @Step("Input username -> {0}")
    public LoginPageSteps inputUserName(String userName) {

        loginPage.userNameInput.inputText(userName);

        return this;

    }

    @Step("Input password -> {0}")
    public LoginPageSteps inputPassword(String password) {

        loginPage.passwordInput.inputText(password);
        return this;

    }

    @Step("Change Language - ENG")
    public LoginPageSteps changeLanguageENG() {
        if (!currenLanguage().equals("ENG")) {
            languageChangePopup = loginPage.changeLanguage.click(LanguageChangePopup.class);
            languageChangePopup.clickOnLanguageEng();
        }
        return this;

    }

    @Step("Change Language - GEO")
    public LoginPageSteps changeLanguageGEO() {

        if (!currenLanguage().equals("ქართ")) {
            languageChangePopup = loginPage.changeLanguage.click(LanguageChangePopup.class);
            languageChangePopup.clickOnLanguageGeo();
        }
        return this;

    }

    private String currenLanguage() {

        return loginPage.changeLanguage.getText();

    }

    @Step("Click On Login Button")
    public LoginPageSteps clickOnLoginButton() {

        authPopup = loginPage.loginButton.click(AuthPopup.class);
        return this;

    }

    @Step("Make Password Visible")
    public LoginPageSteps makePasswordVisible() {

        loginPage.makePasswordVisible();
        return this;

    }

    @Step("Check Password Visibility")
    public LoginPageSteps checkPasswordVisibility() {

        softAssert.assertTrue(loginPage.passwordInput.getAttribute("type").equals("text"));

        return this;
    }

    @Step("Input Invalid OTP")
    public LoginPageSteps inputInvalidOTP() {

        authPopup.otpInput.inputOTP("1111");

        return this;

    }

    @Step("Click on Submit Button on Auth Popup")
    public LoginPageSteps clickOnSubmitButtonOnAuthPopup() {

        authPopup.submitButton.click();
        return this;

    }

    @Step("Block User, Trying To Login With Wrong Password Several Times")
    public LoginPageSteps blockUser() {
        for (int i = 0; i <= 5; i++) {
            loginPage.loginButton.click();
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    @Step("Check If Login Button Is Disabled")
    public LoginPageSteps checkIfLoginButtonIsDisabled() {

        softAssert.assertTrue(loginPage.loginButton.isDisabled(), "Expecting Login Button To Be Disabled");

        return this;

    }

    @Step("Check If Notification Text Is Shown - > {0}")
    public LoginPageSteps checkNotificationText(String errorText) {

        softAssert.assertEquals(loginPage.getHeaderNotificationText(), errorText);
        return this;

    }

}
