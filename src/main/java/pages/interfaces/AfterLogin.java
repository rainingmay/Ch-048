package pages.interfaces;

import org.openqa.selenium.WebDriver;
import pages.allUsers.PageObject;


/**
 * Created by ytomktc on 07.04.2017.
 */
public interface AfterLogin {
     PageObject loginAsAdmin(String email, String password) throws InterruptedException;
     PageObject loginAsManager(String email, String password) throws InterruptedException;
     PageObject loginAsDoctor(String email, String password);
     PageObject loginAsPatient(String email, String password);
}
