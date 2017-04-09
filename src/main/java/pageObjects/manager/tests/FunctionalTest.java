package pageObjects.manager.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by radga on 07.04.2017.
 */
public class FunctionalTest {

    protected static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile("QAAutomation");
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);
        System.setProperty("webdriver.gecko.driver", "C:\\\\drivers\\geckodriver.exe");
        driver = new FirefoxDriver(ffProfile);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }



    @AfterClass
    public static void tearDown(){
        driver.close();
    }
}
