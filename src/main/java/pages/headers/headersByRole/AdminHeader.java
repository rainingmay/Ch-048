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

    @FindBy(linkText = "Actions")
    private WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/users?status=true\"]")
    private WebElement allUsersIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/newUser\"]")
    private WebElement addUserIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/newUser\" ]")
    private WebElement hospitalListIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/new\"]")
    private WebElement addHospitalIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/validate\"]")
    private WebElement googlePoiIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/editHospitalsManagers\"]")
    private WebElement editManagers;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/configureToken\"]")
    private WebElement tokenConfig;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/departmentName\"]")
    private WebElement addDepartment;


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
