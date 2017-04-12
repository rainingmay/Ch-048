package pages.doctor;


import org.openqa.selenium.WebDriver;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.DoctorHeader;

public class WorkScheduler extends BasePage {

    public DoctorHeader header;

    public WorkScheduler(WebDriver driver) {
        super(driver);
        this.header = new DoctorHeader(driver);
    }
}
