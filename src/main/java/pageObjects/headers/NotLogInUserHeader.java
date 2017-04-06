package pageObjects.headers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Evgen on 05.04.2017.
 */
public class NotLogInUserHeader extends BaseGeneralHeader {
    @FindBy(css = "a[href$='/HospitalSeeker/login']")
    protected WebElement login;

    public NotLogInUserHeader(WebDriver driver) {
        super(driver);
    }

}
