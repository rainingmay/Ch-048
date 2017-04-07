package pageObjects.doctor;


import org.openqa.selenium.WebDriver;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.DoctorHeader;

public class WorkScheduler extends PageObject {



    public WorkScheduler(WebDriver driver) {
        super(driver, new DoctorHeader(driver));
    }
}
