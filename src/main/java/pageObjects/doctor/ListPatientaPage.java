package pageObjects.doctor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.DoctorHeader;

public class ListPatientaPage extends PageObject {

    @FindBy(className = "label[for=\"usr\"]")
    private WebElement showPatientsLabel;

    @FindBy(id = "usr")
    private WebElement searchTextField;

    @FindBy(id = "searchButton")
    private WebElement buttonSearch;

    @FindBy(css = "i.glyphicon.glyphicon-refresh")
    private WebElement searchRefresh;

    @FindBy (className = "fa fa-angle-double-left")
    private WebElement goFirstPage;

    @FindBy (className = "fa fa-angle-double-right")
    private WebElement goLastPage;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[2]/div[1]")
    private WebElement colomEmailPatients;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[3]/div[1]")
    private WebElement colomFirstName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[4]/div[1]")
    private WebElement colomLastName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[2]/div[2]/a/i")
    private WebElement sortPatients;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[3]/div[2]/a/i")
    private WebElement sortByFirstName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[4]/div[2]/a/i")
    private WebElement sortByLastName;

    //First row
    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[2]/a")
    private WebElement firstRowPatient;

    @FindBy (xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[3]")
    private WebElement firstRowFirstName;

    @FindBy (xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[4]")
    private WebElement firstRowLastName;

    public void searchByFirstAndLastName(String value){
        searchTextField.clear();
        searchTextField.sendKeys(value);
    }

    public void searchButtonClick(){
       buttonSearch.click();
    }

    public void searchRefresh(){
        searchRefresh.click();
    }

    public ListPatientaPage(WebDriver driver) {
    super(driver, new DoctorHeader(driver));
    }




   }
