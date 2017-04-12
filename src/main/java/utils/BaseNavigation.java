package utils;

import org.openqa.selenium.*;
import pages.admin.AllUsersPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.allUsers.PageObject;
import pages.anonymous.LoginPage;
import pages.manager.HospitalsPage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {

    public static void login(WebDriver driver, String email, String password) throws InterruptedException {

        BrowserWrapper.sleep(3);
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a[href=\'/HospitalSeeker/login\']")).click();
        BrowserWrapper.sleep(3);
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        BrowserWrapper.sleep(1);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        BrowserWrapper.sleep(1);
        driver.findElement(By.id("loginSubmit")).click();

    }

    public static void login1(WebDriver driver, String email, String password) throws InterruptedException{
        BrowserWrapper.sleep(3);
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage(driver);
        LoginPage loginPage = hospitalSeekerHomePage.notLogInUserHeader.loginButton();
        BrowserWrapper.sleep(3);
        loginPage.enterEmail(email);
        BrowserWrapper.sleep(3);
        loginPage.enterPassword(password);
        BrowserWrapper.sleep(1);
        loginPage.loginSubmitButton();
    }


    public static void logout(WebDriver driver) throws InterruptedException {
        BrowserWrapper.sleep(2);
        driver.findElement(By.cssSelector("ul.my-navbar>li:nth-last-child(3)")).click();
        driver.findElement(By.cssSelector("ul.my-navbar>li:nth-last-child(3) li:last-child a")).click();
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
