package pages.AdminSideTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.admin.AllUsersPage;
import databaseUtil.UserDAO;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AdminTest extends BaseTest{
    private static final String LOGIN = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";

    @Test
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);

            Thread.sleep(1000);
            List<String> actual = allUsersPage.getUserDataFromTableRow(4);
            List<String> expected = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
            Assert.assertEquals(actual, expected);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        after();
    }

    @Test
    public void viewInformTest () {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            Thread.sleep(2000);

            List<String> actual = allUsersPage.getUserDataFromInfoWindow(5);
            List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
            List<String> expected = new LinkedList<>();
            expected.add(allInfo.get(0));
            expected.add("true");
            Assert.assertEquals(actual, expected);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        after();
    }

    @Test
    public void editWindowTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            Thread.sleep(2000);

            List<String> actual = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean toNextPage(AllUsersPage allUsersPage) {
        if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) return false;
        else {
            allUsersPage.nextPageButton.click();
            return true;
        }
    }

}
