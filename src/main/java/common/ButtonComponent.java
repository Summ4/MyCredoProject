package common;


import org.openqa.selenium.WebElement;
import utils.Browser;
import org.openqa.selenium.By;

import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class ButtonComponent extends Component {
    private final By button;

    public ButtonComponent(Browser browser, String buttonXpath) {
        super(browser);
        button = By.xpath(buttonXpath);
    }

    public void click() {
        browser.click(button);
    }

    public <T> T click(Class<T> navigationClass) {
        return browser.click(button, navigationClass);
    }

    public boolean isDisabled() {
        return browser.isDisabled(button);
    }

    public String getText(){
        return browser.getText(button);
    }


}