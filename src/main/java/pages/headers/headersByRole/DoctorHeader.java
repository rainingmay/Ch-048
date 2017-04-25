package pages.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.doctor.ListPatientaPage;
import pages.doctor.WorkSchedulerPage;
import pages.doctor.WorkSchedulerPage;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 06.04.2017.
 */
public class DoctorHeader extends AuthorizedHeader {

    public DoctorHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href=\"/HospitalSeeker/patients\"]")
    private WebElement patients;

    @FindBy(css = "a[href=\"/HospitalSeeker/workscheduler\"]")
    private WebElement schedule;


    public ListPatientaPage patientsButtonClick(){
        BrowserWrapper.waitUntilElementClickable(patients);
        patients.click();
        return new ListPatientaPage(driver);
    }
    public WorkSchedulerPage scheduleButtonClick(){
        schedule.click();
        return new WorkSchedulerPage(driver);
    }

}
