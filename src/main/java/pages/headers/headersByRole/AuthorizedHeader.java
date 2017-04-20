package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.HospitalSeekerHomePage;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 12.04.2017.
 */
public class AuthorizedHeader extends BaseHeader{

    @FindBy(css = "ul.my-navbar>li:nth-last-child(3)")
    protected WebElement profileButton;

    @FindBy(css = "ul.my-navbar>li:nth-last-child(3) li:last-child a")
    protected WebElement logoutButton;


    public void profileButtonClick() {
        BrowserWrapper.waitUntilElementClickable(profileButton);
        profileButton.click();
    }

    public HospitalSeekerHomePage logoutButtonClick() {
        BrowserWrapper.waitUntilElementClickable(logoutButton);
        logoutButton.click();
        return new HospitalSeekerHomePage();
    }
}
