package utilities;

import org.openqa.selenium.*;
import pageObjects.admin.AllUsersPage;
import pageObjects.allUsers.HospitalSeekerHomePage;
import pageObjects.allUsers.PageObject;
import pageObjects.anonymous.LoginPage;
import pageObjects.headers.BaseHeader;
import pageObjects.interfaces.AfterLogin;
import pageObjects.manager.HospitalsPage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {

    public static void login(WebDriver driver, String email, String password) throws InterruptedException {
//TODO in proccess of designe
//        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage(driver);
//        LoginPage loginPage = hospitalSeekerHomePage.moveToLoginPage();
//        loginPage.enterEmail(email);
//        loginPage.enterPassword(password);
//        loginPage.loginSubmitButton();

        driver.findElement(By.cssSelector("a[href=\"/HospitalSeeker/login\"]")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginSubmit")).click();
    }




    public static void doubleClick(WebDriver driver, WebElement element){

        JavascriptExecutor js = (JavascriptExecutor)driver;
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public static AllUsersPage loginAsAdmin(WebDriver driver, String email, String password) throws InterruptedException {
        login(driver, email, password);
        return new AllUsersPage(driver);
    }

    public static PageObject loginAsManager(WebDriver driver, String email, String password) throws InterruptedException {
        login(driver, email, password);
        return new HospitalsPage(driver);
    }

    public static PageObject loginAsDoctor(WebDriver driver, String email, String password) {
        return null;
    }

}
