package pageObjects.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.headers.BaseHeader;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AdminHeader extends BaseHeader{

    public AdminHeader(WebDriver driver) {
        super(driver);
    }


    @FindBy(className = "nav navbar-nav dropdown dropdown-toggle active")
    private WebElement actions;

    @FindBy(className = "glyphicon glyphicon-list")
    private WebElement allUsersIco;

    @FindBy(className = "glyphicon glyphicon-plus-sign")
    private WebElement addUserIco;

    @FindBy(className = "glyphicon glyphicon-calendar")
    private WebElement hospitalListIco;

    @FindBy(css = "a[href$='/HospitalSeeker/admin/map/new']")
    private WebElement addHospitalIco;

    @FindBy(className = "glyphicon glyphicon-check")
    private WebElement googlePoiIco;

    @FindBy(className = "glyphicon glyphicon-briefcase")
    private WebElement editManagers;

    @FindBy(className = "glyphicon glyphicon-asterisk")
    private WebElement tokenConfig;

    @FindBy(css = "a[href$='/HospitalSeeker/admin/departmentName']")
    private WebElement addDepartment;




}
