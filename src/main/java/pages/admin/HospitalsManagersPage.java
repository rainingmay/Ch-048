package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.AdminHeader;
import utils.Driver;


/**
 * Created by Evgen on 09.04.2017.
 */
public class HospitalsManagersPage extends BasePage {

    public AdminHeader header;

    public HospitalsManagersPage() {
        this.header = new AdminHeader();
    }

    public AdminHeader adminHeader;


    @FindBy(css = "tbody")
    private WebElement table;

    private WebElement selectManager;

    private WebElement deleteManager;

    public void changeManager(int rowNumber, String name) {
        selectManager = Driver.instance().findElement(By.xpath("tbody tr[" + rowNumber + "] td[3] select"));
        selectManager.findElement(By.linkText(name));
    }

    public void deleteManager(int rowNumber) {
      deleteManager = Driver.instance().findElement(By.cssSelector("tbody tr[" + rowNumber + "] btn btn-danger"));
      deleteManager.click();
    }
}
