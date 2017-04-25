package pages.headers.headersByRole;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import pages.allUsers.HospitalSeekerHomePage;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 12.04.2017.
 */
public class AuthorizedHeader extends BaseHeader{
    public AuthorizedHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul.my-navbar>li:nth-last-child(3)")
    protected WebElement profileButton;

    @FindBy(css = "a[href=\"/HospitalSeeker/logout\"]")
    protected WebElement logoutButton;

    @FindBy(css = "a[data-target=\"#myModal\"]")
    private WebElement myProfile;

    public void profileButtonClick() {
        BrowserWrapper.waitUntilElementClickable(profileButton);
        profileButton.click();
    }

    public HospitalSeekerHomePage logoutButtonClick() {
        BrowserWrapper.waitUntilElementClickable(logoutButton);
        logoutButton.click();
        return new HospitalSeekerHomePage(driver);
    }
}
