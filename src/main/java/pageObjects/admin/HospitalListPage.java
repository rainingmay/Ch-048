package pageObjects.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.AdminHeader;

import java.util.LinkedList;
import java.util.List;

public class HospitalListPage extends PageObject{
<<<<<<< HEAD

    public AdminHeader header;

    public HospitalListPage(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
=======
    public AdminHeader adminHeader;
    public HospitalListPage(WebDriver driver) {
        super(driver);
        adminHeader = new AdminHeader(driver);
>>>>>>> 1c15c20239dd35c991dae1a871918a5ccdd230cb
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/div[1]/a[1]")
    private WebElement addNewHospitalButton;

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/div[1]/a[2]")
    private WebElement checkGooglePoiButton;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "table")
    private WebElement tableBody;

    public AddNewHospitalPage submitAddNewHospital() {
        addNewHospitalButton.click();
        return new AddNewHospitalPage(driver);
    }

    public CheckGooglePOIPage submitCheckGooglePoi() {
        checkGooglePoiButton.click();
        return new CheckGooglePOIPage(driver);
    }

    public List<String> getHospitalDataFromTableRow(int rowNumber) {
        List<String> result = new LinkedList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(1)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(2)")).getText());
        }
        return result;
    }
}
