package pageObjects.interfaces;

import org.openqa.selenium.WebDriver;
import pageObjects.allUsers.PageObject;

/**
 * Created by ytomktc on 07.04.2017.
 */
public interface AfterLogin {
    public PageObject loginAsAdmin(String email, String password) throws InterruptedException;
    public PageObject loginAsManager(String email, String password) throws InterruptedException;
    public PageObject loginAsDoctor(String email, String password);
    public PageObject loginAsPatient(String email, String password);
}
