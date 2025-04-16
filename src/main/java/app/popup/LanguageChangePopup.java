package app.popup;

import org.openqa.selenium.By;
import utils.Browser;

public class LanguageChangePopup extends Popup {

    private final By languageENG = By.xpath("//p[text()='English']");
    private final By languageGEO = By.xpath("//div[@class='sub-language']//p[text()='ქართული']");

    public LanguageChangePopup(Browser browser) {
        super(browser);
    }

    public void clickOnLanguageEng() {
        browser.click(languageENG);
    }

    public void clickOnLanguageGeo() {
        browser.click(languageGEO);
    }

}
