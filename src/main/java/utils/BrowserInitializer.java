package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by radgast on 4/19/17.
 */
public class BrowserInitializer {
    private static final String FIREFOX_PROFILE_NAME = "default";
    private static final String WEBDRIVER_NAME = "webdriver.gecko.driver";
    private static final String LINUX_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String MACOS_WEBDRIVER_PATH = "src/main/resources/geckodriver";
    private static final String WEBDRIVER_PATH = "src/main/resources/geckodriver.exe";
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    private static WebDriverWait wait;

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver browserInitialization() {

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFILE_NAME);
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
            case "MacOS":
                 System.setProperty(WEBDRIVER_NAME, MACOS_WEBDRIVER_PATH);
                break;
            case "Windows 7":
                System.setProperty(WEBDRIVER_NAME, WEBDRIVER_PATH);
                break;
        }
        WebDriver driver = new FirefoxDriver(ffProfile);
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       wait = new WebDriverWait(driver,30, 250);
        return driver;
    }

    public static void browserClose(WebDriver driver) {
        driver.quit();
    }
}
