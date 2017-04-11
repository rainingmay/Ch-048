package pages.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;
import pages.headers.headersByRole.DoctorHeader;

public class WorkScheduler extends PageObject {

    public DoctorHeader header;

    public WorkScheduler(WebDriver driver) {
        super(driver);
        this.header = new DoctorHeader(driver);
    }
}
