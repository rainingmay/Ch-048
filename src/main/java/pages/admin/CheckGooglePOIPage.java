package pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.AdminHeader;


public class CheckGooglePOIPage extends BasePage {
    public AdminHeader header;

    public CheckGooglePOIPage() {
        this.header = new AdminHeader();
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/button")
    private WebElement getGooglePoiButton;

    public void pushGetGooglePoi() {
        getGooglePoiButton.click();
    }
}
