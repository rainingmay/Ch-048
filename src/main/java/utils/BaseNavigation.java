package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.admin.AllUsersPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.allUsers.PageObject;
import pages.anonymous.LoginPage;
import pages.headers.headersByRole.AuthorizedHeader;
import pages.manager.HospitalsPage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {


    public static void login(WebDriver driver, String email, String password) throws InterruptedException{
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage(driver);
        LoginPage loginPage = hospitalSeekerHomePage.notLogInUserHeader.loginButton();
        loginPage.authorization(email, password);
    }


    public static HospitalSeekerHomePage logout(WebDriver driver) throws InterruptedException{
        AuthorizedHeader authorizedHeader = new AuthorizedHeader(driver);
        authorizedHeader.profileButtonClick();
        HospitalSeekerHomePage hospitalSeekerHomePage = authorizedHeader.logoutButtonClick();
        return hospitalSeekerHomePage;
    }


    public static void doubleClick(WebDriver driver, WebElement element){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }


    public static AllUsersPage loginAsAdmin(WebDriver driver, String email, String password) throws InterruptedException {
        login(driver, email, password);
        return new AllUsersPage(driver);
    }

    public static HospitalsPage loginAsManager(WebDriver driver, String email, String password) throws InterruptedException {
        login(driver, email, password);
        return new HospitalsPage(driver);
    }

    public static PageObject loginAsDoctor(WebDriver driver, String email, String password) {
        return null;
    }


}
