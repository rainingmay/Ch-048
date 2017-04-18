package pages.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;

public class PatientsCardPage extends PageObject {
    public DoctorHeader header;

    @FindBy(css = "a.btn.btn-info")
    private WebElement addNewRecord;

    @FindBy (xpath = "//*[@id=\"headingOne\"]")
    private WebElement patientRecords;

    @FindBy (xpath = "//*[@id=\"headingOne\"]/h4/span[2]/a")
    private WebElement editRecord;

    public PatientsCardPage(WebDriver driver) {
        super(driver);
        this.header = new DoctorHeader(driver);
    }
    public boolean checkRecord(){
        return BrowserWrapper.isElementPresent(patientRecords);
    }
    public void addNewRecordButtonClick (){
        addNewRecord.click();
    }
    public void editButtonClick(){
        editRecord.click();
    }
}
