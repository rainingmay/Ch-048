package pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by Jeksonis on 06.04.2017.
 */
public class AddNewHospitalPage extends BasePage {
    public AdminHeader header;

    public AddNewHospitalPage(){
        this.header = new AdminHeader();
    }

    @FindBy(id = "image-uploaded")
    private WebElement imageButton;

    @FindBy(id = "googleMap")
    private WebElement googleMap;

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

    @FindBy(xpath = "//*[@id=\"button-find\"]")
    private WebElement findButton;

    @FindBy(id = "button-fill")
    private WebElement fillButton;

    @FindBy(id = "button-save")
    private WebElement saveButton;

    @FindBy(id = "button-reset")
    private WebElement resetButton;

    @FindBy(css = ".ol-md-12")
    public WebElement pageLabel;

    public void setClipboardData(String pathToPhoto) {
        StringSelection stringSelection = new StringSelection(pathToPhoto);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public void addNewHospitalPhoto() throws AWTException {
        setClipboardData("D:\\RhodeIslandHosp14_360_360_90.jpg");
        Robot robot = new Robot();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
    }

    public void pushAddPhotoButton() {
        imageButton.click();
    }

    public void addressData(String text) {
        addressInputField.clear();
        addressInputField.sendKeys(text);
    }

    public AddNewHospitalPage changeCountry(String text) {
        countryInputField.clear();
        countryInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public AddNewHospitalPage changeCity(String text) {
        cityInputField.clear();
        cityInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public AddNewHospitalPage changeStreet(String text) {
        streetInputField.clear();
        streetInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public AddNewHospitalPage changeBuilding(String text) {
        buildingInputField.clear();
        buildingInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public void addHospitalName(String text) {
        nameInputField.clear();
        nameInputField.sendKeys(text);
    }

    public void addHospitalDescription(String text) {
        descriptionInputField.clear();
        descriptionInputField.sendKeys(text);
    }

   public void pushFindButton() {
        findButton.click();
   }

   public void pushFillButton() {
        fillButton.click();
   }

   public void pushResetButton() {
        resetButton.click();
   }

   public HospitalListPage pushSaveButton() {
        saveButton.click();
        return new HospitalListPage();
   }

   public void addNewHospital(String address, String name, String description) {
       BrowserWrapper.sleep(2);
       addressData(address);
       addHospitalName(name);
       addHospitalDescription(description);
       BrowserWrapper.sleep(2);
       pushFillButton();
       BrowserWrapper.sleep(2);
       pushFindButton();
       BrowserWrapper.sleep(2);
       pushSaveButton();
   }
}
