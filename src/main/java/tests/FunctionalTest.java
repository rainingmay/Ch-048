package tests;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Evgen on 10.04.2017.
 */
public class FunctionalTest {

    protected WebDriver driver;
    protected static final String FIREFOX_PROFIE_NAME = "default";
    protected static final String WEBDRIVER_NAME = "webdriver.gecko.driver";
    protected static final String WEBDRIVER_PATH = "src/main/resources/geckodriver.exe";
    protected static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";

    @BeforeClass(alwaysRun = true)
    public  void setUp(){
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile(FIREFOX_PROFIE_NAME);
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);
        System.setProperty(WEBDRIVER_NAME, WEBDRIVER_PATH);
        driver = new FirefoxDriver(ffProfile);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(BASE_URL);

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}
