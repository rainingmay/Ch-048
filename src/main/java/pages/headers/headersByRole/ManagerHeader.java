package pages.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.manager.AddNewDoctorPage;
import pages.manager.HospitalsPage;
import pages.manager.ModerationFeedBackPage;


/**
 * Created by Evgen on 06.04.2017.
 */
public class ManagerHeader extends AuthorizedHeader {

    public ManagerHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Actions")
    private WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/manage/hospitals]")
    private WebElement hospitals;

    @FindBy(css = "a[href=\"/HospitalSeeker/newDoctor]")
    private WebElement addDoctor;

    @FindBy(css = "a[href=\"/HospitalSeeker/moderationFeedbacks]")
    private WebElement feedbacks;


    public AddNewDoctorPage addNewDoctorPage() {
        addDoctor.click();
        return new AddNewDoctorPage(driver);
    }

    public HospitalsPage managePage() {
        hospitals.click();
        return new HospitalsPage(driver);
    }

    public ModerationFeedBackPage feedBackPage() {
        feedbacks.click();
        return new ModerationFeedBackPage(driver);
    }




}
