package pageObjects.anonymous;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.BaseHeader;

/**
 * Created by ytomktc on 07.04.2017.
 */
public class LoginPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"loginForm\"]/fieldset/h2")
    private WebElement loginText;

    @FindBy(id="email")
    private WebElement emailTextField;

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

    public void enterEmail(String email){
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }

    public void enterPassword(String email){
        passwordTextField.clear();
        passwordTextField.sendKeys(email);
    }

    public void rememberMeButtonClick(){
        rememberMeButton.click();
    }

    public void forgotPasswordButton(){
        forgotPasswordButton.click();
    }

    public void loginSubmitButton(){
        loginSubmitButton.click();
    }

    public LoginPage(WebDriver driver, BaseHeader header) {
        super(driver, header);
    }
}
