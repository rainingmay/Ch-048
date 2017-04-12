package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;


import java.util.LinkedList;
import java.util.List;

public class HospitalListPage extends PageObject {

    public AdminHeader header;

    public HospitalListPage(WebDriver driver) {
        super(driver);
        this.header = new AdminHeader(driver);
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/div[1]/a[1]")
    private WebElement addNewHospitalButton;

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/div[1]/a[2]")
    private WebElement checkGooglePoiButton;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "table")
    private WebElement tableBody;

    WebElement showOnMap;
    WebElement editButton;
    WebElement removeButton;
    WebElement deleteButton;
    WebElement cancelButton;

    public AddNewHospitalPage submitAddNewHospital() {
        addNewHospitalButton.click();
        return new AddNewHospitalPage(driver);
    }

    public CheckGooglePOIPage submitCheckGooglePoi() {
        checkGooglePoiButton.click();
        return new CheckGooglePOIPage(driver);
    }

    public HospitalListPage showOnMapButton(int rowNumber) {
        showOnMap = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form button:nth-child(2)"));
        showOnMap.click();
        return new HospitalListPage(driver);
    }

    public AddNewHospitalPage editButton(int rowNumber) {
        editButton = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form a"));
        editButton.click();
        return new AddNewHospitalPage(driver);
    }

    public HospitalListPage removeButton(int rowNumber) {
        removeButton = driver.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form button:nth-child(4)"));
        removeButton.click();
        deleteButton = driver.findElement(By.xpath("//*[@id=\"6\"]/div/div/div[3]/button[1]"));
        cancelButton = driver.findElement(By.xpath("//*[@id=\"6\"]/div/div/div[3]/button[2]"));
        deleteButton.click();
        return new HospitalListPage(driver);
    }

    public List<String> getHospitalDataFromTableRow(int rowNumber) {
        List<String> result = new LinkedList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(1)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(2)")).getText());
        }
        return result;
    }

    public int getCountOfHospitalsInTable() {
        return tableBody.findElements(By.cssSelector("tr")).size();
    }
}
