package pages.allUsers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;


public class DoctorInfoPage implements PageInitializer {
    BaseHeader baseHeader;

    @FindBy(css = "img.img-thumbnail")
    private WebElement avatarImage;

    @FindBy(id = "addAppointment")
    private WebElement appointmentButton;

    @FindBy(css = "div.panel-heading text-center")
    private WebElement doctorsNameLabel;

    @FindBy(css = "div.panel-body")
    public WebElement doctorInfoLabel;

    @FindBy(css = "div.panel-footer")
    private WebElement doctorsHospitalLabel;

    @FindBy(id = "input-feedback")
  //  @FindBy(xpath = "//*[@id=\"input-feedback\"]")
    public WebElement doctorFeedbackInput;

    @FindBy(id = "sendFeedback")
    private WebElement sendFeedbackButton;



    public void createFeedBack(String value){
        System.out.println("ksks");
        BrowserWrapper.waitUntilElementVisible(doctorFeedbackInput);
        doctorFeedbackInput.clear();
        BrowserWrapper.sleep(5);
        sendFeedbackButton.sendKeys(value);
        BrowserWrapper.sleep(2);
        sendFeedbackButton.click();
        BrowserWrapper.refreshPage();;
    }



    public DoctorInfoPage() {
        baseHeader = new BaseHeader();
        pageInitialization();
    }
}
