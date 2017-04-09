package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.BaseHeader;
import pageObjects.headers.headersByRole.AdminHeader;

/**
 * Created by Evgen on 09.04.2017.
 */
public class GooglePOI extends PageObject{

    public GooglePOI(WebDriver driver) {
        super(driver, new AdminHeader(driver));
    }

    @FindBy(css = "btn btn-default ")
    private WebElement getGooglePOIButton;

    public GooglePOI getGooglePOI() {
        getGooglePOIButton.click();
        return new GooglePOI(driver);
    }
}
