
package tests.AdminCreateUserTest;


import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected String BASE_URL = "https://localhost:8443/HospitalSeeker";
    ProfilesIni profile = new ProfilesIni();
    FirefoxProfile ffProfile;
    WebDriver driver;
    WebDriverWait wait;

    public BaseTest() {
        ffProfile = this.profile.getProfile("gregtar");
        driver = new FirefoxDriver(ffProfile);
        wait = new WebDriverWait(driver, 10L);
    }

    @BeforeClass
    public void setUp() {
        driver.get(BASE_URL);
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDown() throws Exception {
    }

    private boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException var3) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException var2) {
            return false;
        }
    }

    static {
        System.setProperty("webdriver.gecko.driver", "/Users/gregtar/Downloads/geckodriver");
    }
}
