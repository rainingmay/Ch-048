package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageObjects.allUsers.HospitalSeekerHomePage;
import pageObjects.anonymous.LoginPage;
import pageObjects.headers.headersByRole.ManagerHeader;


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

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public static void doubleClick(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String doubleClickJS = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject){ arguments[0].fireEvent('ondblclick');}window.stop();";
        js.executeScript(doubleClickJS, element);
    }
    public static void logout(WebDriver driver) throws InterruptedException {
        ManagerHeader managerHeader = new ManagerHeader(driver);
        managerHeader.logOut();

    }
}
