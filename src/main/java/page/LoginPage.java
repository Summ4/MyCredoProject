package page;

import common.ButtonComponent;
import common.InputComponent;
import org.openqa.selenium.By;
import utils.Browser;

public class LoginPage extends Page {

    public final InputComponent userNameInput;
    public final InputComponent passwordInput;
    public final ButtonComponent togglePassword;
    private static final By headerNotificationText = By.xpath("//section[@class='notification-container error']");
    public final ButtonComponent loginButton;
    public final ButtonComponent changeLanguage;

    public LoginPage(Browser browser) {
        super(browser);

        userNameInput = new InputComponent(browser, "//input[@id='userName']");
        passwordInput = new InputComponent(browser, "//input[@id='newPass']");
        togglePassword = new ButtonComponent(browser, "//div[@id='togglePass']");
        changeLanguage = new ButtonComponent(browser, "//div[@id='languageSwitcherBtn']");
        loginButton = new ButtonComponent(browser, "//button[@id='submitAuth']");

    }

    public String getHeaderNotificationText() {
        return browser.getText(headerNotificationText);
    }

    public void makePasswordVisible() {
        if (!passwordIsVisible())
            togglePassword.click();
    }

    private boolean passwordIsVisible() {

        return passwordInput.getAttribute("type").contains("text");

    }


}
