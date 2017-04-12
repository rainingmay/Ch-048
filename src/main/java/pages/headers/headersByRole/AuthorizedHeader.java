package pages.headers.headersByRole;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import pages.allUsers.HospitalSeekerHomePage;
import pages.headers.BaseHeader;

/**
 * Created by Evgen on 12.04.2017.
 */
public class AuthorizedHeader extends BaseHeader{
    public AuthorizedHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "ul.my-navbar>li:nth-last-child(3)")
    protected WebElement profileButton;

    protected WebElement logoutButton;

    protected void profileButtonClick() {
        profileButton.click();
        logoutButton = driver.findElement(By.cssSelector("ul.my-navbar>li:nth-last-child(3) li:last-child a"));
    }

    public HospitalSeekerHomePage logout() {
        profileButton.click();
        logoutButton.click();
        return new HospitalSeekerHomePage(driver);
    }
}
