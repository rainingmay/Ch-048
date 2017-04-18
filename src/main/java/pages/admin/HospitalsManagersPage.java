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
public class HospitalsManagersPage extends PageObject {

    public AdminHeader header;

    public HospitalsManagersPage(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
    }

    public AdminHeader adminHeader;


    @FindBy(css = "tbody")
    private WebElement table;

    private WebElement selectManager;

    private WebElement deleteManager;

    public void changeManager(int rowNumber, String name) {
        selectManager = driver.findElement(By.xpath("tbody tr[" + rowNumber + "] td[3] select"));
        selectManager.findElement(By.linkText(name));
    }

    public void deleteManager(int rowNumber) {
      deleteManager = driver.findElement(By.cssSelector("tbody tr[" + rowNumber + "] btn btn-danger"));
      deleteManager.click();
    }
}
