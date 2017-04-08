package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.AdminHeader;

public class CheckGooglePOIPage extends PageObject{

    public CheckGooglePOIPage(WebDriver driver) {
        super(driver, new AdminHeader(driver));
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/button")
    private WebElement getGooglePoiButton;

    public void pushGetGooglePoi() {
        getGooglePoiButton.click();
    }
}
