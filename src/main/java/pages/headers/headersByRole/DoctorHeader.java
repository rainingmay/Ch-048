package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.doctor.ListPatientaPage;
import pages.doctor.WorkSchedulerPage;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 06.04.2017.
 */
public class DoctorHeader extends AuthorizedHeader {

    @FindBy(css = "a[href=\"/HospitalSeeker/\"]")
    private WebElement home;

    @FindBy(css = "a[href=\"/HospitalSeeker/mapsearch\"]")
    private WebElement nearestHospital;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/patients\"]")
    private WebElement patients;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/workscheduler\"]")
    private WebElement schedule;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[6]/a")
    @FindBy(css = "a.nav.navbar-nav.dropdown.dropdown-toggle.active")
    private WebElement profile;

//    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[6]/ul/li[2]/a")
    @FindBy(css = "a[href=\"/HospitalSeeker/logout\"]")
    private WebElement logOut;

    public ListPatientaPage patientsButtonClick(){
        BrowserWrapper.waitUntilElementClickable(patients);
        patients.click();
        return new ListPatientaPage();
    }
    public WorkSchedulerPage scheduleButtonClick(){
        schedule.click();
        return new WorkSchedulerPage();
    }

}
