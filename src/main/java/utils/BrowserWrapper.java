package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Evgen on 11.04.2017.
 */
public class BrowserWrapper {

    private static final String FIREFOX_PROFIE_NAME = "myProfile";
    private static final String WEBDRIVER_NAME = "webdriver.gecko.driver";
    private static final String LINUX_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String WEBDRIVER_PATH = "src/main/resources/geckodriver.exe";
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    private static WebDriverWait wait;


    public static WebDriver browserInitialization() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFIE_NAME);
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);
        String osName = System.getProperty("os.name");
        switch (osName){
            case "Linux":
                System.setProperty(WEBDRIVER_NAME, LINUX_WEBDRIVER_PATH);
                break;
            case "Windows 10":
                System.setProperty(WEBDRIVER_NAME, WEBDRIVER_PATH);
                break;
        }

        WebDriver driver = new FirefoxDriver(ffProfile);
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver,10);
        return driver;
    }

    public static void browserClose(WebDriver driver) {
        driver.quit();
    }

    public static boolean isElementPresent(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementEnable(WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitUntilAlertIsPresent() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitUntilElementSelectionState(WebElement element, boolean bool) {
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, bool));
    }

    public static void waitUntilElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public static void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilTitleContains(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }

    public static void waitUntilAllVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static void waitUntilElementIsPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitUntilUrlAvaliable(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public static void selectDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
    public static void sleep(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
