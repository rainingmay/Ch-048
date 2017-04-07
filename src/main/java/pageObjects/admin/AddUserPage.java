package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.BaseHeader;
import pageObjects.headers.headersByRole.AdminHeader;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AddUserPage extends PageObject{

    public AddUserPage(WebDriver driver, AdminHeader header) {
        super(driver, header);
    }


    @FindBy(css = "/html/body/section/div/div/div/div/div/form/fieldset/h1")
    private WebElement addUserLabel;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(className = "col-sm-2 control-label")
    private WebElement roleLable;

    @FindBy(id = "userRoles")
    private WebElement userRoles;

    @FindBy(className = "btn btn-default")
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

}
