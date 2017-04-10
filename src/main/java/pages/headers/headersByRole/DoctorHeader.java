package pages.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headers.BaseHeader;

/**
 * Created by Evgen on 06.04.2017.
 */
public class DoctorHeader extends BaseHeader {
    public DoctorHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    private WebElement patients;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    private WebElement schedule;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[6]/a")
    private WebElement profile;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[6]/ul/li[2]/a")
    private WebElement logOut;

}
