package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.BaseHeader;
import pageObjects.headers.headersByRole.AdminHeader;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AddUserPage extends PageObject{

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
    private WebElement roleLable;

    @FindBy(id = "userRoles")
    private WebElement userRolesSelect;

    @FindBy(xpath = "//*[@id=\"enabledUserCheckbox\"]/button")
    private WebElement enableButton;

    @FindBy(id = "newUserSubmit")
    private WebElement newUserButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public AllUsersPage submitAdding() {
        newUserButton.click();
        return new AllUsersPage(driver);
    }

    public AllUsersPage cancelAdding() {
        cancelButton.click();
        return new AllUsersPage(driver);
    }

    public void enterEmail(String value){
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public void enterPassword(String value){
        passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void confirmPassword(String value){
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(value);
    }

    public void selectRole(String value){
        Select dropdown = new Select(userRolesSelect);
        dropdown.selectByValue(value);

    }

    public void enableButtonClick(){enableButton.click();}




    public void addNewUser(String email, String password, String role) throws InterruptedException {
        Thread.sleep(1000);
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        selectRole(role);
        enableButtonClick();
        submitAdding();

    }
}

