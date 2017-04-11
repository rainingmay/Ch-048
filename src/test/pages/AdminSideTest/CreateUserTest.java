//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tests.AdminCreateUserTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import pages.admin.AddUserPage;
import tests.AdminCreateUserTest.BaseTest;
import utils.BaseNavigation;

import java.sql.Driver;

public class CreateUserTest extends BaseTest {
    public CreateUserTest() {
    }

    @Test
    public void atest() throws Exception {
        BaseNavigation.loginAsAdmin(driver, "patient.cd@hospitals.ua", "1111");
        //AddUserPage addUserPage = new AddUserPage(driver);
        //addUserPage.addNewUser("testwadmin@gmail.com", "Q12345w", "ADMIN");
        BaseNavigation.logout(driver);
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

    @Test
    public void emailWithoytSymbolTest() throws Exception {
    }

    @Test
    private boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (NoSuchElementException var3) {
            return false;
        }
    }
}
