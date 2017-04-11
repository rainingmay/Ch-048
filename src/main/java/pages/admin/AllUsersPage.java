package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AllUsersPage extends PageObject {

    public AdminHeader header;

    @FindBy(id = "userPerPage")
    private WebElement usersPerPagePopUp;

    @FindBy(id = "pref-roleby")
    private WebElement role;

    @FindBy(id = "searchBy")
    private WebElement searchBy;

    @FindBy(id = "search")
    private WebElement searchWindow;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id = "clearButton")
    private WebElement clearButton;

    @FindBy(xpath = "/html/body/section/div[1]/div/form/div[5]/a[1]")
    private WebElement enable;

    @FindBy(xpath = "//*[@id=\"searchForm\"]/div[5]/a[2]")
    private WebElement disable;

    @FindBy(xpath = "//*[@id=\"searchForm\"]/div[5]/a[3]")
    private WebElement allUsers;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "tbody")
    private WebElement tableBody;

    @FindBy(css = "pagination pagination-lg")
    private WebElement navigation;

    @FindBy(css = "body section div.content div div ul li:last-child")
    private WebElement lastPageButton;

    @FindBy(css = "body section div.content div div ul li:first-child a")
    private WebElement firstPageButto;

    @FindBy(id = "email")
    private WebElement sortByEmailButton;

    @FindBy(id = "detail.firstName")
    private WebElement sortByFirsNamButton;

    @FindBy(id = "detail.lastName")
    private WebElement sortByLastNameButton;

    @FindBy(id = "roles.type")
    private WebElement sortByRoleButton;


    private WebElement viewWindow;

    @FindBy(className = "table table-user-information")
    private WebElement editWindow;

    @FindBy(className = "table table-user-information form-control")
    private WebElement editRoleSelect;

    @FindBy(css = "a[aria-label='Next']")
    public WebElement nextPageButton;





    public AllUsersPage(WebDriver driver) {

        super(driver);
        this.header = new AdminHeader(driver);
    }


    public void changeCountOfUsersOnPage(int count) {
        usersPerPagePopUp.findElement(By.cssSelector("option[value=" + count + "]")).click();
    }

    public void changeRole(String role) {
        this.role.findElement(By.cssSelector("option[value=" + role + "]")).click();
    }

    public void changeSearchBy(String field) {
        this.searchBy.findElement(By.cssSelector("option[value=" + field + "]"));
    }

    public void sendKeysToSearchField(String keys) {
        searchWindow.clear();
        searchWindow.sendKeys(keys);
    }

    public AllUsersPage find(int count, String role, String field, String keys) {
        changeCountOfUsersOnPage(count);
        changeRole(role);
        changeSearchBy(field);
        sendKeysToSearchField(keys);
        searchButton.click();
        return new AllUsersPage(driver);
    }





    public List<String> getUserDataFromTableRow(int rowNumber) {
        List<String> result = new LinkedList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(2)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(4)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(5)")).getText());
        }
        return result;
    }

    public List<String> getUserDataFromInfoWindow(int rowNumber) {
        List<String> result = new LinkedList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            WebElement infoButton = tableRow.findElement(By.id("viewUser"));
            infoButton.click();
            viewWindow = driver.findElement(By.className("modal-content"));
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(1) td:last-child")).getText());
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(2) td:last-child")).getText());
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(4) td:last-child")).getText());

        }
        return result;
    }

    public int gecCountOfUsersInTable() {
        return tableBody.findElements(By.cssSelector("tr")).size();
    }

    public AddUserPage goToAddUser() throws InterruptedException {
        header.addUser();
        return new AddUserPage(driver);
    }

}
