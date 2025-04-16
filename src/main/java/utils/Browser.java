package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Getter
public class Browser {

    private WebDriver driver;
    private JavascriptExecutor js;
    private final WebDriverWait wait;

    @SneakyThrows
    Browser() {

        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Configuration.DEFAULT_WAIT_TIME));

    }

    public void openMycredoAuth() {
        this.driver.get(Configuration.BASE_MYCREDO_URL);
    }

    public ByteArrayInputStream takeScreenShot() {

        return new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));

    }

    public void quitBrowser() {
        this.driver.quit();
    }

    public void click(By by) {
        WebElement webElement = wait.until(elementToBeClickable(by));
        webElement.click();
    }

    @SneakyThrows
    public <T> T click(By by, Class<T> navigationClass) {
        this.click(by);

        return navigationClass.getDeclaredConstructor(Browser.class).newInstance(this);
    }

    public void inputText(By by, String text) {
        WebElement element = wait.until(presenceOfElementLocated(by));
        element.clear();
        element.sendKeys(text);
    }

    public void inputOTPCode(By by, String otp) {

        WebElement otpInput = wait.until(presenceOfElementLocated(by));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='%s'; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));".formatted(otp), otpInput);

    }

    public boolean waitUntilVisible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
    }

    public String getText(By by) {
        WebElement webElement = wait.until(visibilityOfElementLocated(by));
        String text = webElement.getText();
        return text.trim();
    }

    public boolean isDisabled(By by) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));

        String classAttribute = element.getDomAttribute("class");
        return !element.isEnabled() || Objects.requireNonNull(classAttribute).contains("x-item-disabled");
    }

    public String getAttribute(By by, String attributeName) {
        WebElement webElement = wait.until(presenceOfElementLocated(by));
        return Objects.requireNonNull(webElement.getDomAttribute(attributeName)).strip();
    }

    public ExpectedCondition<WebElement> elementToBeClickable(By locator) {
        return driver -> {
            try {
                WebElement element = visibilityOfElementLocated(locator).apply(driver);
                String classAttribute = element != null ? element.getDomAttribute("class") : "";
                return (element != null && element.isEnabled() && (classAttribute == null || !classAttribute.contains("x-disabled"))) ? element : null;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        };
    }

}
