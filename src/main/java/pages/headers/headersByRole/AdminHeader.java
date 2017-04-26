package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.admin.AddNewHospitalPage;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import pages.admin.HospitalListPage;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 06.04.2017.
 */
public class AdminHeader extends AuthorizedHeader {


//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    @FindBy(linkText = "Actions")
    private WebElement actions;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/users?status=true\"]")
    private WebElement allUsersIco;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/newUser\"]")
    private WebElement addUserIco;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[3]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/listhospitals\"]")
    private WebElement hospitalListIco;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[4]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/new\"]")
    private WebElement addHospitalIco;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[5]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/validate\"]")
    private WebElement googlePoiIco;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[6]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/editHospitalsManagers\"]")
    private WebElement editManagers;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[7]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/configureToken\"]")
    private WebElement tokenConfig;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[8]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/admin/departmentName\"]")
    private WebElement addDepartment;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    @FindBy(css = "a.active")
    private WebElement profile;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/ul/li[1]/a")
    @FindBy(css = "a.glyphicon")
    private WebElement myProfile;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/ul/li[2]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/logout\"]")
    private WebElement logOut;


    public AllUsersPage allUsersPage () {
        allUsersIco.click();
        return new AllUsersPage();
    }

    public AddUserPage goToAddUserPage() {
        actions.click();
        BrowserWrapper.waitUntilElementClickable(addUserIco);
        addUserIco.click();
        return new AddUserPage();
    }

    public HospitalListPage goToAllHospitalsPage() {
        actions.click();
        hospitalListIco.click();

        return new HospitalListPage();
    }

    public AddNewHospitalPage goToAddNewHospitalPage() {
        actions.click();
        addHospitalIco.click();
        return new AddNewHospitalPage();
    }
}
