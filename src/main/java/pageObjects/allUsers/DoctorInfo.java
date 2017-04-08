package pageObjects.allUsers;

import org.openqa.selenium.WebDriver;
import pageObjects.headers.BaseHeader;

/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorInfo extends PageObject{
    public DoctorInfo(WebDriver driver) {
        super(driver, new BaseHeader(driver));
    }
}
