package pages.admin;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 06.04.2017.
 */
public class AddUserPage extends PageObject {

    public AdminHeader header;

    public AddUserPage(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
    }


    @FindBy(css = "/html/body/section/div/div/div/div/div/form/fieldset/h1")
    private WebElement addUserLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(className = "col-sm-2 control-label")
    private WebElement roleLabel;

    @FindBy(id = "userRoles")
    private WebElement userRolesSelect;

    @FindBy(xpath = "//*[@id=\"enabledUserCheckbox\"]/button")
    private WebElement enableButton;

    @FindBy(id = "newUserSubmit")
    private WebElement newUserButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id="email-error")
    public WebElement emailErrorLabel;

    @FindBy(id="password-error")
    private WebElement passwordErrorLabel;


    @FindBy(id = "userRoles-error")
    public WebElement userRolesErrorLabel;

    private boolean checkEmailInput(){
        return BrowserWrapper.isElementPresent(emailInput);
    }

    private boolean checkPasswordInput(){
        return BrowserWrapper.isElementPresent(passwordInput);
    }

    private boolean checkConfirmPasswordInput(){
        return BrowserWrapper.isElementPresent(confirmPasswordInput);
    }

    private boolean checkRoleLabel(){
        return BrowserWrapper.isElementPresent(roleLabel);
    }

    private boolean checkUserRolesSelect(){
        return BrowserWrapper.isElementPresent(userRolesSelect);
    }

    private boolean checkEnableButton(){
        return BrowserWrapper.isElementPresent(enableButton);
    }

    private boolean checkNewUserButton(){
        return BrowserWrapper.isElementPresent(newUserButton);
    }

    private boolean checkCancelButton(){
        return BrowserWrapper.isElementPresent(cancelButton);
    }

    private boolean checkAddUserLabel(){
        return BrowserWrapper.isElementPresent(addUserLabel);
    }

    public boolean isPageReady() throws Exception {

        BrowserWrapper.waitUntilElementVisible(addUserLabel);
        StringBuilder errors = new StringBuilder();
        if(!checkAddUserLabel()){
            errors.append("Add User Label\n");
        }
        if(!checkEmailInput()){
            errors.append("Email Input\n");
        }
        if(!checkPasswordInput()){
            errors.append("Password Input\n");
        }
        if(!checkConfirmPasswordInput()){
            errors.append("Confirm Password Input\n");
        }
        if(!checkRoleLabel()){
            errors.append("Roles Label\n");
        }
        if(!checkUserRolesSelect()){
            errors.append("User Roles Selector\n");
        }
        if(!checkEnableButton()){
            errors.append("Enable Button\n");
        }
        if(!checkNewUserButton()){
            errors.append("New User Button\n");
        }
        if(!checkCancelButton()){
            errors.append("Cancel Button\n");
        }
        if(!errors.toString().isEmpty()){
            errors.append("are not present");
            throw new NoSuchElementException(errors.toString());
        }
        return true;
    }

    public AllUsersPage submitAdding() {
        newUserButton.click();
        return new AllUsersPage(driver);
    }

    public AllUsersPage cancelAdding() {
        cancelButton.click();
        return new AllUsersPage(driver);
    }

    public void enterEmail(String value) {
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public void enterPassword(String value) {
        passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void confirmPassword(String value) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(value);
    }

    public void selectRole(String value) {
        Select dropdown = new Select(userRolesSelect);
        dropdown.selectByValue(value);

    }

    public void enableButtonClick() {
        enableButton.click();
    }


    public void addNewUser(String email, String password, String role) throws InterruptedException {
        BrowserWrapper.sleep(1);
        driver.get("https://localhost:8443/HospitalSeeker/admin/newUser");
        Thread.sleep(1000);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        selectRole(role);
        enableButtonClick();
        submitAdding();

    }

    public void addNewUserWithotRole(String email, String password) throws InterruptedException {
        BrowserWrapper.sleep(1);
        driver.get("https://localhost:8443/HospitalSeeker/admin/newUser");
        Thread.sleep(1000);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        enableButtonClick();
        submitAdding();

    }

}

