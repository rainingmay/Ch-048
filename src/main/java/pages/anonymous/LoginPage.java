package pages.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class LoginPage extends PageObject {
    public BaseHeader baseHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
        baseHeader = new BaseHeader(driver);
    }
    @FindBy(xpath = "//*[@id=\"loginForm\"]/fieldset/h2")
    private WebElement loginText;

    @FindBy(id="email")
    public WebElement emailTextField;

    @FindBy(id="password")
    private WebElement passwordTextField;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/fieldset/span/button")
    private WebElement rememberMeButton;

    @FindBy(id="forgotButton")
    private WebElement forgotPasswordButton;

    @FindBy(id="loginSubmit")
    private WebElement loginSubmitButton;

    @FindBy(id="registerRedirect")
    private WebElement registerButton;

    public void authorization(String email, String password){
        emailTextField.clear();
        emailTextField.sendKeys(email);
        passwordTextField.clear();
        passwordTextField.sendKeys(password);
        loginSubmitButton.click();
    }

//    public void enterEmail(String email){
//
//                emailTextField.clear();
//                emailTextField.sendKeys(email);
//
//    }
//
//    public void enterPassword(String email){
//        passwordTextField.clear();
//        passwordTextField.sendKeys(email);
//    }

    public void rememberMeButtonClick(){
        rememberMeButton.click();
    }

    public void forgotPasswordButton(){
        forgotPasswordButton.click();
    }

    public void loginSubmitButton(){
        loginSubmitButton.click();
    }



}
