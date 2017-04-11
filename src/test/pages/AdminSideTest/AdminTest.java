package pages.AdminSideTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.admin.AllUsersPage;
import databaseUtil.UserDAO;
import utils.BaseNavigation;
import utils.BaseTest;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AdminTest extends BaseTest {

    private static final String LOGIN = "admin@hospitals.ua";
    private static final String PASSWORD = "1111";


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
                    List<String> actual = allUsersPage.getUserDataFromInfoWindow(i);
                    List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(0));
                    List<String> expected = new LinkedList<>();
                    expected.add(allInfo.get(0));
                    expected.add("ENABLE");
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
