package pages.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.admin.AddNewHospitalPage;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import utils.BrowserWrapper;
import pages.admin.HospitalListPage;


/**
 * Created by Evgen on 06.04.2017.
 */
public class AdminHeader extends AuthorizedHeader {

    public AdminHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    private WebElement actions;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    private WebElement allUsersIco;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    private WebElement addUserIco;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[3]/a")
    private WebElement hospitalListIco;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[4]/a")
    private WebElement addHospitalIco;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[5]/a")
    private WebElement googlePoiIco;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[6]/a")
    private WebElement editManagers;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[7]/a")
    private WebElement tokenConfig;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[8]/a")
    private WebElement addDepartment;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    private WebElement profile;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/ul/li[1]/a")
    private WebElement myProfile;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/ul/li[2]/a")
    private WebElement logOut;


    public AllUsersPage allUsersPage () {
        allUsersIco.click();
        return new AllUsersPage(driver);
    }

    public AddUserPage goToAddUserPage() {
        actions.click();
        BrowserWrapper.waitUntilElementClickable(addUserIco);
        addUserIco.click();
        return new AddUserPage(driver);
    }

    public HospitalListPage goToAllHospitalsPage() {
        actions.click();
        hospitalListIco.click();
        return new HospitalListPage(driver);
    }

    public AddNewHospitalPage goToAddNewHospitalPage() {
        actions.click();
        addHospitalIco.click();
        return new AddNewHospitalPage(driver);
    }
}
