package utils;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import page.LoginPage;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class BaseSetup {

    private static Browser browser;
    private static LoginPage loginPage;

    @BeforeSuite
    public void baseBeforeSuite() {

        browser = new Browser();

        loginPage = new LoginPage(getBrowser());
        setAllureEnvironment();
    }

    @BeforeMethod
    public void beforeMethod() {
        getBrowser().openMycredoAuth();
    }

    private Browser getBrowser() {
        return browser;
    }

    protected static LoginPage getLoginPage() {
        return loginPage;
    }

    @AfterSuite
    public void baseAfterSuite() {
        getBrowser().quitBrowser();
    }

    public void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Utils.Browser", "Chrome")
                        .put("OS", "Windows 10")
                        .put("Environment", "TEST")
                        .build(),
                System.getProperty("user.dir") + "/allure-results/");
    }

    @AfterMethod
    public void attachments(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) return;
        try {
            Allure.addAttachment(result.getName() + " - Screenshot", getBrowser().takeScreenShot());
        } catch (Exception e) {
            Allure.addAttachment(result.getName() + " - Error", "Failed to capture screenshot: " + e.getMessage());
        }
    }

}
