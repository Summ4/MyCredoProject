package app.popup;

import org.openqa.selenium.By;
import utils.Browser;
import utils.Configuration;

public class LanguageChangePopup extends Popup {

    private By languageENG = By.xpath("//p[text()='English']");
    private By languageGEO = By.xpath("//div[@class='sub-language']//p[text()='ქართული']");

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
