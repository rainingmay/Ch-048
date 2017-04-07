package pageObjects.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.DoctorHeader;

public class CreateNewRecord extends PageObject {
    @FindBy(className = "label[for=\"complaint\"]")
    private WebElement complaintLabel;

    @FindBy (id = "complaint")
    private WebElement complaint;

    @FindBy(css = "a.btn.btn-danger")
    private WebElement backButton;

    @FindBy(css = "button.btn.btn-default")
    private WebElement submitButton;

    @FindBy(className = "<label[for=\"result\"]")
    private WebElement resultLabel;

    @FindBy (id = "result")
    private WebElement result;

    @FindBy (className = "label[for=\"prescription\"]")
    private WebElement prescriptionLabel;

    @FindBy(id = "prescription")
    private WebElement prescription;


   public CreateNewRecord(WebDriver driver) {
        super(driver, new DoctorHeader(driver));
    }

    public void inputComplaint(String value){
       complaint.sendKeys(value);
    }

    public void inputResult(String value){
        result.sendKeys(value);
    }

    public void inputPrescription(String value){
        prescription.sendKeys(value);
    }

    public PatientsCardPage backButtonClick (){
        backButton.click();
        return new PatientsCardPage(driver);
    }
    public PatientsCardPage submitButtonClick(){
        submitButton.click();
        return new PatientsCardPage(driver);
    }
}
