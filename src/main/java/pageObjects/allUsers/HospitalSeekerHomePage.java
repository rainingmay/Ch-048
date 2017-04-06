package pageObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.headers.BaseHeader;

/**
 * Created by Evgen on 05.04.2017.
 */
public class HospitalSeekerHomePage {
    private WebDriver driver;
    private BaseHeader header;

    public HospitalSeekerHomePage(WebDriver driver, BaseHeader header) {
        this.driver = driver;
        this.header = header;
    }
}
