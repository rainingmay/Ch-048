package utils;

import org.openqa.selenium.WebDriver;
import pages.admin.AllUsersPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.anonymous.LoginPage;
import pages.headers.headersByRole.AuthorizedHeader;
import pages.manager.HospitalsPage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {


    public static void login(WebDriver driver, String email, String password) {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage(driver);
        LoginPage loginPage = hospitalSeekerHomePage.notAuthorizedHeader.loginButton();
        loginPage.authorization(email, password);
    }


    public static HospitalSeekerHomePage logout(WebDriver driver) {
        AuthorizedHeader authorizedHeader = new AuthorizedHeader(driver);
        authorizedHeader.profileButtonClick();
        HospitalSeekerHomePage hospitalSeekerHomePage = authorizedHeader.logoutButtonClick();
        return hospitalSeekerHomePage;
    }


    public static AllUsersPage loginAsAdmin(WebDriver driver, String email, String password) {
        login(driver, email, password);
        return new AllUsersPage(driver);
    }

    public static HospitalsPage loginAsManager(WebDriver driver, String email, String password)  {
        login(driver, email, password);
        return new HospitalsPage(driver);
    }

    public static HospitalSeekerHomePage loginAsDoctor(WebDriver driver, String email, String password)  {
        login(driver, email, password);
        return new HospitalSeekerHomePage(driver) ;
    }


}
