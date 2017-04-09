package pageObjects.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.BaseHeader;
import pageObjects.headers.headersByRole.AdminHeader;

/**
 * Created by Evgen on 09.04.2017.
 */
public class HospitalsManagers extends PageObject{

    public HospitalsManagers(WebDriver driver) {
        super(driver, new AdminHeader(driver));
    }

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
