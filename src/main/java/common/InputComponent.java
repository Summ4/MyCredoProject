package common;

import utils.Browser;
import org.openqa.selenium.By;

public class InputComponent extends Component {

    private final By textInput;

    public InputComponent(Browser browser, String inputXpath) {
        super(browser);
        this.textInput = By.xpath(inputXpath);
    }

    public void inputText(String text) {
        browser.inputText(this.textInput, text);
    }

    public String getAttribute(String attributeName) {
        return browser.getAttribute(this.textInput, attributeName);
    }

    public void inputOTP(String text){
        browser.inputOTPCode(this.textInput, text);
    }

}
