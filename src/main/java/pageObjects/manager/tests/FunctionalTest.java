package pageObjects.manager.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by radga on 07.04.2017.
 */
public class FunctionalTest {

    protected  WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public  void setUp(){
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffProfile = profile.getProfile("QAAutomation");
        ffProfile.setAcceptUntrustedCertificates(true);
        ffProfile.setAssumeUntrustedCertificateIssuer(false);
       System.setProperty("webdriver.gecko.driver", "/home/radgast/geckodriver");
        driver = new FirefoxDriver(ffProfile);
        //driver = new HtmlUnitDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://localhost:8443/HospitalSeeker/");

    }



    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
    }
}
