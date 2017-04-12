package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;


public class CheckGooglePOIPage extends PageObject {
    public AdminHeader header;

    public CheckGooglePOIPage(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/button")
    private WebElement getGooglePoiButton;

    public void pushGetGooglePoi() {
        getGooglePoiButton.click();
    }
}
