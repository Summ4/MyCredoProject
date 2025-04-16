package common;

import utils.Browser;
import org.openqa.selenium.By;

public class CheckboxComponent extends Component {
    private final By checkBox;

    public CheckboxComponent(Browser browser, String checkboxXpath) {
        super(browser);
        checkBox = By.xpath(checkboxXpath);
    }

    public void check() {
        if (!isChecked())
            browser.click(checkBox);
    }

    public void uncheck() {

        if (isChecked())
            browser.click(checkBox);

    }

    public boolean isChecked() {

        String classAttribute = browser.getAttribute(checkBox, "class");
        return classAttribute.contains("x-form-cb-checked");

    }

}