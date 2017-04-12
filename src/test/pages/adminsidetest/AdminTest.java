package pages.adminsidetest;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.admin.AllUsersPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.databaseutil.UserDAO;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AdminTest extends BaseTest {


    private static final String LOGIN = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";



    @Test(priority = 1)
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(3);

            List<String> actual = allUsersPage.getUserDataFromTableRow(4);
            List<String> expected = UserDAO.getUserFromDatabaseByEmail(actual.get(0));

            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void viewInformTest () {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(2);

            List<String> actual = allUsersPage.getUserDataFromInfoWindow(5);
            List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
            List<String> expected = new LinkedList<>();
            Collections.addAll(expected, new String[]{allInfo.get(0), "true"});

            BrowserWrapper.sleep(2);

            Assert.assertEquals(actual, expected);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 3)
    public void editWindowTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            BrowserWrapper.sleep(3);

            boolean actual;
            try{
                actual = allUsersPage.openEditWindow(1).findElement(By.cssSelector("input")).isDisplayed();
            } catch (ElementNotVisibleException e) {actual = false;}

            allUsersPage.closeEditWindow();

            BrowserWrapper.sleep(3);

            Assert.assertEquals(actual, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeRoleTest() {

    }


    public boolean toNextPage(AllUsersPage allUsersPage) {
        if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) return false;
        else {
            allUsersPage.nextPageButton.click();
            return true;
        }
    }

}
