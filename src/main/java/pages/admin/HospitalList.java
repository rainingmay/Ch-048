package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;


/**
 * Created by Evgen on 09.04.2017.
 */
public class HospitalList extends PageObject {

    public AdminHeader header;

    public HospitalList(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
    }

    @FindBy(css = ".btn-group a:first-child")
    private WebElement addNewHospitalButton;

    @FindBy(css = ".btn-group a:last-child")
    private WebElement checkGooglePOIButton;

    @FindBy(css = "tbody")
    private WebElement table;

    private WebElement showOnMapButton;

    private WebElement editButton;
    private WebElement deleteButton;
    private WebElement submitButton;
    private WebElement cancelButton;


    public AddNewHospitalPage editHospital(int rowNumber) {
        editButton = table.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form a"));
        editButton.click();
        return new AddNewHospitalPage(driver);
    }

    public HospitalList deleteHospital(int rowNumber) {
        deleteButton = table.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form button:nth-child(4)"));
        deleteButton.click();
        submitButton = driver.findElement(By.xpath("//*[@id=\"3\"]/div/div/div[3]/button[1]"));
        cancelButton = driver.findElement(By.xpath("//*[@id=\"3\"]/div/div/div[3]/button[2]"));
        submitButton.click();
        return new HospitalList(driver);
    }

    public AddNewHospitalPage addNewHospital() {
        addNewHospitalButton.click();
        return new AddNewHospitalPage(driver);
    }


}
