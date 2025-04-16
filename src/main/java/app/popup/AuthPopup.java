package app.popup;

import common.ButtonComponent;
import common.InputComponent;
import utils.Browser;

public class AuthPopup extends Popup {

    public InputComponent otpInput;
    public ButtonComponent submitButton;

    public AuthPopup(Browser browser) {
        super(browser);
        otpInput = new InputComponent(browser, "//input[@id='otpCodeInput']");
        submitButton = new ButtonComponent(browser, "//app-otp[@id='otpSend']//button");
    }

}
