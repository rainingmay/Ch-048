package pages.admin;

import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;


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
    public WebElement searchButton;

    @FindBy(id = "clearButton")
    private WebElement clearButton;

    @FindBy(xpath = "/html/body/section/div[1]/div/form/div[5]/a[1]")
    private WebElement enableButton;

    @FindBy(xpath = "/html/body/section/div[1]/div/form/div[5]/a[2]")
    private WebElement disableButton;

    @FindBy(css = ".pull-right .btn-group a:last-child")
    private WebElement allUsersButton;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "tbody")
    public WebElement tableBody;

    @FindBy(css = "pagination pagination-lg")
    private WebElement navigation;

    @FindBy(css = "body section div.content div div ul li:last-child")
    private WebElement lastPageButton;

    @FindBy(css = "body section div.content div div ul li:first-child a")
    private WebElement firstPageButto;

    @FindBy(id = "email")
    public WebElement sortByEmailButton;

    @FindBy(id = "detail.firstName")
    private WebElement sortByFirsNamButton;

    @FindBy(id = "detail.lastName")
    private WebElement sortByLastNameButton;

    @FindBy(xpath = "/html/body/section/div[1]/div[1]/div/h4")
    public WebElement createdLabel;


    @FindBy(id = "roles.type")
    private WebElement sortByRoleButton;

    private WebElement viewWindow;

    @FindBy(className = "table table-user-information")
    private WebElement editWindow;

    @FindBy(className = "table table-user-information form-control")
    private WebElement editRoleSelect;

    @FindBy(css = "a[aria-label='Next']")
    public WebElement nextPageButton;


    private WebElement editButton;



    public WebElement deleteWindow;

    private WebElement deleteButton;



    public AllUsersPage(WebDriver driver) {

        super(driver);
        this.header = new AdminHeader(driver);
    }


    public AllUsersPage getCreatedTableText(WebDriver driver){
        createdLabel.getText();
        return new AllUsersPage(driver);
    }


    public AllUsersPage showAllUsers() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , enableButton);
        return new AllUsersPage(driver);
    }

    public AllUsersPage showEnableUsers() {
        enableButton.click();
        BrowserWrapper.waitForPage(driver);
        return new AllUsersPage(driver);
    }

    public AllUsersPage showDisableUsers() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , disableButton);
        BrowserWrapper.waitForPage(driver);
        return new AllUsersPage(driver);
    }


    public AllUsersPage changeCountOfUsersOnPage(int count) {
        selectDropdownCount(usersPerPagePopUp, String.valueOf(count));
        BrowserWrapper.sleep(3);
        return new AllUsersPage(driver);
    }

    public AllUsersPage changeRole(String role) {
        this.role.findElement(By.cssSelector("option[value=" + role + "]")).click();
        searchButton.click();
        BrowserWrapper.sleep(3);
        return new AllUsersPage(driver);
    }


    public void changeSearchBy(String field) {
        searchBy.findElement(By.cssSelector("option[value=" + field + "]")).click();
    }


    public void sendKeysToSearchField(String keys) {
        searchWindow.clear();
        searchWindow.sendKeys(keys);
    }

    public AllUsersPage search(int count, String role, String field, String keys) {
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



    public List<String> getUserDataFromInfoWindow(int rowNumber) throws InterruptedException {
        List<String> result = new LinkedList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            WebElement infoButton = tableRow.findElement(By.id("viewUser"));
            infoButton.click();
            Thread.sleep(2000);
            viewWindow = driver.findElement(By.className("modal-content"));
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(1) td:last-child")).getText());
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(2) td:last-child")).getText());
            result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(4) td:last-child")).getText());
            closeViewWindow();
            Thread.sleep(2000);
        }
        return result;
    }


    public int getCountOfUsersInTable() {
        return tableBody.findElements(By.cssSelector("tr")).size();
    }


    public void closeViewWindow(){
        viewWindow.findElement(By.className("close")).click();
    }


    public void closeEditWindow() {
        editWindow.findElement(By.className("close")).click();
    }



    public WebElement openEditWindow(int rowNumber) {
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            WebElement editButton = tableRow.findElement(By.id("ediUser"));
            editButton.click();
            BrowserWrapper.sleep(1);
            editWindow = driver.findElement(By.id("detailForm"));
            return editWindow;
        }
        return null;
    }

    public AllUsersPage changeRoleInEditWindow(int rowNumber, String role) {
        openEditWindow(rowNumber);
        BrowserWrapper.sleep(3);
        selectDropdownRole(editWindow.findElement(By.id("userRoles")), role);
        BrowserWrapper.sleep(3);
        editWindow.findElement(By.cssSelector("input[value=\"Edit\"]")).click();
        BrowserWrapper.sleep(3);
        return new AllUsersPage(driver);
    }

    public static void selectDropdownRole(WebElement element, String text) {
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(element);
        if (dropdown.getAllSelectedOptions().size() != 0) dropdown.deselectAll();
        dropdown.selectByValue(text);
    }

    public static void selectDropdownCount(WebElement element, String text) {
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(element);
        //dropdown.selectByVisibleText(text);
        //if (dropdown.getAllSelectedOptions().size() != 0) dropdown.deselectAll();
        dropdown.selectByValue(text);
    }

    public AllUsersPage toNextPage() {
            if (!nextPageButton.equals(null)) {
                nextPageButton.click();
                return new AllUsersPage(driver);
            }
            return null;
    }

    public AllUsersPage deleteUser(int rowNumber) {
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            deleteButton = tableRow.findElement(By.id("deleteUser"));
            deleteButton.click();
            BrowserWrapper.sleep(3);
            deleteWindow = driver.findElement(By.className("modal-content"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();" , driver.findElement(By.id("deleteButton")));
            BrowserWrapper.sleep(2);
            return new AllUsersPage(driver);
        }
        return null;
    }

    public AllUsersPage clickSortByEmail() {
        sortByEmailButton.click();
        return new AllUsersPage(driver);
    }





    public boolean equals(AllUsersPage allUsersPage) {
        if (this.tableBody.equals(allUsersPage.tableBody)) return true;
        return false;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
