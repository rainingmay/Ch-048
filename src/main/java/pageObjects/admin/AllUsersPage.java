package pageObjects.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.AdminHeader;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AllUsersPage extends PageObject{
    private WebDriver driver;
    private AdminHeader header;


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

    @FindBy(css = "body > section > div.content > div > div > ul > li:last-child")
    private WebElement lastPageButton;

    @FindBy(css = "body > section > div.content > div > div > ul > li:first-child > a")
    private WebElement firstPageButto;

    public AllUsersPage(WebDriver driver) {
        super(driver);
    }
}
