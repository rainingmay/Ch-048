package pages.headers.headersByRole;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.anonymous.LoginPage;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 05.04.2017.
 */
public class NotLogInUserHeader extends BaseHeader {

    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[4]/a")
    protected WebElement login;

    public LoginPage loginButton(){
        try {
            login.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }

    public NotLogInUserHeader(WebDriver driver) {
        super(driver);
    }

}
