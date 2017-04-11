package pages.AdminSideTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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
public class AdminTest {

    private static final String LOGIN = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";

    protected WebDriver driver;

    @BeforeTest
    public void before() {
        driver = BrowserWrapper.browserInitialization();
    }

    @AfterTest
    public void after() {
        BrowserWrapper.browserClose(driver);
    }


    @Test
    public void correctUsersDataTest() {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            while (true) {
                Thread.sleep(2000);
                for (int i = 1; i < allUsersPage.gecCountOfUsersInTable(); i++) {
                    List<String> actual = allUsersPage.getUserDataFromTableRow(i);
                    List<String> expected = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
                    Assert.assertEquals(actual, expected);
                }
                if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) break;
                else allUsersPage.nextPageButton.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void viewInformTest () {
        try {
            AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(driver, LOGIN, PASSWORD);
            while (true) {
                Thread.sleep(2000);
                for (int i = 1; i < allUsersPage.gecCountOfUsersInTable(); i++) {
                    Thread.sleep(2000);
                    List<String> actual = allUsersPage.getUserDataFromInfoWindow(i);
                    String email = actual.get(0);
                    List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(email);
                    List<String> expected = new LinkedList<>();
                    expected.add(allInfo.get(0));
                    expected.add("true");
                    Assert.assertEquals(actual, expected);
                }
                if (driver.findElements(By.cssSelector("a[aria-label='Next']")).size() == 0) break;
                else allUsersPage.nextPageButton.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
