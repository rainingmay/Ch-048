
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

    public static final String NEWUSERLOGIN = "testwadmin2@gmail.com.ua.bb";
    public static final String NEWUSERPASSWORD = "Q12345w";
    public static final String NEWUSERROLE = "ADMIN";



    @Test
    public void addNewUserTest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, ADMIN_LOGIN, ADMIN_PASSWORD);
        AllUsersPage allUsersPage = new AllUsersPage(driver);
        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.addNewUser(NEWUSERLOGIN, NEWUSERPASSWORD, NEWUSERROLE);
        //BaseNavigation.logout(driver);
    }





}
