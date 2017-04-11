package pageObjects.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.anonymous.LoginPage;
import pageObjects.headers.BaseHeader;

/**
 * Created by Evgen on 05.04.2017.
 */
public class NotLogInUserHeader extends BaseHeader {
    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[4]/a")
    protected WebElement login;
//
    public LoginPage loginButton(){
        login.click();
        return new LoginPage(driver);
    }

    public NotLogInUserHeader(WebDriver driver) {
        super(driver);
    }

}
