
package pages.AdminSideTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import pages.anonymous.LoginPage;
import pages.manager.HospitalsPage;
import pages.manager.SchedulerPage;
import utils.BaseNavigation;
import utils.BaseTest;
import org.testng.annotations.Parameters;

import java.sql.Driver;

public class CreateUserTest extends BaseTest {
    public CreateUserTest() {
    }

    @Test
    public void addNewUserTest() throws Exception {


        BaseNavigation.loginAsAdmin(driver, "admin@hospitals.ua", "1111");
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser("testwadmin2@gmail.com.ua", "Q12345w", "ADMIN");
        //BaseNavigation.logout(driver);
    }





}
