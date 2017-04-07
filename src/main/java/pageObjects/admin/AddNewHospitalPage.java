package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.AdminHeader;

public class AddNewHospitalPage extends PageObject{
    private WebDriver driver;
    private AdminHeader header;

    public AddNewHospitalPage(WebDriver driver) {
        super(driver, new AdminHeader(driver));
    }

    @FindBy(id = "addressGeo")
    private WebElement addressInputField;

    @FindBy(id = "address.country")
    private WebElement countryInputField;

    @FindBy(id = "address.city")
    private WebElement cityInputField;

    @FindBy(id = "address.street")
    private WebElement streetInputField;

    @FindBy(id = "address.building")
    private WebElement buildingInputField;

    @FindBy(id = "name")
    private WebElement nameInputField;

    @FindBy(id = "description")
    private WebElement descriptionInputField;

    @FindBy(id = "button-find")
    private WebElement findButton;

    @FindBy(id = "button-fill")
    private WebElement fillButton;

    @FindBy(id = "button-save")
    private WebElement saveButton;

    @FindBy(id = "button-reset")
    private WebElement resetButton;

    public void addressData(String text) {
        addressInputField.clear();
        addressInputField.sendKeys(text);
    }

    public void changeCountry(String text) {
        countryInputField.clear();
        countryInputField.sendKeys(text);
    }

    public void changeCity(String text) {
        cityInputField.clear();
        cityInputField.sendKeys(text);
    }

    public void changeStreet(String text) {
        streetInputField.clear();
        streetInputField.sendKeys(text);
    }

    public void changeBuilding(String text) {
        buildingInputField.clear();
        buildingInputField.sendKeys(text);
    }

    public void addHospitalName(String text) {
        nameInputField.clear();
        nameInputField.sendKeys(text);
    }

    public void addHospitalDescription(String text) {
        descriptionInputField.clear();
        descriptionInputField.sendKeys(text);
    }

   public void pushFind() {
        findButton.click();
   }

   public void pushFill() {
        fillButton.click();
   }

   public void pushReset() {
        resetButton.click();
   }

   public HospitalListPage pushSave() {
        saveButton.click();
        return new HospitalListPage(driver);
   }
}
