package pages.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.DoctorHeader;

public class PatientsCardPage extends BasePage {
    public DoctorHeader header;

    @FindBy(css = "a.btn.btn-info")
    private WebElement addNewRecord;

    @FindBy (className = "alert alert-warning")
    private WebElement allRecords;

    @FindBy (xpath = "//*[@id=\"headingOne\"]/h4/span[2]/a")
    private WebElement editRecord;

    public PatientsCardPage(WebDriver driver) {
        super(driver);
        this.header = new DoctorHeader(driver);
    }

    public void addNewRecordButtonClick (){
        addNewRecord.click();
    }
    public void editButtonClick(){
        editRecord.click();
    }
}
