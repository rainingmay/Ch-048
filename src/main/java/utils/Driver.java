package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.concurrent.TimeUnit;

/**
 * Created by radgast on 4/20/17.
 */
public class Driver {

    private static final String FIREFOX_PROFILE_NAME = "myProfile";
    private static final String WEBDRIVER_NAME = "webdriver.gecko.driver";
    private static final String LINUX_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver";
    private static final String MACOS_WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver";
    private static final String WEBDRIVER_PATH = "src/main/resources/drivers/geckodriver.exe";
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    private static WebDriver driver;


    public static void initialization(){
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
            case "Windows 7":
                System.setProperty(WEBDRIVER_NAME, WEBDRIVER_PATH);
                break;
            case "MacOS":
                System.setProperty(WEBDRIVER_NAME, MACOS_WEBDRIVER_PATH);
                break;
        }




        driver = new FirefoxDriver(ffProfile);

        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);



    }
    public static WebDriver instance(){
        return driver;
    }

    public static void close(){
        driver.quit();
    }

}
