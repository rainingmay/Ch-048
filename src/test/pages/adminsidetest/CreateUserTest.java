
package pages.adminsidetest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import pages.manager.HospitalsPage;
import pages.manager.SchedulerPage;
import utils.BaseNavigation;
import utils.BaseTest;

import java.sql.Driver;

public class CreateUserTest extends BaseTest {
    public CreateUserTest() {
    }

    @Test
    public void addNewUserTest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, "admin@hospitals.ua", "1111");
        AddUserPage addUserPage = new AddUserPage(this.driver);
        addUserPage.addNewUser("testwadmin@gmail.com", "Q12345w", "ADMIN");
        //BaseNavigation.logout(driver);
    }

    @Test
    public void emailWithoutDotTest() throws Exception {
        BaseNavigation.login(driver, "admin@hospitals.ua", "1111");
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser("test2admin@gmailcom", "Q12345w", "ADMIN");
        BaseNavigation.logout(driver);
        BaseNavigation.login(driver, "test2admin@gmailcom", "Q12345w");
        BaseNavigation.logout(driver);


    }
}
